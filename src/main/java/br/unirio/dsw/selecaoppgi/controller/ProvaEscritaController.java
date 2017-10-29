package br.unirio.dsw.selecaoppgi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.unirio.dsw.selecaoppgi.model.edital.Edital;
import br.unirio.dsw.selecaoppgi.model.edital.ProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.AvaliacaoProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoEdital;
import br.unirio.dsw.selecaoppgi.service.dao.InscricaoDAO;

@Controller
public class ProvaEscritaController {
	// /edital/escrita/presenca
	// /edital/escrita/nota

	// /edital/escrita/encerramento
	InscricaoDAO inscricaoDAO = new InscricaoDAO();

	/*
	 * Função que verifica quais candidatos possuem pendências e calcula a nota da prova caso não haja pendências.
	 * 
	 */
	@Secured("ROLE_PROFESSOR")
	public List<InscricaoEdital> VerificaCandidatosComPendenciaNasProvas(List<InscricaoEdital> ListaDeCanditados) {
		// TODO: Testar e Revisar
		List<InscricaoEdital> candidatosComPendencia = new ArrayList<InscricaoEdital>();

		for (InscricaoEdital candidato : ListaDeCanditados) {
			for (AvaliacaoProvaEscrita provaEscrita : candidato.getAvaliacoesProvasEscritas()) {

				int idCandidato = candidato.getIdCandidato();
				String codigoProva = provaEscrita.getProvaEscrita().getCodigo();

				if (inscricaoDAO.indicaAusenciaProvaEscrita(idCandidato, codigoProva)) {
					candidatosComPendencia.add(candidato);
				} else {
					CalculaNotaDaProvaEscrita(candidato);
				}
			}
		}
		return candidatosComPendencia;
	}
/*
 * Função que calcula a nota da prova Escrita
 */
	public void CalculaNotaDaProvaEscrita(InscricaoEdital candidato) {
		// Cálculo da prova
		Iterable<AvaliacaoProvaEscrita> provasEscritas = candidato.getAvaliacoesProvasEscritas();
		for(AvaliacaoProvaEscrita provaEscrita : provasEscritas) {
			if (!provaEscrita..isDispensavel()) {
				provaEscrita.adicionaQuestao(1);
				provaEscrita.adicionaQuestao(1);
				provaEscrita.adicionaQuestao(1);
			} else {
				return;
			}
		}
		AvaliacaoProvaEscrita avaliacaoProvaEscrita = new AvaliacaoProvaEscrita(provaEscrita);
		avaliacaoProvaEscrita.setNotaOriginalQuestao(0, 50);
		avaliacaoProvaEscrita.setNotaOriginalQuestao(1, 80);
		avaliacaoProvaEscrita.setNotaOriginalQuestao(2, 100);

		for (int i = 0; i < provaEscrita.contaQuestoes(); i++) {
			if (avaliacaoProvaEscrita.possuiNotaRecursoQuestao(i)) {
				avaliacaoProvaEscrita.setNotaOriginalQuestao(i, avaliacaoProvaEscrita.getNotaRecursoQuestao(i));
			}
		}

		int minhaNota = (avaliacaoProvaEscrita.getNotaOriginalQuestao(0)
				+ avaliacaoProvaEscrita.getNotaOriginalQuestao(1) + avaliacaoProvaEscrita.getNotaOriginalQuestao(2))
				/ provaEscrita.contaQuestoes();

		System.out.println(minhaNota >= provaEscrita.getNotaMinimaAprovacao() ? "aprovado" : "reprovado");
	}

	/**
	 * Ação AJAX que lista todas as inscrições de candidatos que podem fazer provas escritas de um edital
	 */
	@ResponseBody
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/edital/escrita/presenca", method = RequestMethod.GET, produces = "application/json")
	public String lista(@ModelAttribute("edital") Edital edital, @ModelAttribute("code") String codigoProva)
	{
		List<InscricaoEdital> lista = inscricaoDAO.carregaPresencaProvaEscrita(edital, codigoProva);

		Gson gson = new Gson();
		JsonArray jsonInscricoesEdital = new JsonArray();
		
		for (InscricaoEdital inscricao : lista)
			jsonInscricoesEdital.add(gson.toJsonTree(inscricao));
		
		JsonObject root = new JsonObject();
		root.addProperty("Result", "OK");
		root.add("Records", jsonInscricoesEdital);
		return root.toString();
	}
	
	/**
	 * Ação que redireciona o usuário para a tela presença em prova escrita
	 */
	@RequestMapping(value = "/edital/escrita/presenca", method = RequestMethod.GET)
	public ModelAndView mostraPaginaPresencaProvaEscrita() {
		ModelAndView model = new ModelAndView();
		model.setViewName("edital/escrita/presenca");
		return model;
	}

}
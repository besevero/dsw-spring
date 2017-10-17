package br.unirio.dsw.selecaoppgi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	 * Função que verifica quais candidatos possuem pendências
	 * 
	 */

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

	public void CalculaNotaDaProvaEscrita(InscricaoEdital candidato) {
		// Cálculo da prova
		ProvaEscrita provaEscrita = new ProvaEscrita();
		if (!provaEscrita.isDispensavel()) {
			provaEscrita.adicionaQuestao(1);
			provaEscrita.adicionaQuestao(1);
			provaEscrita.adicionaQuestao(1);
		} else {
			return;
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
	 * Ação que redireciona o usuário para a tela presença em prova escrita
	 */
	@RequestMapping(value = "/edital/escrita/presenca", method = RequestMethod.GET)
	public ModelAndView mostraPaginaPresencaProvaEscrita() {
		ModelAndView model = new ModelAndView();
		model.setViewName("edital/escrita/presenca");
		return model;
	}

}
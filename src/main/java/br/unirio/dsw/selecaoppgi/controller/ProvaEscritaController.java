package br.unirio.dsw.selecaoppgi.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;

import br.unirio.dsw.selecaoppgi.model.edital.Edital;
import br.unirio.dsw.selecaoppgi.model.edital.ProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.AvaliacaoProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoEdital;
import br.unirio.dsw.selecaoppgi.service.ServicoEdital;
import br.unirio.dsw.selecaoppgi.service.ServicoInscricao;
import br.unirio.dsw.selecaoppgi.service.dao.EditalDAO;
import br.unirio.dsw.selecaoppgi.service.dao.InscricaoDAO;
import br.unirio.dsw.selecaoppgi.service.dao.UsuarioDAO;
import br.unirio.dsw.selecaoppgi.service.json.JsonInscricaoWriter;

@Controller
public class ProvaEscritaController 
{
    @Autowired
	private UsuarioDAO userDAO;
    
    @Autowired
	private EditalDAO editalDAO;
    
    @Autowired
	private InscricaoDAO inscricaoDAO;
    
	// /edital/escrita/presenca
    /**
     * Ação AJAX que atualiza o status de presença dos candidatos em provas escritas
     */
    @ResponseBody
	@RequestMapping(value = "/edital/escrita/presenca/", method = RequestMethod.GET)
    public boolean atualizaPresenca(HttpServletRequest request, @ModelAttribute("code") String codigoProva, @ModelAttribute("id") int idInscricao, @ModelAttribute("status") boolean status) {
    	return ServicoInscricao.atualizaPresencaCandidato(request, codigoProva, idInscricao, status);
    }
	// /edital/escrita/nota

	// /edital/escrita/encerramento
		
	

	/**
	 * Ação AJAX que lista todas as inscrições de candidatos que podem fazer provas escritas de um edital
	 */
	@ResponseBody
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/edital/escrita/presenca", method = RequestMethod.GET, produces = "application/json")
	public String lista(HttpServletRequest request, @ModelAttribute("code") String codigoProva)
	{
		Edital editalSelecionado = ServicoEdital.pegaEditalSelecionado(request, editalDAO, userDAO);
		List<InscricaoEdital> lista = inscricaoDAO.carregaPresencaProvaEscrita(editalSelecionado, codigoProva);
		
		JsonInscricaoWriter writer = new JsonInscricaoWriter();
		JsonArray jsonInscricoesEdital = new JsonArray();
		
		for (InscricaoEdital inscricao : lista)
			jsonInscricoesEdital.add(writer.execute(inscricao));
		
		return jsonInscricoesEdital.toString();
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
	/*
	 * Função que verifica quais candidatos possuem pendências e calcula a nota da prova caso não haja pendências.
	 * 
	 */
	
	public List<InscricaoEdital> VerificaCandidatosComPendenciaNasProvas(List<InscricaoEdital> lista) {
		List<InscricaoEdital> candidatosComPendencia = new ArrayList<InscricaoEdital>();

		for (InscricaoEdital candidato : lista) {
			if (candidato.getHomologado() == false) {
					candidatosComPendencia.add(candidato);
				} else {
					CalculaNotaDaProvaEscrita(candidato);
				}
			}
		
		return candidatosComPendencia;
	}
/*
 * Função que calcula a nota da prova Escrita
 */
	public void CalculaNotaDaProvaEscrita(InscricaoEdital candidato) {
		// Cálculo da prova
		
		ProvaEscrita provaEscrita = new ProvaEscrita();
		 	  if(!provaEscrita.isDispensavel()) {
		     	  provaEscrita.adicionaQuestao(1);
		     	  provaEscrita.adicionaQuestao(1);
		     	  provaEscrita.adicionaQuestao(1);
		 	  }
		 	  else { return; }
		 	  AvaliacaoProvaEscrita avaliacaoProvaEscrita = new AvaliacaoProvaEscrita(provaEscrita);
		 	  avaliacaoProvaEscrita.setNotaOriginalQuestao(0, 50);
		 	  avaliacaoProvaEscrita.setNotaOriginalQuestao(1, 80);
		 	  avaliacaoProvaEscrita.setNotaOriginalQuestao(2, 100);
		 	  
		 	  for(int i=0; i<provaEscrita.contaQuestoes(); i++) {
		 	    if(avaliacaoProvaEscrita.possuiNotaRecursoQuestao(i)) {
		 	      avaliacaoProvaEscrita.setNotaOriginalQuestao(i, avaliacaoProvaEscrita.getNotaRecursoQuestao(i));
		 	    }
		 	  }
		 	  
		 	  int minhaNota = (avaliacaoProvaEscrita.getNotaOriginalQuestao(0) + avaliacaoProvaEscrita.getNotaOriginalQuestao(1) + avaliacaoProvaEscrita.getNotaOriginalQuestao(2))
		 	  / provaEscrita.contaQuestoes();
		 	  
		 	  System.out.println(minhaNota >= provaEscrita.getNotaMinimaAprovacao() ? "aprovado":"reprovado");
	}
}
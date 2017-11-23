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
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/edital/escrita/presenca/atualiza", method = RequestMethod.POST)
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
			if (candidato.getHomologado() == true) 
			{
				if(verificaSeEstaComTodasAsNotas(candidato)) {
					CalculaNotaDaProvaEscrita(candidato);
				}
				else
				{
					candidatosComPendencia.add(candidato);
				}
			} 
			
		}
		
		return candidatosComPendencia;
	}
	/*
	 * Verifica se todas as provas já estão com nota
	 */
	public boolean verificaSeEstaComTodasAsNotas(InscricaoEdital candidato) {
		
		Iterable<AvaliacaoProvaEscrita> listaAvaliacoes = candidato.getAvaliacoesProvasEscritas();
		
		for(AvaliacaoProvaEscrita prova : listaAvaliacoes) {
			int indiceQuestao = 0;
			while(indiceQuestao < prova.getProvaEscrita().contaQuestoes()) 
			{
				if(prova.possuiNotaOriginalQuestao(indiceQuestao) || prova.possuiNotaRecursoQuestao(indiceQuestao)) {
					
			}
				else 
				{
					return false;
				}
				indiceQuestao++;
			}
		}
		return true;
	}
	
	/*
	 * Função que calcula a nota da prova Escrita
	 */
	public void CalculaNotaDaProvaEscrita(InscricaoEdital candidato) {
		Iterable<AvaliacaoProvaEscrita> listaAvaliacoes = candidato.getAvaliacoesProvasEscritas();
		
		for(AvaliacaoProvaEscrita prova : listaAvaliacoes) {
			int indiceQuestao = 0;
			int somatorio = 0;
			int somaPesos = 0;
			if(!prova.getProvaEscrita().isDispensavel()) {
				while(indiceQuestao < prova.getProvaEscrita().contaQuestoes()) {
					int pegaPesoQuestao = prova.getProvaEscrita().pegaPesoQuestaoIndice(indiceQuestao);
					if(prova.possuiNotaRecursoQuestao(indiceQuestao)) {
						somatorio = somatorio + (prova.getNotaRecursoQuestao(indiceQuestao) * pegaPesoQuestao);
						somaPesos = somaPesos + pegaPesoQuestao;
						
					}
					else
					{
						somatorio = somatorio + (prova.getNotaOriginalQuestao(indiceQuestao) * pegaPesoQuestao);
						somaPesos = somaPesos + pegaPesoQuestao;
					}
				}
					indiceQuestao++;
				}
		}
	}
		
		
		
	
}
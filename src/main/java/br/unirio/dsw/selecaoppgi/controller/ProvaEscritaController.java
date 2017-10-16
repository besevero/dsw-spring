package br.unirio.dsw.selecaoppgi.controller;



import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.unirio.dsw.selecaoppgi.model.inscricao.AvaliacaoProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoEdital;
import br.unirio.dsw.selecaoppgi.service.dao.InscricaoDAO;
import br.unirio.dsw.selecaoppgi.view.login.ForgotPasswordForm;

@Controller
public class ProvaEscritaController
{
//	/edital/escrita/presenca
//	/edital/escrita/nota


//	/edital/escrita/encerramento
	InscricaoDAO inscricaoDAO = new InscricaoDAO();
	
	public List<InscricaoEdital> VerificaHomologacaoDasInscricoes(List<InscricaoEdital> ListaDeCanditados){
		//TODO: Testar e Revisar
		List<InscricaoEdital> candidatosComPendencia = new List<InscricaoEdital>();
		
		for(InscricaoEdital candidato : ListaDeCanditados){
			for(AvaliacaoProvaEscrita provaEscrita : candidato.getAvaliacoesProvasEscritas()) {
				
				int idCandidato = candidato.getIdCandidato();
				String codigoProva = provaEscrita.getProvaEscrita().getCodigo();
				
				if(inscricaoDAO.indicaAusenciaProvaEscrita(idCandidato, codigoProva)) {
					candidatosComPendencia.add(candidato);
				}else {
					
				}
			}
		}
		return candidatosComPendencia;
	}
	

    /**
     * Ação que redireciona o usuário para a tela presença em prova escrita
     */
	@RequestMapping(value = "/edital/escrita/presenca", method = RequestMethod.GET)
	public ModelAndView mostraPaginaPresencaProvaEscrita()
	{
		ModelAndView model = new ModelAndView();
        model.setViewName("edital/escrita/presenca");
		return model;
	}
	
}
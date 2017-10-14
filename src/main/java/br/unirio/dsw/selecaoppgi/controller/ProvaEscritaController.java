package br.unirio.dsw.selecaoppgi.controller;

import java.awt.List;

import org.springframework.stereotype.Controller;

import br.unirio.dsw.selecaoppgi.model.inscricao.AvaliacaoProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoEdital;
import br.unirio.dsw.selecaoppgi.service.dao.InscricaoDAO;

@Controller
public class ProvaEscritaController
{
//	/edital/escrita/presenca
//	/edital/escrita/nota


//	/edital/escrita/encerramento
	InscricaoDAO inscricaoDAO = new InscricaoDAO();
	
	public List VerificaHomologacaoDasInscricoes(List<InscricaoEdital> ListaDeCanditados){
		//TODO: Testar e Revisar;
		
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
}
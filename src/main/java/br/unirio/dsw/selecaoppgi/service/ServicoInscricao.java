package br.unirio.dsw.selecaoppgi.service;

import javax.servlet.http.HttpServletRequest;

import br.unirio.dsw.selecaoppgi.service.dao.InscricaoDAO;


/**
 * Classe responsavel por manipular a inscricao
 * 
 * @author Bernardo Severo
 */
public class ServicoInscricao {

	public static boolean atualizaPresencaCandidato(HttpServletRequest request, String codigoProva, int idInscricao, boolean status)
	{
		InscricaoDAO inscricaoDAO = new InscricaoDAO();
		if (status == true)
		{
			return inscricaoDAO.indicaPresencaProvaEscrita(idInscricao, codigoProva);
		}
		else
		{
			return inscricaoDAO.indicaAusenciaProvaEscrita(idInscricao, codigoProva);
		}
	}
}
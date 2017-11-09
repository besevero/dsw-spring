package br.unirio.dsw.selecaoppgi.service;

import javax.servlet.http.HttpServletRequest;

import br.unirio.dsw.selecaoppgi.service.dao.InscricaoDAO;


/**
 * Classe responsavel por manipular a inscricao
 * 
 * @author Bernardo Severo
 */
public class ServicoInscricao {

	public static void atualizaPresençaCandidato(HttpServletRequest request, String codigoProva, int idInscricao, boolean atualiza)
	{
		InscricaoDAO inscricaoDAO = new InscricaoDAO();
		if (atualiza)
		{
			inscricaoDAO.indicaPresencaProvaEscrita(idInscricao, codigoProva);
		}
		else
		{
			inscricaoDAO.indicaAusenciaProvaEscrita(idInscricao, codigoProva);
		}
	}
}
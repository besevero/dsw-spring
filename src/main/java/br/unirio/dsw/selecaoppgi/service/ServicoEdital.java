package br.unirio.dsw.selecaoppgi.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;

import br.unirio.dsw.selecaoppgi.model.edital.Edital;
import br.unirio.dsw.selecaoppgi.model.usuario.Usuario;
import br.unirio.dsw.selecaoppgi.service.dao.EditalDAO;
import br.unirio.dsw.selecaoppgi.service.dao.UsuarioDAO;

/**
 * Classe responsavel por retornar o edital selecionado
 * 
 * @author juliocampos
 */
public class ServicoEdital
{
	public static Edital pegaEditalSelecionado(HttpServletRequest request, EditalDAO editalDAO, UsuarioDAO userDAO)
	{
		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Edital edital = (Edital) request.getSession().getAttribute("edital");
		
		if ((edital == null || edital.getId() != usuario.getIdEdital()) && usuario.getIdEdital() > 0)
		{
			edital = editalDAO.carregaEditalId(usuario.getIdEdital(), userDAO);
			request.getSession().setAttribute("edital", edital);
		}
		
		return edital;
	}
}
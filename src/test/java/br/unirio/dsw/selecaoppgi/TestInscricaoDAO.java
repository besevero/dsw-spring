package br.unirio.dsw.selecaoppgi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.unirio.dsw.selecaoppgi.controller.ProvaEscritaController;
import br.unirio.dsw.selecaoppgi.model.edital.Edital;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoEdital;
import br.unirio.dsw.selecaoppgi.service.dao.InscricaoDAO;


public class TestInscricaoDAO
{
	private InscricaoDAO inscricaoDAO;
	

	@Before
	public void setup()
	{
	
	}
	
<<<<<<< HEAD
	@Test//(expected = NullPointerException.class)
	public void TestmarcaAprovadoProvasEscritas()
	{
	  //try {
	    assertTrue(inscricaoDAO.marcaAprovadoProvasEscritas(1));
	  /*}
	  catch(Exception e) {
	    e.getMessage();
	  }*/

	}

	@Test
	public void testmarcaReprovadoProvasEscritas()
	{
		assertTrue(inscricaoDAO.marcaReprovadoProvasEscritas(6) == true);
	}
	
	@Test
	public void testCalculaNotaDaProvaEscrita() {
	  ProvaEscritaController a = new ProvaEscritaController();
	  Edital edital = null;
	  InscricaoEdital candidato = new InscricaoEdital(edital);
	  a.CalculaNotaDaProvaEscrita(candidato);//retorna 76 de média 
	}

=======
	@Test
	public void testmarcaAprovadoProvasEscritas() throws Exception
	{
		int idInscricao = 1;
		try
		{
			assertTrue(inscricaoDAO.marcaAprovadoProvasEscritas(idInscricao));
			
		}
		catch (Exception e)
		{
			fail ("Ops! Deveria ter ocorrido uma exceção");
		}
	}
>>>>>>> a8bc9f9870215217301888e145206cfc10b5d61a
	
}
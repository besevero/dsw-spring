package br.unirio.dsw.selecaoppgi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.unirio.dsw.selecaoppgi.service.dao.InscricaoDAO;


public class TestInscricaoDAO
{
	private InscricaoDAO inscricaoDAO;
	

	@Before
	public void setup()
	{
	
	}
	
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
	
}
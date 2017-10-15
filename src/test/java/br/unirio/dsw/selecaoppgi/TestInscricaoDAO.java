package br.unirio.dsw.selecaoppgi;

import static org.junit.Assert.assertTrue;

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
	public void TestmarcaAprovadoProvasEscritas()
	{
		assertTrue(inscricaoDAO.marcaAprovadoProvasEscritas(5) == true);
	}

	@Test
	public void testmarcaReprovadoProvasEscritas()
	{
		assertTrue(inscricaoDAO.marcaReprovadoProvasEscritas(6) == true);
	}

	
}
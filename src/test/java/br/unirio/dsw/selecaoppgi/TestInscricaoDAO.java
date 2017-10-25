package br.unirio.dsw.selecaoppgi;

import static org.junit.Assert.*;

import org.junit.Test;

import br.unirio.dsw.selecaoppgi.controller.ProvaEscritaController;
import br.unirio.dsw.selecaoppgi.model.edital.Edital;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoEdital;

public class TestInscricaoDAO
{

	@Test
	public void test()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testCalculaNotaDaProvaEscrita()
	{
		ProvaEscritaController a = new ProvaEscritaController();
		Edital edital = null;
		InscricaoEdital candidato = new InscricaoEdital(edital);
		a.CalculaNotaDaProvaEscrita(candidato); // retorna 76 de média
	}
}

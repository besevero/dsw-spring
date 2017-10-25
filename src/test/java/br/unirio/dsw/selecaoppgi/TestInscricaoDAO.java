package br.unirio.dsw.selecaoppgi;

import static org.junit.Assert.*;

import org.junit.Test;

import br.unirio.dsw.selecaoppgi.controller.ProvaEscritaController;
import br.unirio.dsw.selecaoppgi.model.edital.Edital;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoEdital;

<<<<<<< HEAD
public class TestInscricaoDAO {
	private InscricaoDAO inscricaoDAO;

	/*@Before
	public void setup()
	{
	
	}
	

	@Test//(expected = NullPointerException.class)
	public void TestmarcaAprovadoProvasEscritas()
	{
	  //try {
	    assertTrue(inscricaoDAO.marcaAprovadoProvasEscritas(1));
	  /*}
	  catch(Exception e) {
	    e.getMessage();
	  }*/
	public void setup() {

	}
=======
public class TestInscricaoDAO
{
>>>>>>> 15635db5675b1d913202487a6cf50df670efa0e8

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
<<<<<<< HEAD
		a.CalculaNotaDaProvaEscrita(candidato);// retorna 76 de média apenas
	}

	@Test
	public void testmarcaAprovadoProvasEscritas() throws Exception {
		int idInscricao = 1;
		try {
			assertTrue(inscricaoDAO.marcaAprovadoProvasEscritas(idInscricao));

		} catch (Exception e) {
			fail("Ops! Deveria ter ocorrido uma exceção");
		}
	}

}
=======
		a.CalculaNotaDaProvaEscrita(candidato); // retorna 76 de média
	}
}
>>>>>>> 15635db5675b1d913202487a6cf50df670efa0e8

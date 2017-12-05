package br.unirio.dsw.selecaoppgi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;

import br.unirio.dsw.selecaoppgi.model.edital.Edital;
import br.unirio.dsw.selecaoppgi.model.inscricao.AvaliacaoProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoEdital;
import br.unirio.dsw.selecaoppgi.service.ServicoEdital;
import br.unirio.dsw.selecaoppgi.service.ServicoInscricao;
import br.unirio.dsw.selecaoppgi.service.dao.EditalDAO;
import br.unirio.dsw.selecaoppgi.service.dao.InscricaoDAO;
import br.unirio.dsw.selecaoppgi.service.dao.UsuarioDAO;
import br.unirio.dsw.selecaoppgi.service.json.JsonInscricaoWriter;

@Controller
public class ProvaEscritaController
{
	@Autowired
	private UsuarioDAO userDAO;

	@Autowired
	private EditalDAO editalDAO;

	@Autowired
	private InscricaoDAO inscricaoDAO;

	/**
	 * Ação AJAX que atualiza o status de presença de um candidato em uma prova
	 * escrita
	 */
	@ResponseBody
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/edital/escrita/presenca/atualiza", method = RequestMethod.POST)
	public boolean atualizaPresencaProvaEscrita(HttpServletRequest request, @ModelAttribute("code") String codigoProva,
			@ModelAttribute("id") int idInscricao, @ModelAttribute("status") boolean status)
	{
		return ServicoInscricao.atualizaPresencaCandidato(request, codigoProva, idInscricao, status);
	}

	/**
	 * Ação AJAX que atualiza o status de presença de um candidato em uma prova oral
	 */
	@ResponseBody
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/edital/escrita/alinhamento/atualiza", method = RequestMethod.POST)
	public boolean atualizaPresencaProvaOral(HttpServletRequest request,
			@ModelAttribute("code") String codigoProjetoPesquisa, @ModelAttribute("id") int idInscricao,
			@ModelAttribute("status") boolean status)
	{
		return ServicoInscricao.atualizaPresencaCandidato(request, codigoProjetoPesquisa, idInscricao, status);
	}

	/**
	 * Ação AJAX que lista todas as inscrições de candidatos que podem fazer provas
	 * escritas de um edital
	 */
	@ResponseBody
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/edital/escrita/presenca", method = RequestMethod.GET, produces = "application/json")
	public String listaProvasEscritas(HttpServletRequest request, @ModelAttribute("code") String codigoProva)
	{
		Edital editalSelecionado = ServicoEdital.pegaEditalSelecionado(request, editalDAO, userDAO);
		List<InscricaoEdital> lista = inscricaoDAO.carregaPresencaProvaEscrita(editalSelecionado, codigoProva);

		JsonInscricaoWriter writer = new JsonInscricaoWriter();
		JsonArray jsonInscricoesEdital = new JsonArray();

		for (InscricaoEdital inscricao : lista)
			jsonInscricoesEdital.add(writer.execute(inscricao));

		return jsonInscricoesEdital.toString();
	}

	/**
	 * Ação AJAX que lista todas as inscrições de candidatos que podem fazer provas
	 * orais de um edital
	 */
	@ResponseBody
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/edital/alinhamento/presenca", method = RequestMethod.GET, produces = "application/json")
	public String listaProvasOrais(HttpServletRequest request, @ModelAttribute("code") String codigoProjetoPesquisa)
	{
		Edital editalSelecionado = ServicoEdital.pegaEditalSelecionado(request, editalDAO, userDAO);
		List<InscricaoEdital> lista = inscricaoDAO.carregaPresencaProvaOral(editalSelecionado, codigoProjetoPesquisa);

		JsonInscricaoWriter writer = new JsonInscricaoWriter();
		JsonArray jsonInscricoesEdital = new JsonArray();

		for (InscricaoEdital inscricao : lista)
			jsonInscricoesEdital.add(writer.execute(inscricao));

		return jsonInscricoesEdital.toString();
	}

	/**
	 * Ação que redireciona o usuário para a tela de presença em prova escrita
	 */
	@RequestMapping(value = "/edital/escrita/presenca", method = RequestMethod.GET)
	public ModelAndView mostraPaginaPresencaProvaEscrita()
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("edital/escrita/presenca");
		return model;
	}

	/**
	 * Ação que redireciona o usuário para a tela de presença em prova oral
	 */
	@RequestMapping(value = "/edital/alinhamento/presenca", method = RequestMethod.GET)
	public ModelAndView mostraPaginaPresencaProvaOral()
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("edital/alinhamento/presenca");
		return model;
	}

	/**
	 * Ação do encerramento de nota da prova escrita
	 */
	@ResponseBody
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/edital/escrita/encerramento", method = RequestMethod.GET)
	public ModelAndView encerramento(HttpServletRequest request)
	{
		ModelAndView model = new ModelAndView();

		Edital editalSelecionado = ServicoEdital.pegaEditalSelecionado(request, editalDAO, userDAO);
		List<InscricaoEdital> lista = inscricaoDAO.carregaInscricoesEdital(editalSelecionado);
		List<String> resultado = VerificaCandidatosComPendenciaNasProvas(lista);

		model.getModel().put("candidatos", resultado);
		model.setViewName("/edital/escrita/encerramento");

		return model;
	}

	/**
	 * Função que verifica quais candidatos possuem pendências e calcula a nota da
	 * prova escrita caso não haja pendências
	 */
	public List<String> VerificaCandidatosComPendenciaNasProvas(List<InscricaoEdital> lista)
	{
		List<String> pendencias = new ArrayList<String>();

		for (InscricaoEdital candidato : lista)
		{
			if (candidato.getHomologado() == true)
			{
				ArrayList<String> listaNomesProvas = verificaSeEstaComTodasAsNotas(candidato);
				for (String nome : listaNomesProvas)
				{
					if (!nome.equals(""))
					{
						String pendenciaFormatada = "O candidato " + candidato.getNomeCandidato()
								+ " está sem nota na prova " + nome;
						pendencias.add(pendenciaFormatada);
					}

				}
			}

		}

		return pendencias;
	}

	/**
	 * Função que verifica se todas as provas escritas possuem notas
	 */
	public ArrayList<String> verificaSeEstaComTodasAsNotas(InscricaoEdital candidato)
	{

		Iterable<AvaliacaoProvaEscrita> listaAvaliacoes = candidato.getAvaliacoesProvasEscritas();
		ArrayList<String> nomeProva = new ArrayList<String>();

		for (AvaliacaoProvaEscrita prova : listaAvaliacoes)
		{
			int indiceQuestao = 0;

			boolean estaHomologado = candidato.getHomologado() == true;
			boolean possuiNotaOriginalQuestao = prova.possuiNotaOriginalQuestao(indiceQuestao);
			boolean possuiNotaOriginalRecurso = prova.possuiNotaRecursoQuestao(indiceQuestao);

			if (estaHomologado && possuiNotaOriginalQuestao || possuiNotaOriginalRecurso)
			{
			} else
			{
				nomeProva.add(prova.getProvaEscrita().getNome());
			}
		}
		return nomeProva;
	}

	/**
	 * Função que calcula a nota de uma prova escrita
	 */
	public void CalculaNotaDaProvaEscrita(InscricaoEdital candidato)
	{
		Iterable<AvaliacaoProvaEscrita> listaAvaliacoes = candidato.getAvaliacoesProvasEscritas();

		for (AvaliacaoProvaEscrita prova : listaAvaliacoes)
		{
			int indiceQuestao = 0;
			int somatorio = 0;
			int somaPesos = 0;
			int media = 0;
			if (!prova.getProvaEscrita().isDispensavel())
			{
				while (indiceQuestao < prova.getProvaEscrita().contaQuestoes())
				{
					int pegaPesoQuestao = prova.getProvaEscrita().pegaPesoQuestaoIndice(indiceQuestao);
					if (prova.possuiNotaRecursoQuestao(indiceQuestao))
					{
						somatorio = somatorio + (prova.getNotaRecursoQuestao(indiceQuestao) * pegaPesoQuestao);
						somaPesos = somaPesos + pegaPesoQuestao;
						media = somatorio / somaPesos;

					} else
					{
						somatorio = somatorio + (prova.getNotaOriginalQuestao(indiceQuestao) * pegaPesoQuestao);
						somaPesos = somaPesos + pegaPesoQuestao;

					}

					indiceQuestao++;
				}
				media = somatorio / somaPesos;
				inscricaoDAO.atualizaMediaProvaFinal(media, candidato.getId());
			}
		}
	}

	/**
	 * Ação AJAX que apresenta o formulário de edição de um edital
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/edital/escrita/encerramento/confirma", method = RequestMethod.GET)
	public void atualizaPresenca(HttpServletRequest request)
	{
		// pega o edital da sessão atual
		Edital editalSelecionado = ServicoEdital.pegaEditalSelecionado(request, editalDAO, userDAO);
		// realiza verificação das regras de negócio novamente
		List<InscricaoEdital> lista = inscricaoDAO.carregaInscricoesEdital(editalSelecionado);

		for (InscricaoEdital candidato : lista)
		{
			if (confirmaEncerramentoCandidato(candidato))
				CalculaNotaDaProvaEscrita(candidato);
		}
		// atualiza a status do edital para 4 Em provas de alinhamento
		inscricaoDAO.atualizaStatusEdital(editalSelecionado.getId());
	}

	/**
	 * Função que realiza verificações de regras após confirmação de encerramento de
	 * provas escritas
	 */
	public boolean confirmaEncerramentoCandidato(InscricaoEdital candidato)
	{
		Iterable<AvaliacaoProvaEscrita> listaAvaliacoes = candidato.getAvaliacoesProvasEscritas();

		for (AvaliacaoProvaEscrita prova : listaAvaliacoes)
		{
			int indiceQuestao = 0;

			boolean estaHomologado = candidato.getHomologado() == true;
			boolean possuiNotaOriginalQuestao = prova.possuiNotaOriginalQuestao(indiceQuestao);
			boolean possuiNotaOriginalRecurso = prova.possuiNotaRecursoQuestao(indiceQuestao);

			if (estaHomologado && possuiNotaOriginalQuestao || possuiNotaOriginalRecurso)
			{
				return true;
			} else
			{
				return false;
			}
		}
		return true;
	}
}
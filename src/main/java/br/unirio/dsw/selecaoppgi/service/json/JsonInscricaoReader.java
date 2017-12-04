package br.unirio.dsw.selecaoppgi.service.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.unirio.dsw.selecaoppgi.model.edital.CriterioAlinhamento;
import br.unirio.dsw.selecaoppgi.model.edital.Edital;
import br.unirio.dsw.selecaoppgi.model.edital.ProjetoPesquisa;
import br.unirio.dsw.selecaoppgi.model.edital.ProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.edital.SubcriterioAlinhamento;
import br.unirio.dsw.selecaoppgi.model.inscricao.AvaliacaoProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoEdital;
import br.unirio.dsw.selecaoppgi.model.usuario.Usuario;
import br.unirio.dsw.selecaoppgi.service.dao.UsuarioDAO;


public class JsonInscricaoReader
{
	/**
	 * Carrega uma inscrição a partir da representação JSON
	 */
	public InscricaoEdital execute(JsonObject json, Edital edital)
	{
		UsuarioDAO userDAO = new UsuarioDAO();
		InscricaoEdital inscricao = new InscricaoEdital(edital);

		int id = json.get("id").getAsInt();
		inscricao.setId(id);

		int idCandidato = json.get("idCandidato").getAsInt();
		inscricao.setIdCandidato(idCandidato);

		String nomeCandidato = json.get("nomeCandidato").getAsString();
		inscricao.setNomeCandidato(nomeCandidato);

		Boolean cotaNegros = json.get("cotaNegros").getAsBoolean();
		inscricao.setCotaNegros(cotaNegros);

		Boolean cotaDeficientes = json.get("cotaDeficientes").getAsBoolean();
		inscricao.setCotaDeficientes(cotaDeficientes);

		Boolean homologadoOriginal = json.get("homologadoOriginal").getAsBoolean();
		inscricao.setHomologadoOriginal(homologadoOriginal);

		String justificativaHomologacaoOriginal = json.get("justificativaHomologacaoOriginal").getAsString();
		inscricao.setJustificativaHomologacaoOriginal(justificativaHomologacaoOriginal);

		Boolean homologadoRecurso = json.get("homologadoRecurso").getAsBoolean();
		inscricao.setHomologadoRecurso(homologadoRecurso);

		String justificativaHomologacaoRecurso = json.get("justificativaHomologacaoRecurso").getAsString();
		inscricao.setJustificativaHomologacaoRecurso(justificativaHomologacaoRecurso);

		Boolean dispensadoProvaOriginal = json.get("dispensadoProvaOriginal").getAsBoolean();
		inscricao.setDispensadoProvaOriginal(dispensadoProvaOriginal);

		String justificativaDispensaOriginal = json.get("justificativaDispensaOriginal").getAsString();
		inscricao.setJustificativaDispensaOriginal(justificativaDispensaOriginal);

		Boolean dispensadoProvaRecurso = json.get("dispensadoProvaRecurso").getAsBoolean();
		inscricao.setDispensadoProvaRecurso(dispensadoProvaRecurso);

		String justificativaDispensaRecurso = json.get("justificativaDispensaRecurso").getAsString();
		inscricao.setJustificativaDispensaRecurso(justificativaDispensaRecurso);

		carregaProvasEscritas(json, inscricao);
		
		carregaRepresentacaoProjetosPesquisa(json, edital, userDAO);
		carregaRepresentacaoCriteriosAlinhamento(json, edital);
		
		return inscricao;
	}

	/**
	 * Carrega uma prova escrita a partir da representação Json
	 */
	private void carregaProvasEscritas(JsonObject json, InscricaoEdital inscricao)
	{
		JsonArray jsonProvasEscritas = json.getAsJsonArray("provasEscritas");

		if (jsonProvasEscritas == null)
			return;

		JsonQuestoesReader leitorQuestoes = new JsonQuestoesReader();

		for (int i = 0; i < jsonProvasEscritas.size(); i++)
		{
			JsonObject jsonProva = jsonProvasEscritas.get(i).getAsJsonObject();
			
			String codigo = jsonProva.get("codigo").getAsString();
			ProvaEscrita prova = inscricao.getEdital().pegaProvaEscritaCodigo(codigo);
			
			if (prova != null)
			{
				AvaliacaoProvaEscrita avaliacao = inscricao.pegaAvaliacaoProvaEscrita(prova);

				if (avaliacao != null)
				{
					
					boolean presente = jsonProva.get("presente").getAsBoolean();
					avaliacao.setPresente(presente);

					String codigoProvaEscrita = jsonProva.get("codigoProvaEscrita").getAsString();
					avaliacao.getProvaEscrita().setCodigo(codigoProvaEscrita);
					
					String nomeProvaEscrita = jsonProva.get("nomeProvaEscrita").getAsString();
					avaliacao.getProvaEscrita().setNome(nomeProvaEscrita);
					
					JsonArray jsonNotaOriginal = jsonProva.get("notaOriginalQuestao").getAsJsonArray();
					leitorQuestoes.carregaNotasIniciais(jsonNotaOriginal, avaliacao);
					
					JsonArray jsonNotaRecurso = jsonProva.get("notaRecursoQuestao").getAsJsonArray();
					leitorQuestoes.carregaNotasRecurso(jsonNotaRecurso, avaliacao);
				}
			}
		}
	}
	
	/**
	 * Carrega a lista de projetos de pesquisa a partir da representação JSON
	 */
	private void carregaRepresentacaoProjetosPesquisa(JsonObject json, Edital edital, UsuarioDAO userDAO)
	{
		JsonArray jsonProjetos = json.getAsJsonArray("projetosPesquisa");
		
		if (jsonProjetos == null)
			return;
		
		for (int i = 0; i < jsonProjetos.size(); i++)
		{
			JsonObject jsonProjeto = jsonProjetos.get(i).getAsJsonObject();
			ProjetoPesquisa projeto = carregaRepresentacaoProjetoPesquisa(jsonProjeto, edital, userDAO);
			edital.adicionaProjetoPesquisa(projeto);
		}
	}

	/**
	 * Carrega um projeto de pesquisa a partir da representação JSON
	 */
	private ProjetoPesquisa carregaRepresentacaoProjetoPesquisa(JsonObject json, Edital edital, UsuarioDAO userDAO)
	{
		ProjetoPesquisa projeto = new ProjetoPesquisa();
		projeto.setCodigo(json.get("codigo").getAsString());
		projeto.setNome(json.get("nome").getAsString());
		projeto.setExigeProvaOral(json.get("exigeProvaOral").getAsBoolean());
		carregaRepresentacaoProfessoresProjetoPesquisa(json, projeto, userDAO);
		carregaRepresentacaoProvasEscritasProjetoPesquisa(json, projeto, edital);
		return projeto;
	}

	/**
	 * Carrega a lista de professores de um projeto de pesquisa a partir da representação JSON
	 */
	private void carregaRepresentacaoProfessoresProjetoPesquisa(JsonObject json, ProjetoPesquisa projeto, UsuarioDAO userDAO)
	{
		JsonArray jsonProfessores = json.getAsJsonArray("professores");
		
		if (jsonProfessores == null)
			return;
		
		for (int i = 0; i < jsonProfessores.size(); i++)
		{
			JsonObject jsonProfessor = jsonProfessores.get(i).getAsJsonObject();
			int id = jsonProfessor.get("id").getAsInt();
			Usuario professor = userDAO.carregaUsuarioId(id);
			
			if (professor != null)
				projeto.adicionaProfessor(professor);
		}
	}

	/**
	 * Carrega a lista de provas escritas a partir da representação JSON
	 */
	private void carregaRepresentacaoProvasEscritasProjetoPesquisa(JsonObject json, ProjetoPesquisa projeto, Edital edital)
	{
		JsonArray jsonProvas = json.getAsJsonArray("provasEscritas");
		
		for (int i = 0; i < jsonProvas.size(); i++)
		{
			String codigo = jsonProvas.get(i).getAsString();
			ProvaEscrita prova = edital.pegaProvaEscritaCodigo(codigo);
			
			if (prova != null)
				projeto.adicionaProvaEscrita(prova);
		}
	}

	/**
	 * Carrega a lista de critérios de alinhamento a partir da representação JSON
	 */
	private void carregaRepresentacaoCriteriosAlinhamento(JsonObject json, Edital edital)
	{
		JsonArray jsonCriterios = json.getAsJsonArray("criteriosAlinhamento");
		
		if (jsonCriterios == null)
			return;
		
		for (int i = 0; i < jsonCriterios.size(); i++)
		{
			JsonObject jsonCriterio = jsonCriterios.get(i).getAsJsonObject();
			CriterioAlinhamento criterio = carregaRepresentacaoCriterioAlinhamento(jsonCriterio);
			edital.adicionaCriterioAlinhamento(criterio);
		}
	}

	/**
	 * Carrega um criterio de alinhamento de pesquisa a partir da representação JSON
	 */
	private CriterioAlinhamento carregaRepresentacaoCriterioAlinhamento(JsonObject json)
	{
		CriterioAlinhamento criterio = new CriterioAlinhamento();
		criterio.setCodigo(json.get("codigo").getAsString());
		criterio.setNome(json.get("nome").getAsString());
		criterio.setPesoComProvaOral(json.get("pesoComProvaOral").getAsInt());
		criterio.setPesoSemProvaOral(json.get("pesoSemProvaOral").getAsInt());
		criterio.setPertenceProvaOral(json.get("pertenceProvaOral").getAsBoolean());
		
		JsonArray jsonSubcriterios = json.getAsJsonArray("subcriterios");
		
		for (int i = 0; i < jsonSubcriterios.size(); i++)
		{
			JsonObject jsonSubcriterio = jsonSubcriterios.get(i).getAsJsonObject();
			SubcriterioAlinhamento subcriterio = carregaRepresentacaoSubcriterioAlinhamento(jsonSubcriterio); 
			criterio.adicionaSubcriterio(subcriterio);
		}
		
		return criterio;
	}

	/**
	 * Carrega um subcriterio de alinhamento de pesquisa a partir da representação JSON
	 */
	private SubcriterioAlinhamento carregaRepresentacaoSubcriterioAlinhamento(JsonObject json)
	{
		SubcriterioAlinhamento subcriterio = new SubcriterioAlinhamento();
		subcriterio.setCodigo(json.get("codigo").getAsString());
		subcriterio.setNome(json.get("nome").getAsString());
		subcriterio.setDescricao(json.get("descricao").getAsString());
		subcriterio.setPeso(json.get("peso").getAsInt());
		return subcriterio;
	}
	
}
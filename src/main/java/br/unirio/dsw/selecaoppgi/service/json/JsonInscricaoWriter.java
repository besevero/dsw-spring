package br.unirio.dsw.selecaoppgi.service.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import br.unirio.dsw.selecaoppgi.model.edital.CriterioAlinhamento;
import br.unirio.dsw.selecaoppgi.model.edital.Edital;
import br.unirio.dsw.selecaoppgi.model.edital.ProjetoPesquisa;
import br.unirio.dsw.selecaoppgi.model.edital.ProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.edital.SubcriterioAlinhamento;
import br.unirio.dsw.selecaoppgi.model.inscricao.AvaliacaoCriterioAlinhamento;
import br.unirio.dsw.selecaoppgi.model.inscricao.AvaliacaoProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoEdital;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoProjetoPesquisa;
import br.unirio.dsw.selecaoppgi.model.usuario.Usuario;

public class JsonInscricaoWriter
{
	/**
	 * Gera a representação Json
	 */
	public JsonObject execute(InscricaoEdital inscricaoEdital)
	{
		JsonObject json = new JsonObject();

		json.addProperty("id", inscricaoEdital.getId());
		json.addProperty("idCandidato", inscricaoEdital.getIdCandidato());
		json.addProperty("nomeCandidato", inscricaoEdital.getNomeCandidato());
		json.addProperty("cotaNegros", inscricaoEdital.isCotaNegros());
		json.addProperty("cotaDeficientes", inscricaoEdital.isCotaDeficientes());
		json.addProperty("homolodadoOriginal", inscricaoEdital.getHomologadoOriginal());

		if (inscricaoEdital.getJustificativaHomologacaoOriginal() != null)
			json.addProperty("justificativaHomologacaoOriginal", inscricaoEdital.getJustificativaHomologacaoOriginal());

		json.addProperty("homologadoRecurso", inscricaoEdital.getHomologadoRecurso());
		
		if (inscricaoEdital.getJustificativaHomologacaoRecurso() != null)
			json.addProperty("justificativaHomologacaoRecurso", inscricaoEdital.getJustificativaHomologacaoRecurso());		
		
		json.addProperty("dispensadoProvaOriginal", inscricaoEdital.getDispensadoProvaOriginal());

		if (inscricaoEdital.getJustificativaDispensaOriginal() != null)
			json.addProperty("justificativaDispensaOriginal", inscricaoEdital.getJustificativaDispensaOriginal());
		
		json.addProperty("dispensadoProvaRecurso", inscricaoEdital.getDispensadoProvaRecurso());
		
		if (inscricaoEdital.getJustificativaDispensaRecurso() != null)
			json.addProperty("justificativaDispensaRecurso", inscricaoEdital.getJustificativaDispensaRecurso());

		JsonArray jsonProvas = geraRepresentacaoAvaliacaoProvasEscritas(inscricaoEdital);

		if (jsonProvas.size() > 0)
			json.add("provasEscritas", jsonProvas);
		
		JsonArray jsonProjetos = geraRepresentacaoProjetosPesquisa(inscricaoEdital.getEdital());

		if (jsonProjetos.size() > 0)
			json.add("projetosPesquisa", jsonProjetos);
		
		JsonArray jsonCriterios = geraRepresentacaoCriteriosAlinhamento(inscricaoEdital.getEdital());

		if (jsonCriterios.size() > 0)
			json.add("criteriosAlinhamento", jsonCriterios);
		
		JsonArray jsonInscricaoProjetoPesquisa = geraRepresentacaoInscricaoProjetoPesquisa(inscricaoEdital.getInscricoesProjetoPesquisa());
		if(jsonInscricaoProjetoPesquisa.size() > 0)
			json.add("inscricaoProjetoPesquisa", jsonInscricaoProjetoPesquisa);

		return json;
	}

	private JsonArray geraRepresentacaoInscricaoProjetoPesquisa(
			Iterable<InscricaoProjetoPesquisa> inscricoesProjetoPesquisa) {
			JsonArray jsonProjetoPesquisa = new JsonArray();
		
		for(InscricaoProjetoPesquisa projeto: inscricoesProjetoPesquisa) {
			jsonProjetoPesquisa.add(carregaAvaliacaoCriterioAlinhamento(projeto));
		}
		
		
		return jsonProjetoPesquisa ;
	}
	private JsonArray carregaAvaliacaoCriterioAlinhamento(InscricaoProjetoPesquisa projeto) {
		JsonArray jsonAvaliacao = new JsonArray();
		for(AvaliacaoCriterioAlinhamento avaliacao : projeto.getAvaliacoesCriterioAlinhamento()) {
			JsonObject json = new JsonObject();
			json.addProperty("presencaProvaOral", avaliacao.getPresenteProvaOral());
			jsonAvaliacao.add(json);
	}
		return jsonAvaliacao;
}

	/**
	 * Gera a representação Json das avaliações de provas escritas
	 */
	private JsonArray geraRepresentacaoAvaliacaoProvasEscritas(InscricaoEdital inscricaoEdital)
	{
		JsonArray jsonProvas = new JsonArray();

		for (AvaliacaoProvaEscrita prova : inscricaoEdital.getAvaliacoesProvasEscritas())
			jsonProvas.add(geraRepresentacaoAvaliacaoProvaEscrita(prova));

		return jsonProvas;
	}

	/**
	 * Gera a representação Json de uma avaliação de prova escrita
	 */
	private JsonObject geraRepresentacaoAvaliacaoProvaEscrita(AvaliacaoProvaEscrita prova)
	{
		JsonObject json = new JsonObject();
				
		if(prova.getPresente() != null)
			json.addProperty("presenca", prova.getPresente());
		
		if(prova.getProvaEscrita().getCodigo() != null)
			json.addProperty("codigoProvaEscrita", prova.getProvaEscrita().getCodigo());		

		if(prova.getProvaEscrita().getNome() != null)
			json.addProperty("nomeProvaEscrita", prova.getProvaEscrita().getNome());

		JsonQuestoesWritter questoes = new JsonQuestoesWritter();
		
		json.add("notaOriginalQuestao", questoes.salvaNotasIniciais(prova));
		json.add("notaRecursoQuestao", questoes.salvaNotasRecurso(prova));

		return json;
	}
	
	/**
	 * Gera a representação JSON da lista de projetos de pesquisa
	 */
	private JsonArray geraRepresentacaoProjetosPesquisa(Edital edital)
	{
		JsonArray jsonProjetos = new JsonArray();
		
		for (ProjetoPesquisa projeto : edital.getProjetosPesquisa())
			jsonProjetos.add(geraRepresentacaoProjetoPesquisa(projeto));
		
		return jsonProjetos;
	}
	
	/**
	 * Gera a representação JSON de um projeto de pesquisa
	 */
	private JsonObject geraRepresentacaoProjetoPesquisa(ProjetoPesquisa projeto)
	{
		JsonObject json = new JsonObject();
		json.addProperty("codigo", projeto.getCodigo());
		json.addProperty("nome", projeto.getNome());
		json.addProperty("exigeProvaOral", projeto.isExigeProvaOral());
		json.add("professores", geraRepresentacaoProfessoresProjetoPesquisa(projeto));
		json.add("provasEscritas", geraRepresentacaoProvasEscritasProjetoPesquisa(projeto));
		return json;
	}

	/**
	 * Gera a representação JSON da lista de professores
	 */
	private JsonArray geraRepresentacaoProfessoresProjetoPesquisa(ProjetoPesquisa projeto)
	{
		JsonArray jsonProfessores = new JsonArray();
		
		for (Usuario professor : projeto.getProfessores())
		{
			JsonObject jsonProfessor = new JsonObject();
			jsonProfessor.addProperty("id", professor.getId());
			jsonProfessor.addProperty("nome", professor.getNome());
			jsonProfessores.add(jsonProfessor);
		}
		
		return jsonProfessores;
	}

	/**
	 * Gera a representação JSON da lista de provas escritas
	 */
	private JsonArray geraRepresentacaoProvasEscritasProjetoPesquisa(ProjetoPesquisa projeto)
	{
		JsonArray jsonProvas = new JsonArray();
		
		for (ProvaEscrita prova : projeto.getProvasEscritas())
			jsonProvas.add(new JsonPrimitive(prova.getCodigo()));
		
		return jsonProvas;
	}

	/**
	 * Gera a representação JSON da lista de projetos de pesquisa
	 */
	private JsonArray geraRepresentacaoCriteriosAlinhamento(Edital edital)
	{
		JsonArray jsonCriterios = new JsonArray();
		
		for (CriterioAlinhamento criterio : edital.getCriteriosAlinhamento())
			jsonCriterios.add(geraRepresentacaoCriterioAlinhamento(criterio));
		
		return jsonCriterios;
	}

	/**
	 * Gera a representação JSON de um critério de alinhamento
	 */
	private JsonObject geraRepresentacaoCriterioAlinhamento(CriterioAlinhamento criterio)
	{
		JsonObject json = new JsonObject();
		json.addProperty("codigo", criterio.getCodigo());
		json.addProperty("nome", criterio.getNome());
		json.addProperty("pesoComProvaOral", criterio.getPesoComProvaOral());
		json.addProperty("pesoSemProvaOral", criterio.getPesoSemProvaOral());
		json.addProperty("pertenceProvaOral", criterio.isPertenceProvaOral());

		JsonArray jsonSubcriterios = new JsonArray();
		
		for (SubcriterioAlinhamento subcriterio : criterio.getSubcriterios())
			jsonSubcriterios.add(geraRepresentacaoSubcriterioAlinhamento(subcriterio));

		json.add("subcriterios", jsonSubcriterios);
		return json;
	}

	/**
	 * Gera a representação JSON de um subcritério de avaliação de alinhamento
	 */
	private JsonObject geraRepresentacaoSubcriterioAlinhamento(SubcriterioAlinhamento subcriterio)
	{
		JsonObject json = new JsonObject();
		json.addProperty("codigo", subcriterio.getCodigo());
		json.addProperty("nome", subcriterio.getNome());
		json.addProperty("descricao", subcriterio.getDescricao());
		json.addProperty("peso", subcriterio.getPeso());
		return json;
	}
}
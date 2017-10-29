package br.unirio.dsw.selecaoppgi.service.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import br.unirio.dsw.selecaoppgi.model.inscricao.AvaliacaoProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoEdital;

public class JsonInscricaoWritter
{

	/*
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
		json.addProperty("justificativaHomologacaoOriginal", inscricaoEdital.getHomologadoOriginal());
		json.addProperty("homologadoRecurso", inscricaoEdital.getHomologadoRecurso());
		json.addProperty("justificativaHomologacaoRecurso", inscricaoEdital.getJustificativaHomologacaoRecurso());
		json.addProperty("dispensadoProvaOriginal", inscricaoEdital.getDispensadoProvaOriginal());
		json.addProperty("justificativaDispensaOriginal", inscricaoEdital.getJustificativaDispensaOriginal());
		json.addProperty("dispensadoProvaRecurso", inscricaoEdital.getDispensadoProvaRecurso());
		json.addProperty("justificativaDispensaRecurso", inscricaoEdital.getJustificativaDispensaRecurso());

		JsonArray jsonProvas = geraRepresentacaoAvaliacaoProvasEscritas(inscricaoEdital);

		if (jsonProvas.size() > 0)
			json.add("provasEscritas", jsonProvas);

		return json;
	}

	/*
	 * Gera Representação Json das Avaliações de Prova Escrita
	 */
	private JsonArray geraRepresentacaoAvaliacaoProvasEscritas(InscricaoEdital inscricaoEdital)
	{
		JsonArray jsonProvas = new JsonArray();

		for (AvaliacaoProvaEscrita prova : inscricaoEdital.getAvaliacoesProvasEscritas())
			jsonProvas.add(geraRepresentacaoAvaliacaoProvaEscrita(prova));

		return jsonProvas;
	}

	/*
	 * Gera representação de uma avaliação de prova escrita
	 */
	private JsonObject geraRepresentacaoAvaliacaoProvaEscrita(AvaliacaoProvaEscrita prova)
	{
		JsonObject json = new JsonObject();
		json.addProperty("presenca", prova.getPresente());

		JsonQuestoesWritter questoes = new JsonQuestoesWritter();
		json.add("notaOriginalQuestao", questoes.salvaNotasIniciais(prova));
		json.add("notaRecursoQuestao", questoes.salvaNotasRecurso(prova));

		return json;
	}

}

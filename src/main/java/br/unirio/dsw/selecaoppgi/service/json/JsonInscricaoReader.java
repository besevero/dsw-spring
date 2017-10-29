package br.unirio.dsw.selecaoppgi.service.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.unirio.dsw.selecaoppgi.model.inscricao.AvaliacaoProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoEdital;

public class JsonInscricaoReader
{
	/**
	 * Carrega uma inscrição a partir da representação JSON
	 */
	public InscricaoEdital execute(JsonObject json, InscricaoEdital inscricaoEdital)
	{
		inscricaoEdital.setId(json.get("id").getAsInt());
		inscricaoEdital.setIdCandidato(json.get("idCandidato").getAsInt());
		inscricaoEdital.setNomeCandidato(json.get("nomeCandidato").getAsString());
		inscricaoEdital.setCotaNegros(json.get("cotaNegros").getAsBoolean());
		inscricaoEdital.setCotaDeficientes(json.get("cotaDeficientes").getAsBoolean());
		inscricaoEdital.setHomologadoOriginal(json.get("homologadoOriginal").getAsBoolean());
		inscricaoEdital.setJustificativaHomologacaoRecurso(json.get("justificativaHomologacaoOriginal").getAsString());
		inscricaoEdital.setHomologadoRecurso(json.get("homologadoRecurso").getAsBoolean());
		inscricaoEdital.setJustificativaHomologacaoRecurso(json.get("justificativaHomologacaoRecurso").getAsString());
		inscricaoEdital.setDispensadoProvaOriginal(json.get("dispensadoProvaOriginal").getAsBoolean());
		inscricaoEdital.setJustificativaDispensaOriginal(json.get("justificativaDispensaOriginal").getAsString());
		inscricaoEdital.setDispensadoProvaRecurso(json.get("dispensadoProvaRecurso").getAsBoolean());
		inscricaoEdital.setJustificativaDispensaRecurso(json.get("justificativaDispensaRecurso").getAsString());

		carregaProvasEscritas(json, inscricaoEdital);

		return null;
	}

	/**
	 * Carrega uma prova escrita a partir da representação Json
	 */
	private void carregaProvasEscritas(JsonObject json, InscricaoEdital inscricaoEdital)
	{
		JsonArray jsonProvasEscritas = json.getAsJsonArray("provasEscritas");
		JsonQuestoesReader leitorQuestoes = new JsonQuestoesReader();

		if (jsonProvasEscritas == null)
			return;

		for (int i = 0; i < jsonProvasEscritas.size(); i++)
		{
			JsonObject jsonProva = jsonProvasEscritas.get(i).getAsJsonObject();
			jsonProva.get("presente").getAsBoolean();
			carregaNotasQuestoesIniciais(jsonProva.get("notaOriginalQuestao").getAsJsonArray(),
					inscricaoEdital.pegaAvaliacaoProvaEscrita(i), leitorQuestoes);
			carregaNotasQuestoesRecurso(jsonProva.get("notaRecursoQuestao").getAsJsonArray(),
					inscricaoEdital.pegaAvaliacaoProvaEscrita(i), leitorQuestoes);
		}
	}

	private void carregaNotasQuestoesIniciais(JsonArray json, AvaliacaoProvaEscrita provaEscrita, JsonQuestoesReader leitorQuestoes)
	{
		leitorQuestoes.carregaNotasIniciais(json, provaEscrita);
	}

	private void carregaNotasQuestoesRecurso(JsonArray json, AvaliacaoProvaEscrita provaEscrita, JsonQuestoesReader leitorQuestoes)
	{
		leitorQuestoes.carregaNotasRecurso(json, provaEscrita);
	}
}

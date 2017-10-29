package br.unirio.dsw.selecaoppgi.service.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.unirio.dsw.selecaoppgi.model.edital.Edital;
import br.unirio.dsw.selecaoppgi.model.inscricao.AvaliacaoProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoEdital;
import br.unirio.dsw.selecaoppgi.service.dao.InscricaoDAO;

public class JsonInscricaoReader
{
	/**
	 * Carrega uma inscrição a partir da representação JSON
	 */

	public InscricaoEdital execute(JsonObject json, Edital edital, InscricaoDAO userDAO)
	{

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

		return inscricao;
	}

	/**
	 * Carrega uma prova escrita a partir da representação Json
	 */
	private void carregaProvasEscritas(JsonObject json, InscricaoEdital inscricao)
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
					inscricao.pegaAvaliacaoProvaEscrita(i), leitorQuestoes);
			carregaNotasQuestoesRecurso(jsonProva.get("notaRecursoQuestao").getAsJsonArray(),
					inscricao.pegaAvaliacaoProvaEscrita(i), leitorQuestoes);
		}
	}

	private void carregaNotasQuestoesIniciais(JsonArray json, AvaliacaoProvaEscrita provaEscrita,
			JsonQuestoesReader leitorQuestoes)
	{
		leitorQuestoes.carregaNotasIniciais(json, provaEscrita);
	}

	private void carregaNotasQuestoesRecurso(JsonArray json, AvaliacaoProvaEscrita provaEscrita,
			JsonQuestoesReader leitorQuestoes)
	{
		leitorQuestoes.carregaNotasRecurso(json, provaEscrita);
	}
}

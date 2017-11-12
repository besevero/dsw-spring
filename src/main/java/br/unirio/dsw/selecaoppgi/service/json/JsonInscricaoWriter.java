package br.unirio.dsw.selecaoppgi.service.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import br.unirio.dsw.selecaoppgi.model.inscricao.AvaliacaoProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoEdital;

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

		return json;
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
		if(prova.getProvaEscrita().getCodigo() != null)
			json.addProperty("codigoProvaEscrita", prova.getProvaEscrita().getCodigo());		
		if(prova.getProvaEscrita().getNome() != null)
			json.addProperty("codigoProvaEscrita", prova.getProvaEscrita().getNome());
		
		if(prova.getPresente() != null)
			json.addProperty("presenca", prova.getPresente());
		
		JsonQuestoesWritter questoes = new JsonQuestoesWritter();
		
		json.add("notaOriginalQuestao", questoes.salvaNotasIniciais(prova));
		json.add("notaRecursoQuestao", questoes.salvaNotasRecurso(prova));

		return json;
	}
}
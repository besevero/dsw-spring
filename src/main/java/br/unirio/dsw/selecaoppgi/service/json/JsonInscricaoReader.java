package br.unirio.dsw.selecaoppgi.service.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.unirio.dsw.selecaoppgi.model.edital.Edital;
import br.unirio.dsw.selecaoppgi.model.edital.ProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.AvaliacaoProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoEdital;


public class JsonInscricaoReader
{
	/**
	 * Carrega uma inscrição a partir da representação JSON
	 */
	public InscricaoEdital execute(JsonObject json, Edital edital)
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
					String codigoProvaEscrita = jsonProva.get("codigoProvaEscrita").getAsString();
					avaliacao.getProvaEscrita().setCodigo(codigoProvaEscrita);
					
					String nomeProvaEscrita = jsonProva.get("nomeProvaEscrita").getAsString();
					avaliacao.getProvaEscrita().setNome(nomeProvaEscrita);
					
					boolean presente = jsonProva.get("presente").getAsBoolean();
					avaliacao.setPresente(presente);
					
					JsonArray jsonNotaOriginal = jsonProva.get("notaOriginalQuestao").getAsJsonArray();
					leitorQuestoes.carregaNotasIniciais(jsonNotaOriginal, avaliacao);
					
					JsonArray jsonNotaRecurso = jsonProva.get("notaRecursoQuestao").getAsJsonArray();
					leitorQuestoes.carregaNotasRecurso(jsonNotaRecurso, avaliacao);
				}
			}
		}
	}
}
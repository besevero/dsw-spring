package br.unirio.dsw.selecaoppgi.service.json;

import com.google.gson.JsonArray;

import br.unirio.dsw.selecaoppgi.model.inscricao.AvaliacaoProvaEscrita;

/**
 * Classe responsável por carregar as notas de uma avaliação de prova no formato JSON
 * 
 * @author Bernardo
 */
public class JsonQuestoesReader
{
	/**
	 * Carrega a representação JSON das notas originais de uma prova
	 */
	public void carregaNotasIniciais(JsonArray jsonQuestoes, AvaliacaoProvaEscrita avaliacao)
	{
		for (int i = 0; i < jsonQuestoes.size(); i++)
		{
			int nota = jsonQuestoes.get(i).getAsInt();
			avaliacao.setNotaOriginalQuestao(i, nota);
		}
	}

	/**
	 * Carrega a representação JSON das notas de recurso de uma prova
	 */
	public void carregaNotasRecurso(JsonArray jsonQuestoes, AvaliacaoProvaEscrita avaliacao)
	{
		for (int i = 0; i < jsonQuestoes.size(); i++)
		{
			int nota = jsonQuestoes.get(i).getAsInt();
			avaliacao.setNotaRecursoQuestao(i, nota);
		}
	}
}
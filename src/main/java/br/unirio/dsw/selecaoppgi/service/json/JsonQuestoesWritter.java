package br.unirio.dsw.selecaoppgi.service.json;

import com.google.gson.JsonArray;

import br.unirio.dsw.selecaoppgi.model.inscricao.AvaliacaoProvaEscrita;

/**
 * Classe que publica as notas das questões da prova em formato JSON
 * 
 * @author Bernardo Severo
 */
public class JsonQuestoesWritter 
{
	/**
	 * Gera a representação JSON das notas originais de uma prova escrita
	 */
	public JsonArray salvaNotasIniciais(AvaliacaoProvaEscrita avaliacao)
	{
		JsonArray jsonResultado = new JsonArray();
		int numeroQuestoes = avaliacao.getProvaEscrita().contaQuestoes();
		
		for (int i = 0; i < numeroQuestoes; i++)
			jsonResultado.add(avaliacao.getNotaOriginalQuestao(i));

		return jsonResultado;
	}

	/**
	 * Gera a representação JSON das notas de recurso de uma prova escrita
	 */
	public JsonArray salvaNotasRecurso(AvaliacaoProvaEscrita avaliacao)
	{
		JsonArray jsonResultado = new JsonArray();
		int numeroQuestoes = avaliacao.getProvaEscrita().contaQuestoes();
		
		for (int i = 0; i < numeroQuestoes; i++)
			jsonResultado.add(avaliacao.getNotaRecursoQuestao(i));

		return jsonResultado;
	}
}
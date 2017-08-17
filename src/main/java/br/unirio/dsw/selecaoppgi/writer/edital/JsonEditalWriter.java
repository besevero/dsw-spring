package br.unirio.dsw.selecaoppgi.writer.edital;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import br.unirio.dsw.selecaoppgi.model.edital.CriterioAlinhamento;
import br.unirio.dsw.selecaoppgi.model.edital.Edital;
import br.unirio.dsw.selecaoppgi.model.edital.ProjetoPesquisa;
import br.unirio.dsw.selecaoppgi.model.edital.ProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.edital.SubcriterioAlinhamento;
import br.unirio.dsw.selecaoppgi.model.usuario.User;

/**
 * Classe que publica os dados de um edital em formato JSON
 * 
 * @author Marcio Barros
 */
public class JsonEditalWriter
{
	/**
	 * Gera a representação JSON
	 */
	public JsonObject execute(Edital edital)
	{
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
		
		JsonObject json = new JsonObject();
		json.addProperty("id", edital.getId());
		json.addProperty("nome", edital.getNome());
		json.addProperty("status", edital.getStatus().getCodigo());
		json.addProperty("nomeStatus", edital.getStatus().getNome());
		json.addProperty("notaMinimaAlinhamento", edital.getNotaMinimaAlinhamento());
		json.addProperty("dataInicio", fmt.print(edital.getDataInicio()));
		json.addProperty("dataTermino", fmt.print(edital.getDataTermino()));
		json.add("selecao", geraRepresentacaoComissaoSelecao(edital));
		json.add("recursos", geraRepresentacaoComissaoRecursos(edital));
		json.add("provas", geraRepresentacaoProvasEscritas(edital));
		json.add("projetos", geraRepresentacaoProjetosPesquisa(edital));
		json.add("criterios", geraRepresentacaoCriteriosAlinhamento(edital));
		return json;
	}

	/**
	 * Gera a representação JSON da comissão de seleção
	 */
	private JsonArray geraRepresentacaoComissaoSelecao(Edital edital)
	{
		JsonArray jsonComissao = new JsonArray();
		
		for (User professor : edital.getComissaoSelecao())
		{
			JsonObject jsonProfessor = new JsonObject();
			jsonProfessor.addProperty("id", professor.getId());
			jsonProfessor.addProperty("nome", professor.getName());
			jsonComissao.add(jsonProfessor);
		}
		
		return jsonComissao;
	}

	/**
	 * Gera a representação JSON da comissão de recursos
	 */
	private JsonArray geraRepresentacaoComissaoRecursos(Edital edital)
	{
		JsonArray jsonComissao = new JsonArray();
		
		for (User professor : edital.getComissaoRecursos())
		{
			JsonObject jsonProfessor = new JsonObject();
			jsonProfessor.addProperty("id", professor.getId());
			jsonProfessor.addProperty("nome", professor.getName());
			jsonComissao.add(jsonProfessor);
		}
		
		return jsonComissao;
	}

	/**
	 * Gera a representação JSON da lista de provas escritas
	 */
	private JsonArray geraRepresentacaoProvasEscritas(Edital edital)
	{
		JsonArray jsonProvas = new JsonArray();
		
		for (ProvaEscrita prova : edital.getProvasEscritas())
			jsonProvas.add(geraRepresentacaoProvaEscrita(prova));
		
		return jsonProvas;
	}

	/**
	 * Gera a representação JSON de uma prova escrita
	 */
	private JsonObject geraRepresentacaoProvaEscrita(ProvaEscrita prova)
	{
		JsonObject json = new JsonObject();
		json.addProperty("sigla", prova.getSigla());
		json.addProperty("nome", prova.getNome());
		json.addProperty("dispensavel", prova.isDispensavel());
		json.addProperty("notaMinima", prova.getNotaMinimaAprovacao());

		JsonArray jsonQuestoes = new JsonArray();
		
		for (Integer peso : prova.getPesosQuestoes())
			jsonQuestoes.add(new JsonPrimitive(peso));

		json.add("questoes", jsonQuestoes);
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
		json.add("provas", geraRepresentacaoProvasEscritasProjetoPesquisa(projeto));
		return json;
	}

	/**
	 * Gera a representação JSON da lista de professores
	 */
	private JsonArray geraRepresentacaoProfessoresProjetoPesquisa(ProjetoPesquisa projeto)
	{
		JsonArray jsonProfessores = new JsonArray();
		
		for (User professor : projeto.getProfessores())
		{
			JsonObject jsonProfessor = new JsonObject();
			jsonProfessor.addProperty("id", professor.getId());
			jsonProfessor.addProperty("nome", professor.getName());
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
			jsonProvas.add(new JsonPrimitive(prova.getSigla()));
		
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
		json.addProperty("nome", subcriterio.getNome());
		json.addProperty("descricao", subcriterio.getDescricao());
		json.addProperty("peso", subcriterio.getPeso());
		return json;
	}
}
package br.unirio.dsw.selecaoppgi.service.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import br.unirio.dsw.selecaoppgi.model.edital.CriterioAlinhamento;
import br.unirio.dsw.selecaoppgi.model.edital.Edital;
import br.unirio.dsw.selecaoppgi.model.edital.ProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.AvaliacaoProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoEdital;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoProjetoPesquisa;
import br.unirio.dsw.selecaoppgi.service.json.JsonInscricaoProjetoPesquisaReader;
import br.unirio.dsw.selecaoppgi.service.json.JsonQuestoesReader;

/**
 * Classe responsavel pela persistencia de inscrições em edital de seleção
 * 
 * @author Marcio Barros
 */
@Service("inscricaoDAO")
public class InscricaoDAO extends AbstractDAO
{
	/**
	 * Registra uma nova inscrição de um candidato, incluindo os projetos de
	 * pesquisa, provas e critérios de alinhamento
	 */
	public boolean registraInscricao(InscricaoEdital inscricao)
	{
		// Cria um registro preenchendo os seguintes campos na tabela de Inscricao:
		//
		// * dataRegistro
		// * dataAtualizacao
		// * idEdital
		// * idCandidato
		// * cotaNegros
		// * cotaDeficientes
		// * jsonProjetos: vetor de codigo do projeto de pesquisa e texto descrevendo
		// interesses de pesquisa
		//
		// Cria os registros de provas escritas referentes à inscrição (notas e
		// presencao nulas, inicialmente)
		//
		// Cria os registros de critérios de alinhamento referentes à inscrição

		return false;
	}

	/**
	 * Carrega a lista de inscrições de um determinado edital que podem ser
	 * homologadas
	 */
	public List<InscricaoEdital> carregaAvaliacaoHomologacao(int idEdital)
	{
		// TODO Grupo 4: implementar este método em função do caso de uso #6
		return null;
	}

	/**
	 * Registra a homologação de uma inscrição na avaliação inicial
	 */
	public boolean homologacaoInicial(int idInscricao)
	{
		// Muda o campo homologadoInicial para TRUE e limpa o campo
		// justificativaHomologacaoInicial
		// Muda a data de atualização do registro de inscrição para a data de hoje
		// TODO Grupo 4: implementar este método em função do caso de uso #6
		return false;
	}

	/**
	 * Registra a recusa de homologação de uma inscrição na avaliação inicial
	 */
	public boolean recusaHomologacaoInicial(int idInscricao, String justificativa)
	{
		// Muda o campo homologadoInicial para FALSE e preenche o campo
		// justificativaHomologacaoInicial
		// Muda a data de atualização do registro de inscrição para a data de hoje
		// TODO Grupo 4: implementar este método em função do caso de uso #6
		return false;
	}

	/**
	 * Registra a homologação de uma inscrição no recurso
	 */
	public boolean homologacaoRecurso(int idInscricao)
	{
		// Muda o campo homologadoRecurso para TRUE e limpa o campo
		// justificativaHomologacaoRecurso
		// Muda a data de atualização do registro de inscrição para a data de hoje
		// Somente se o campo homologadoInicial estiver FALSE
		// TODO Grupo 4: implementar este método em função do caso de uso #6
		return false;
	}

	/**
	 * Registra a recusa de homologação de uma inscrição no recurso
	 */
	public boolean recusaHomologacaoRecurso(int idInscricao, String justificativa)
	{
		// Muda o campo homologadoRecurso para FALSE e preenche o campo
		// justificativaHomologacaoRecurso
		// Muda a data de atualização do registro de inscrição para a data de hoje
		// Somente se o campo homologadoInicial estiver FALSE
		// TODO Grupo 4: implementar este método em função do caso de uso #6
		return false;
	}

	/**
	 * Carrega a lista de inscrições de um determinado edital que podem ser
	 * dispensados de prova
	 */
	public List<InscricaoEdital> carregaAvaliacaoDispensaProva(int idEdital)
	{
		// As inscrições devem estar homologadas na avaliação inicial ou no recurso
		// TODO Grupo 4: implementar este método em função do caso de uso #6
		return null;
	}

	/**
	 * Registra a dispensa de provas de uma inscrição na avaliação inicial
	 */
	public boolean dispensaProvaInicial(int idInscricao)
	{
		// Muda o campo dispensadoProvaInicial para TRUE e limpa o campo
		// justificativaDispensaInicial
		// Muda a data de atualização do registro de inscrição para a data de hoje
		// Somente se o campo homologadoInicial estiver TRUE ou o campo
		// homologadoRecurso estiver TRUE
		// TODO Grupo 4: implementar este método em função do caso de uso #7
		return false;
	}

	/**
	 * Registra a recusa de dispensa de provas de uma inscrição na avaliação inicial
	 */
	public boolean recusaDispensaProvaInicial(int idInscricao, String justificativa)
	{
		// Muda o campo dispensadoProvaInicial para FALSE e preenche o campo
		// justificativaDispensaInicial
		// Muda a data de atualização do registro de inscrição para a data de hoje
		// Somente se o campo homologadoInicial estiver TRUE ou o campo
		// homologadoRecurso estiver TRUE
		// TODO Grupo 4: implementar este método em função do caso de uso #7
		return false;
	}

	/**
	 * Registra a dispensa de provas de uma inscrição no recurso
	 */
	public boolean dispensaProvaRecurso(int idInscricao)
	{
		// Muda o campo dispensadoProvaRecurso para TRUE e limpa o campo
		// justificativaDispensaRecurso
		// Muda a data de atualização do registro de inscrição para a data de hoje
		// Somente se o campo homologadoInicial estiver TRUE ou o campo
		// homologadoRecurso estiver TRUE
		// Somente se o campo dispensadoProvaInicial estiver FALSE
		// TODO Grupo 4: implementar este método em função do caso de uso #7
		return false;
	}

	/**
	 * Registra a recusa de dispensa de provas de uma inscrição no recurso
	 */
	public boolean recusaDispensaProvaRecurso(int idInscricao, String justificativa)
	{
		// Muda o campo dispensadoProvaRecurso para FALSE e preenche o campo
		// justificativaDispensaRecurso
		// Muda a data de atualização do registro de inscrição para a data de hoje
		// Somente se o campo homologadoInicial estiver TRUE ou o campo
		// homologadoRecurso estiver TRUE
		// Somente se o campo dispensadoProvaInicial estiver FALSE
		// TODO Grupo 4: implementar este método em função do caso de uso #7
		return false;
	}

	/**
	 * Marca uma inscrição como homologada, como parte do encerramento das
	 * homologações
	 */
	public boolean marcaHomologado(int idInscricao)
	{
		// Muda o campo homologado da inscricao para TRUE
		// TODO Grupo 3: implementar este método em função do caso de uso #8
		return false;
	}

	/**
	 * Marca uma inscrição como não homologada, como parte do encerramento das
	 * homologações
	 */
	public boolean marcaNaoHomologado(int idInscricao)
	{
		// Muda o campo homologado da inscricao para FALSE
		// TODO Grupo 3: implementar este método em função do caso de uso #8
		return false;
	}

	/**
	 * Marca uma inscrição como dispensada da prova, como parte do encerramento das
	 * homologações
	 */
	public boolean marcaDispensadoProva(int idInscricao)
	{
		// Muda o campo dispensado da inscricao para TRUE
		// TODO Grupo 3: implementar este método em função do caso de uso #8
		return false;
	}

	/**
	 * Marca uma inscrição como não dispensada da prova, como parte do encerramento
	 * das homologações
	 */
	public boolean marcaNaoDispensadoProva(int idInscricao)
	{
		// Muda o campo dispensado da inscricao para FALSE
		// TODO Grupo 3: implementar este método em função do caso de uso #8
		return false;
	}

	/**
	 * Carrega a lista de inscrições de um determinado edital que podem fazer uma
	 * prova
	 */
	public List<InscricaoEdital> carregaPresencaProvaEscrita(Edital edital, String codigoProva)
	{
		// TODO Grupo 1: implementar este método em função do caso de uso #9

		String SQL = "SELECT ipe.* " + "FROM InscricaoProvaEscrita ipe "
				+ "INNER JOIN Inscricao i ON ipe.idInscricao = i.id " + "WHERE codigoProvaEscrita = ? "
				+ "AND idEdital = ?";

		Connection c = getConnection();

		if (c == null)
			return null;

		ProvaEscrita prova = edital.pegaProvaEscritaCodigo(codigoProva);

		if (prova == null)
			return null;

		List<InscricaoEdital> lista = carregaInscricoesEdital(edital);
		eliminaInscricoesSemProvaEscrita(lista, edital, codigoProva);

		try
		{
			PreparedStatement ps = c.prepareStatement(SQL);
			ps.setString(1, codigoProva);
			ps.setInt(2, edital.getId());

			ResultSet rs = ps.executeQuery();

			while (rs.next())
			{
				int idInscricao = rs.getInt("idInscricao");
				boolean presente = rs.getInt("presente") != 0;
				String jsonQuestoesInicialString = rs.getString("jsonQuestoesInicial");
				String jsonQuestoesRecursoString = rs.getString("jsonQuestoesRecurso");

				InscricaoEdital inscricao = pegaInscricaoId(lista, idInscricao);

				if (inscricao != null)
				{
					AvaliacaoProvaEscrita inscricaoProva = inscricao.pegaAvaliacaoProvaEscrita(prova);
					inscricaoProva.setPresente(presente);

					JsonQuestoesReader readerJsonQuestoes = new JsonQuestoesReader();

					JsonArray jsonQuestoesInicialArray = (JsonArray) new JsonParser().parse(jsonQuestoesInicialString);
					readerJsonQuestoes.carregaNotasIniciais(jsonQuestoesInicialArray, inscricaoProva);

					JsonArray jsonQuestoesRecursoArray = (JsonArray) new JsonParser().parse(jsonQuestoesRecursoString);
					readerJsonQuestoes.carregaNotasRecurso(jsonQuestoesRecursoArray, inscricaoProva);

					// muda as notas na avaliacao original
					// muda as notas no recurso
				}
			}

			c.close();

		} catch (SQLException e)
		{
			log("InscricaoDAO.lista: " + e.getMessage());
		}

		return lista;
	}

	/**
	 * Retorna o id de inscrição de um candidato da lista de inscrições de um edital
	 */
	private InscricaoEdital pegaInscricaoId(List<InscricaoEdital> lista, int idInscricao)
	{	
		for (int i = lista.size() - 1; i > 0; i++)
		{
			InscricaoEdital inscricao = lista.get(i);
			
			if (inscricao.getId() == idInscricao)
				return inscricao;
		}
		return null;
	}

	/**
	 * Carrega todas as inscrições em um edital
	 */
	private List<InscricaoEdital> carregaInscricoesEdital(Edital edital)
	{
		String SQL = "SELECT usuario.id as id, usuario.nome AS nome, inscricao.* " + "FROM Inscricao "
				+ "INNER JOIN usuario ON usuario.id = inscricao.idCandidato " + "AND homologado = 1 "
				+ "AND idEdital = ?";

		Connection c = getConnection();

		if (c == null)
			return null;

		List<InscricaoEdital> lista = carregaInscricoesEdital(edital);

		try
		{
			PreparedStatement ps = c.prepareStatement(SQL);
			ps.setInt(1, edital.getId());

			ResultSet rs = ps.executeQuery();

			while (rs.next())
			{
				InscricaoEdital item = new InscricaoEdital(edital);
				item.setId(rs.getInt("inscricao.id"));
				item.setIdCandidato(rs.getInt("id"));
				item.setNomeCandidato(rs.getString("Nome"));
				item.setCotaNegros(rs.getInt("inscricao.cotaNegros") != 0);
				item.setCotaDeficientes(rs.getInt("inscricao.cotaDeficientes") != 0);
				item.setHomologadoOriginal(rs.getInt("inscricao.homologadoInicial") != 0);
				item.setJustificativaHomologacaoOriginal(rs.getString("inscricao.justificativaHomologacaoInicial"));
				item.setHomologadoRecurso(rs.getInt("inscricao.homologadoRecurso") != 0);
				item.setJustificativaHomologacaoRecurso(rs.getString("inscricao.justificativaHomologacaoRecurso"));
				item.setDispensadoProvaOriginal(rs.getInt("dispensadoProvaInicial") != 0);
				item.setJustificativaDispensaOriginal(rs.getString("justificativaDispensaInicial"));
				item.setDispensadoProvaRecurso(rs.getInt("dispensadoProvaRecurso") != 0);
				item.setJustificativaDispensaRecurso(rs.getString("justificativaDispensaRecurso"));

				String jsonProjetosString = rs.getString("inscricao.jsonProjetos");
				JsonArray jsonProjetos = (JsonArray) new JsonParser().parse(jsonProjetosString);

				JsonInscricaoProjetoPesquisaReader reader = new JsonInscricaoProjetoPesquisaReader();
				reader.execute(jsonProjetos, edital, item);

				lista.add(item);
			}

			c.close();

		} catch (SQLException e)
		{
			log("InscricaoDAO.carregaTodos: " + e.getMessage());
		}

		return lista;
	}

	/**
	 * Elimina todas as inscrições que não farão uma prova
	 */
	private void eliminaInscricoesSemProvaEscrita(List<InscricaoEdital> lista, Edital edital, String codigoProva)
	{
		ProvaEscrita prova = edital.pegaProvaEscritaCodigo(codigoProva);

		if (prova == null)
		{
			lista.clear();
			return;
		}

		for (int i = lista.size() - 1; i >= 0; i--)
		{
			InscricaoEdital inscricao = lista.get(i);

			if (inscricao.pegaAvaliacaoProvaEscrita(prova) == null)
				lista.remove(i);
		}

	}
	
	/**
	 * Conta o numero de inscrições de provas escritas que atendem a um filtro
	 */
	public int conta(String codigoProvaEscrita)
	{
		String SQL = "SELECT COUNT(*) " +
				"FROM inscricaoprovaescrita" +
				"WHERE codigoProvaEscrita LIKE ?";	
		
		Connection c = getConnection();
		
		if (c == null)
			return 0;
		
		try
		{
			PreparedStatement ps = c.prepareStatement(SQL);
			ps.setString(1, "%" + codigoProvaEscrita + "%");

			ResultSet rs = ps.executeQuery();
			int count = rs.next() ? rs.getInt(1) : 0;

			c.close();
			return count;

		} catch (SQLException e)
		{
			log("InscricaoDAO.conta: " + e.getMessage());
			return 0;
		}
	}

	/**
	 * Indica que um candidato esteve presente em uma prova
	 */
	public boolean indicaPresencaProvaEscrita(int idInscricao, String codigoProva)
	{

		// Muda o campo presente para TRUE no registro da prova escrita associada à
		// inscrição
		// Somente se o campo homologadoInicial estiver TRUE ou o campo
		// homologadoRecurso estiver TRUE
		// Somente se o campo dispensadoProvaInicial estiver FALSE ou
		// dispensadoProvaRecurso estiver FALSE
		// TODO Grupo 1: implementar este método em função do caso de uso #9

		Connection c = getConnection();

		if (c == null)
			return false;

		try
		{
			CallableStatement cs = c.prepareCall("{call AtualizaPresencaProvaEscrita(?, ?, ?)}");
			cs.setInt(1, idInscricao);
			cs.setString(2, codigoProva);
			cs.setInt(3, 1);
			cs.execute();
			c.close();
			return true;

		} catch (SQLException e)
		{
			log("InscricaoDAO.indicaPresenca: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Indica que um candidato faltou a uma prova
	 */
	public boolean indicaAusenciaProvaEscrita(int idInscricao, String codigoProva)
	{

		// Muda o campo presente para FALSE no registro da prova escrita associada à
		// inscrição
		// Somente se o campo homologadoInicial estiver TRUE ou o campo
		// homologadoRecurso estiver TRUE
		// Somente se o campo dispensadoProvaInicial estiver FALSE ou
		// dispensadoProvaRecurso estiver FALSE
		// TODO Grupo 1: implementar este método em função do caso de uso #9

		Connection c = getConnection();

		if (c == null)
			return false;

		try
		{
			CallableStatement cs = c.prepareCall("{call AtualizaPresencaProvaEscrita(?, ?, ?)}");
			cs.setInt(1, idInscricao);
			cs.setString(2, codigoProva);
			cs.setInt(3, 0);
			cs.execute();
			c.close();
			return true;

		} catch (SQLException e)
		{
			log("InscricaoDAO.indicaAusencia: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Carrega a lista de inscrições de um determinado edital que fizeram uma prova
	 */
	public List<InscricaoEdital> carregaAvaliacaoProvaEscrita(int idEdital, String codigoProva)
	{
		// TODO Grupo 2: implementar este método em função do caso de uso #10
		return null;
	}

	/**
	 * Atualiza os notas da avaliação inicial de uma inscrição
	 */
	public boolean indicaNotasProvaEscritaInicial(int idInscricao, AvaliacaoProvaEscrita avaliacao)
	{
		// Muda os campos jsonQuestoes e notaFinal de acordo com os parâmetros
		// Somente se o campo homologadoInicial estiver TRUE ou o campo
		// homologadoRecurso estiver TRUE
		// Somente se o campo dispensadoProvaInicial estiver FALSE ou
		// dispensadoProvaRecurso estiver FALSE
		// Somente se a presença na prova estiver TRUE
		// TODO Grupo 2: implementar este método em função do caso de uso #10
		return false;
	}

	/**
	 * Atualiza os notas do recurso de uma inscrição
	 */
	public boolean indicaNotasProvaEscritaRecurso(int idInscricao, AvaliacaoProvaEscrita avaliacao)
	{
		// Muda os campos jsonQuestoes e notaFinal de acordo com os parâmetros
		// Somente se o campo homologadoInicial estiver TRUE ou o campo
		// homologadoRecurso estiver TRUE
		// Somente se o campo dispensadoProvaInicial estiver FALSE ou
		// dispensadoProvaRecurso estiver FALSE
		// Somente se a presença na prova estiver TRUE
		// TODO Grupo 2: implementar este método em função do caso de uso #11
		return false;
	}

	/**
	 * Indica que um candidato foi aprovado nas provas escritas
	 */
	public boolean marcaAprovadoProvasEscritas(int idInscricao)
	{
		// Muda o campo aprovadoProvas de uma inscrição para TRUE
		// TODO Grupo 1: implementar este método em função do caso de uso #12

		Connection c = getConnection();
		try
		{
			CallableStatement cs = c.prepareCall("{call AtualizaCampoAprovadoProvas(?, ?)}");
			cs.setInt(1, idInscricao);
			cs.setInt(2, 1);
			cs.execute();
			c.close();
			return true;

		} catch (SQLException e)
		{
			log("InscricaoDAO.marcaAprovado: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Indica que um candidato foi reprovado nas provas escritas
	 */
	public boolean marcaReprovadoProvasEscritas(int idInscricao)
	{
		// Muda o campo aprovadoProvas de uma inscrição para FALSE
		// TODO Grupo 1: implementar este método em função do caso de uso #12

		Connection c = getConnection();
		try
		{
			CallableStatement cs = c.prepareCall("{call AtualizaCampoAprovadoProvas(?, ?)}");
			cs.setInt(1, idInscricao);
			cs.setInt(2, 0);
			cs.execute();
			c.close();
			return true;

		} catch (SQLException e)
		{
			log("InscricaoDAO.marcaReprovado: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Carrega a lista de inscrições de um determinado edital que podem fazer uma
	 * prova oral
	 */
	public List<InscricaoEdital> carregaPresencaProvaOral(int idEdital, String codigoProjetoPesquisa)
	{
		// TODO Grupo 1: implementar este método em função do caso de uso #13
		String SQL = "SELECT" + "usuario.nome AS 'Nome'," + "inscricaoprovaoral.codigoProvaOral AS 'Prova',"
				+ "inscricaoprovaoral.presente AS 'Presenca'" + "FROM usuario" + "JOIN"
				+ "inscricao ON usuario.id = inscricao.idCandidato" + "AND homologado = 1" + "AND idEdital = ?" + "JOIN"
				+ "inscricaoprovaoral ON usuario.id = inscricaoprovaoral.idInscricao" + "AND codigoProvaOral = ?";

		Connection c = getConnection();

		if (c == null)
			return null;

		List<InscricaoEdital> lista = new ArrayList<InscricaoEdital>();

		try
		{
			PreparedStatement ps = c.prepareStatement(SQL);
			ps.setInt(1, idEdital);
			ps.setString(2, "%" + codigoProjetoPesquisa + "%");

			ResultSet rs = ps.executeQuery();

			while (rs.next())
			{
				Edital edital = null;

				InscricaoEdital item = new InscricaoEdital(edital);
				item.setNomeCandidato(rs.getString("Nome"));

				CriterioAlinhamento provaOral = new CriterioAlinhamento();
				provaOral.setCodigo(rs.getString("Prova"));
				CriterioAlinhamento avaliacaoOral = new CriterioAlinhamento();
				// avaliacaoOral.setPresente(rs.getBoolean("Presenca"));

				lista.add(item);
			}

			c.close();

		} catch (SQLException e)
		{
			log("InscricaoDAO.lista: " + e.getMessage());
		}

		return lista;
	}

	/**
	 * Indica que um candidato esteve presente na prova oral de um projeto
	 */
	public boolean indicaPresencaProvaOral(int idInscricao, String codigoProjetoPesquisa)
	{
		// Muda o campo presenteProvaOral para TRUE no registro da prova de alinhamento
		// associada à inscrição e projeto de pesquisa
		// Somente se o campo homologadoInicial estiver TRUE ou o campo
		// homologadoRecurso estiver TRUE
		// Somente se o campo dispensadoProvaInicial estiver FALSE ou
		// dispensadoProvaRecurso estiver FALSE
		// Somente se a nota final de todas as provas escritas for maior do que a nota
		// mínima para aprovação
		// Somente se o projeto exigir prova oral
		// TODO Grupo 1: implementar este método em função do caso de uso #13
		String SQLConsulta = "Select homologadoInicial, " + "homologadoRecurso, " + "dispensadoProvaInicial, "
				+ "dispensadoProvaRecurso " + "notaFinalProvaEscrita" + "exigeProvaOral" + "From inscricao"
				+ "WHERE id = ?;";

		String SQLUpdate = "UPDATE inscricaoprovaoral " + "SET presente = 0 "
				+ "WHERE idInscricao = ? and codigoProvaOral = ?";

		Connection c = getConnection();

		if (c == null)
			return false;

		try
		{
			PreparedStatement ps = c.prepareStatement(SQLConsulta);
			ps.setInt(1, idInscricao);
			ResultSet rs = ps.executeQuery();

			if (rs.getInt("homologadoInicial") == 1 || rs.getInt("homologadoRecurso") == 1
					|| rs.getInt("dispensadoProvaInicial") == 0 || rs.getInt("dispensadoProvaRecurso") == 0
					|| rs.getInt("notaFinalProvaEscrita") > 7)
			{
				ps = c.prepareStatement(SQLUpdate);
				ps.setInt(1, idInscricao);
				ps.setString(1, codigoProjetoPesquisa);
				rs = ps.executeQuery();
			}

			c.close();
		} catch (SQLException e)
		{
			log("EditalDAO.lista: " + e.getMessage());
		}

		return true;
	}

	/**
	 * Indica que um candidato esteve ausente na prova oral de um projeto
	 */
	public boolean indicaAusenciaProvaOral(int idInscricao, String codigoProjetoPesquisa)
	{
		// Muda o campo presenteProvaOral para FALSE no registro da prova de alinhamento
		// associada à inscrição e projeto de pesquisa
		// Somente se o campo homologadoInicial estiver TRUE ou o campo
		// homologadoRecurso estiver TRUE
		// Somente se o campo dispensadoProvaInicial estiver FALSE ou
		// dispensadoProvaRecurso estiver FALSE
		// Somente se a nota final de todas as provas escritas for maior do que a nota
		// mínima para aprovação
		// Somente se o projeto exigir prova oral
		// TODO Grupo 1: implementar este método em função do caso de uso #13
		return false;
	}

	/**
	 * Carrega a lista de inscrições de um determinado edital que podem fazer uma
	 * prova oral
	 */
	public List<InscricaoEdital> carregaAvaliacaoAlinhamento(int idEdital, String codigoProjetoPesquisa)
	{
		// TODO Grupo 7: implementar este método em função dos casos de uso #14 e #15
		return null;
	}

	/**
	 * Indica as notas de alinhamento de um projeto de pesquisa na avaliação inicial
	 */
	public boolean indicaNotasAlinhamentoInicial(int idInscricao, InscricaoProjetoPesquisa avaliacao)
	{
		// Muda o campo justificativaNotasInicial do alinhamento da inscrição e projeto
		// de pesquisa de acordo com o parâmetro
		// Muda o campo jsonSubcriteriosInicial com as avaliações dos critérios
		// recebidas como parâmetro
		// Muda o campo notaFinal de acordo com o parâmetro
		// Somente se o campo homologadoInicial estiver TRUE ou o campo
		// homologadoRecurso estiver TRUE
		// Somente se o campo dispensadoProvaInicial estiver FALSE ou
		// dispensadoProvaRecurso estiver FALSE
		// Somente se a nota final de todas as provas escritas for maior do que a nota
		// mínima para aprovação
		// Somente se o projeto de pesquisa não exigir prova oral ou estiver presente na
		// prova oral
		// TODO Grupo 7: implementar este método em função do caso de uso #14
		return false;
	}

	/**
	 * Indica as notas de alinhamento de um projeto de pesquisa no recurso
	 */
	public boolean indicaNotasAlinhamentoRecurso(int idInscricao, InscricaoProjetoPesquisa avaliacao)
	{
		// Muda o campo justificativaNotasRecurso do alinhamento da inscrição e projeto
		// de pesquisa de acordo com o parâmetro
		// Muda o campo jsonSubcriteriosRecurso com as avaliações dos critérios
		// recebidas como parâmetro
		// Muda o campo notaFinal de acordo com o parâmetro
		// Somente se o campo homologadoInicial estiver TRUE ou o campo
		// homologadoRecurso estiver TRUE
		// Somente se o campo dispensadoProvaInicial estiver FALSE ou
		// dispensadoProvaRecurso estiver FALSE
		// Somente se a nota final de todas as provas escritas for maior do que a nota
		// mínima para aprovação
		// Somente se o projeto de pesquisa não exigir prova oral ou estiver presente na
		// prova oral
		// TODO Grupo 7: implementar este método em função do caso de uso #15
		return false;
	}

	// TODO criar script para povoar as inscrições para os nossos editais
}
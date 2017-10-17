package br.unirio.dsw.selecaoppgi.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.unirio.dsw.selecaoppgi.model.inscricao.AvaliacaoProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoEdital;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoProjetoPesquisa;

/**
 * Classe responsavel pela persistencia de inscrições em edital de seleção
 * 
 * @author Marcio Barros
 */
@Service("inscricaoDAO")
public class InscricaoDAO extends AbstractDAO
{
	/**
	 * Registra uma nova inscrição de um candidato, incluindo os projetos de pesquisa, provas e critérios de alinhamento
	 */
	public boolean registraInscricao(InscricaoEdital inscricao)
	{
//		Cria um registro preenchendo os seguintes campos na tabela de Inscricao:
//
//		* dataRegistro
//		* dataAtualizacao
//		* idEdital
//		* idCandidato
//		* cotaNegros
//		* cotaDeficientes
//		* jsonProjetos: vetor de codigo do projeto de pesquisa e texto descrevendo interesses de pesquisa
//
//		Cria os registros de provas escritas referentes à inscrição (notas e presencao nulas, inicialmente)
//
//		Cria os registros de critérios de alinhamento referentes à inscrição

		return false;
	}
	
	/**
	 * Carrega a lista de inscrições de um determinado edital que podem ser homologadas
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
		// Muda o campo homologadoInicial para TRUE e limpa o campo justificativaHomologacaoInicial
		// Muda a data de atualização do registro de inscrição para a data de hoje
		// TODO Grupo 4: implementar este método em função do caso de uso #6
		return false;
	}
	
	/**
	 * Registra a recusa de homologação de uma inscrição na avaliação inicial
	 */
	public boolean recusaHomologacaoInicial(int idInscricao, String justificativa)
	{
		// Muda o campo homologadoInicial para FALSE e preenche o campo justificativaHomologacaoInicial
		// Muda a data de atualização do registro de inscrição para a data de hoje
		// TODO Grupo 4: implementar este método em função do caso de uso #6
		return false;
	}
	
	/**
	 * Registra a homologação de uma inscrição no recurso
	 */
	public boolean homologacaoRecurso(int idInscricao)
	{
		// Muda o campo homologadoRecurso para TRUE e limpa o campo justificativaHomologacaoRecurso
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
		// Muda o campo homologadoRecurso para FALSE e preenche o campo justificativaHomologacaoRecurso
		// Muda a data de atualização do registro de inscrição para a data de hoje
		// Somente se o campo homologadoInicial estiver FALSE
		// TODO Grupo 4: implementar este método em função do caso de uso #6
		return false;
	}
	
	/**
	 * Carrega a lista de inscrições de um determinado edital que podem ser dispensados de prova
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
		// Muda o campo dispensadoProvaInicial para TRUE e limpa o campo justificativaDispensaInicial
		// Muda a data de atualização do registro de inscrição para a data de hoje
		// Somente se o campo homologadoInicial estiver TRUE ou o campo homologadoRecurso estiver TRUE
		// TODO Grupo 4: implementar este método em função do caso de uso #7
		return false;
	}
	
	/**
	 * Registra a recusa de dispensa de provas de uma inscrição na avaliação inicial
	 */
	public boolean recusaDispensaProvaInicial(int idInscricao, String justificativa)
	{
		// Muda o campo dispensadoProvaInicial para FALSE e preenche o campo justificativaDispensaInicial
		// Muda a data de atualização do registro de inscrição para a data de hoje
		// Somente se o campo homologadoInicial estiver TRUE ou o campo homologadoRecurso estiver TRUE
		// TODO Grupo 4: implementar este método em função do caso de uso #7
		return false;
	}
	
	/**
	 * Registra a dispensa de provas de uma inscrição no recurso
	 */
	public boolean dispensaProvaRecurso(int idInscricao)
	{
		// Muda o campo dispensadoProvaRecurso para TRUE e limpa o campo justificativaDispensaRecurso
		// Muda a data de atualização do registro de inscrição para a data de hoje
		// Somente se o campo homologadoInicial estiver TRUE ou o campo homologadoRecurso estiver TRUE
		// Somente se o campo dispensadoProvaInicial estiver FALSE
		// TODO Grupo 4: implementar este método em função do caso de uso #7
		return false;
	}
	
	/**
	 * Registra a recusa de dispensa de provas de uma inscrição no recurso
	 */
	public boolean recusaDispensaProvaRecurso(int idInscricao, String justificativa)
	{
		// Muda o campo dispensadoProvaRecurso para FALSE e preenche o campo justificativaDispensaRecurso
		// Muda a data de atualização do registro de inscrição para a data de hoje
		// Somente se o campo homologadoInicial estiver TRUE ou o campo homologadoRecurso estiver TRUE
		// Somente se o campo dispensadoProvaInicial estiver FALSE
		// TODO Grupo 4: implementar este método em função do caso de uso #7
		return false;
	}
	
	/**
	 * Marca uma inscrição como homologada, como parte do encerramento das homologações
	 */
	public boolean marcaHomologado(int idInscricao)
	{
		// Muda o campo homologado da inscricao para TRUE
		// TODO Grupo 3: implementar este método em função do caso de uso #8
		return false;
	}
	
	/**
	 * Marca uma inscrição como não homologada, como parte do encerramento das homologações
	 */
	public boolean marcaNaoHomologado(int idInscricao)
	{
		// Muda o campo homologado da inscricao para FALSE
		// TODO Grupo 3: implementar este método em função do caso de uso #8
		return false;
	}
	
	/**
	 * Marca uma inscrição como dispensada da prova, como parte do encerramento das homologações
	 */
	public boolean marcaDispensadoProva(int idInscricao)
	{
		// Muda o campo dispensado da inscricao para TRUE
		// TODO Grupo 3: implementar este método em função do caso de uso #8
		return false;
	}
	
	/**
	 * Marca uma inscrição como não dispensada da prova, como parte do encerramento das homologações
	 */
	public boolean marcaNaoDispensadoProva(int idInscricao)
	{
		// Muda o campo dispensado da inscricao para FALSE
		// TODO Grupo 3: implementar este método em função do caso de uso #8
		return false;
	}
	
	/**
	 * Carrega a lista de inscrições de um determinado edital que podem fazer uma prova
	 */
	public List<InscricaoEdital> carregaPresencaProvaEscrita(int idEdital, String codigoProva)
	{
		// TODO Grupo 1: implementar este método em função do caso de uso #9
		
		String SQL = "SELECT" + 
				     		"usuario.nome AS 'Nome'," +
				     		"inscricaoprovaescrita.codigoProvaEscrita AS 'Prova'," +
				     		"inscricaoprovaescrita.presente AS 'Presenca'" +
				      "FROM usuario" +
				      "JOIN" +
					  		"inscricao ON usuario.id = inscricao.idCandidato" +
					  		"AND homologado = 1" +
					  		"AND idEdital = ?" +
					  "JOIN" +
			     			"inscricaoprovaescrita ON usuario.id = inscricaoprovaescrita.idInscricao" +
						     "AND codigoProvaEscrita = ?";

		Connection c = getConnection();
		 
		 if (c == null)
			return null;
		
		List<InscricaoEdital> lista = new ArrayList<InscricaoEdital>();
		
		try
		{
			PreparedStatement ps = c.prepareStatement(SQL);
			ps.setInt(1, idEdital);
			ps.setString(2, "%" + codigoProva + "%");
			
			ResultSet rs = ps.executeQuery();

			while (rs.next())
			{
				/*Exemplo
				 * publicacao = new PublicacaoModelo();
                publicacao.setTitulo(rs.getString("titulo"));
                publicacao.setId(rs.getInt("id"));
                publicacao.setPaginaFinal(rs.getInt("paginafinal"));
                publicacao.setPaginaInicial(rs.getInt("paginainicial"));
                publicacao.setDataPublicacao(rs.getString("datapublicacao"));
                listaPublicacao.add(publicacao);
                */
				InscricaoEdital item = new InscricaoEdital();
				item.setNomeCandidato(rs.getString("nome"));
				item.(rs.getString("nome"));
				//TODO REVISAR: Inscrição Edital não tem os atributos retornados na consulta, é essa mesma a consulta?
				
				lista.add(item);
				// lista.add(item);
			}
			
			c.close();

		} catch (SQLException e)
		{
			log("InscricaoDAO.lista: " + e.getMessage());
		}
		    
		return lista;
	}
	/**
	 * Indica que um candidato esteve presente em uma prova
	 */
	public boolean indicaPresencaProvaEscrita(int idInscricao, String codigoProva)
	{
		String SQLConsulta = "Select homologadoInicial, "
							 + "homologadoRecurso, "
							 + "dispensadoProvaInicial, "
							 + "dispensadoProvaRecurso "
				    + "From inscricao"
				    + "WHERE id = ?;";
		
	   String SQLUpdate = "UPDATE inscricaoprovaescrita "
	   					+ "SET presente = 1 "
	   					+ "WHERE idInscricao = ? and codigoProvaEscrita = ?";
	   
	   Connection c = getConnection();
		
		if (c == null)
			return false;
								
		try
			{
				PreparedStatement ps = c.prepareStatement(SQLConsulta);
				ps.setInt(1, idInscricao);	
				ResultSet rs = ps.executeQuery();
				
				if(rs.getInt("homologadoInicial") == 1 || rs.getInt("homologadoRecurso") == 1 ||
					rs.getInt("dispensadoProvaInicial") == 0 || rs.getInt("dispensadoProvaRecurso") == 0)
				{
					ps = c.prepareStatement(SQLUpdate);
					ps.setInt(1, idInscricao);	
					ps.setString(1, codigoProva);
					rs = ps.executeQuery();
				}
				
				c.close();
			} catch (SQLException e)
			{
				log("EditalDAO.lista: " + e.getMessage());
			}

		// Muda o campo presente para TRUE no registro da prova escrita associada à inscrição
		// Somente se o campo homologadoInicial estiver TRUE ou o campo homologadoRecurso estiver TRUE
		// Somente se o campo dispensadoProvaInicial estiver FALSE ou dispensadoProvaRecurso estiver FALSE
		// TODO Grupo 1: implementar este método em função do caso de uso #9
		return true;
	}
	
	/**
	 * Indica que um candidato faltou a uma prova
	 */
	public boolean indicaAusenciaProvaEscrita(int idInscricao, String codigoProva)
	{
		// Muda o campo presente para FALSE no registro da prova escrita associada à inscrição
		// Somente se o campo homologadoInicial estiver TRUE ou o campo homologadoRecurso estiver TRUE
		// Somente se o campo dispensadoProvaInicial estiver FALSE ou dispensadoProvaRecurso estiver FALSE
		// TODO Grupo 1: implementar este método em função do caso de uso #9
		String SQLConsulta = "Select homologadoInicial, "
									 + "homologadoRecurso, "
									 + "dispensadoProvaInicial, "
									 + "dispensadoProvaRecurso "
							 + "From inscricao"
							 + "WHERE id = ?;";

		String SQLUpdate = "UPDATE inscricaoprovaescrita "
						+ "SET presente = 0 "
						+ "WHERE idInscricao = ? and codigoProvaEscrita = ?";

		Connection c = getConnection();
		
		if (c == null)
			return false;
							
		try
		{
			PreparedStatement ps = c.prepareStatement(SQLConsulta);
			ps.setInt(1, idInscricao);	
			ResultSet rs = ps.executeQuery();
			
			if(rs.getInt("homologadoInicial") == 1 || rs.getInt("homologadoRecurso") == 1 ||
				rs.getInt("dispensadoProvaInicial") == 0 || rs.getInt("dispensadoProvaRecurso") == 0)
			{
				ps = c.prepareStatement(SQLUpdate);
				ps.setInt(1, idInscricao);	
				ps.setString(1, codigoProva);
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
		// Somente se o campo homologadoInicial estiver TRUE ou o campo homologadoRecurso estiver TRUE
		// Somente se o campo dispensadoProvaInicial estiver FALSE ou dispensadoProvaRecurso estiver FALSE
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
		// Somente se o campo homologadoInicial estiver TRUE ou o campo homologadoRecurso estiver TRUE
		// Somente se o campo dispensadoProvaInicial estiver FALSE ou dispensadoProvaRecurso estiver FALSE
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
			PreparedStatement ps = c.prepareStatement("UPDATE inscricao SET aprovadoProvas = 1 WHERE id = ?");
			ps.setInt(1, idInscricao);
			
			ps.executeQuery();			
			
			c.close();
			return true;

		} catch (SQLException e)
		{
			log("InscricaoDAO.getInscricaoId: " + e.getMessage());
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
			PreparedStatement ps = c.prepareStatement("UPDATE inscricao SET aprovadoProvas = 0 WHERE id = ?");
			ps.setInt(1, idInscricao);
			
			ps.executeQuery();			
			
			c.close();
			return true;

		} catch (SQLException e)
		{
			log("InscricaoDAO.getInscricaoId: " + e.getMessage());
			return false;
		}
}
	
	/**
	 * Carrega a lista de inscrições de um determinado edital que podem fazer uma prova oral
	 */
	public List<InscricaoEdital> carregaPresencaProvaOral(int idEdital, String codigoProjetoPesquisa)
	{
		// TODO Grupo 1: implementar este método em função do caso de uso #13
		return null;
	}

	/**
	 * Indica que um candidato esteve presente na prova oral de um projeto
	 */
	public boolean indicaPresencaProvaOral(int idInscricao, String codigoProjetoPesquisa)
	{
		// Muda o campo presenteProvaOral para TRUE no registro da prova de alinhamento associada à inscrição e projeto de pesquisa
		// Somente se o campo homologadoInicial estiver TRUE ou o campo homologadoRecurso estiver TRUE
		// Somente se o campo dispensadoProvaInicial estiver FALSE ou dispensadoProvaRecurso estiver FALSE
		// Somente se a nota final de todas as provas escritas for maior do que a nota mínima para aprovação
		// Somente se o projeto exigir prova oral
		// TODO Grupo 1: implementar este método em função do caso de uso #13
		return false;
	}
	
	/**
	 * Indica que um candidato esteve ausente na prova oral de um projeto
	 */
	public boolean indicaAusenciaProvaOral(int idInscricao, String codigoProjetoPesquisa)
	{
		// Muda o campo presenteProvaOral para FALSE no registro da prova de alinhamento associada à inscrição e projeto de pesquisa
		// Somente se o campo homologadoInicial estiver TRUE ou o campo homologadoRecurso estiver TRUE
		// Somente se o campo dispensadoProvaInicial estiver FALSE ou dispensadoProvaRecurso estiver FALSE
		// Somente se a nota final de todas as provas escritas for maior do que a nota mínima para aprovação
		// Somente se o projeto exigir prova oral
		// TODO Grupo 1: implementar este método em função do caso de uso #13
		return false;
	}
	
	/**
	 * Carrega a lista de inscrições de um determinado edital que podem fazer uma prova oral
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
		// Muda o campo justificativaNotasInicial do alinhamento da inscrição e projeto de pesquisa de acordo com o parâmetro
		// Muda o campo jsonSubcriteriosInicial com as avaliações dos critérios recebidas como parâmetro
		// Muda o campo notaFinal de acordo com o parâmetro
		// Somente se o campo homologadoInicial estiver TRUE ou o campo homologadoRecurso estiver TRUE
		// Somente se o campo dispensadoProvaInicial estiver FALSE ou dispensadoProvaRecurso estiver FALSE
		// Somente se a nota final de todas as provas escritas for maior do que a nota mínima para aprovação
		// Somente se o projeto de pesquisa não exigir prova oral ou estiver presente na prova oral
		// TODO Grupo 7: implementar este método em função do caso de uso #14
		return false;
	}
	
	/**
	 * Indica as notas de alinhamento de um projeto de pesquisa no recurso
	 */
	public boolean indicaNotasAlinhamentoRecurso(int idInscricao, InscricaoProjetoPesquisa avaliacao)
	{
		// Muda o campo justificativaNotasRecurso do alinhamento da inscrição e projeto de pesquisa de acordo com o parâmetro
		// Muda o campo jsonSubcriteriosRecurso com as avaliações dos critérios recebidas como parâmetro
		// Muda o campo notaFinal de acordo com o parâmetro
		// Somente se o campo homologadoInicial estiver TRUE ou o campo homologadoRecurso estiver TRUE
		// Somente se o campo dispensadoProvaInicial estiver FALSE ou dispensadoProvaRecurso estiver FALSE
		// Somente se a nota final de todas as provas escritas for maior do que a nota mínima para aprovação
		// Somente se o projeto de pesquisa não exigir prova oral ou estiver presente na prova oral
		// TODO Grupo 7: implementar este método em função do caso de uso #15
		return false;
	}
	
	// TODO criar script para povoar as inscrições para os nossos editais
}
package too.trabalho.spa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import too.trabalho.spa.jdbc.CriaConexao;
import too.trabalho.spa.tipos.Pesquisa;
import too.trabalho.spa.tipos.Questionario;

/**
 * Classe para manipular os objetos Questionario no banco de dados
 * @author Rafaela
 *
 */
public class QuestionarioDAO {
	Connection connection = new CriaConexao().conexao();
	
	/**
	 * Insere, no banco, os dados sobre o questionário
	 * @param questionario questionario a ser inserido
	 * @param cod_pesquisa código da pesquisa
	 */
	public void insere(int cod_pesquisa, Questionario questionario){
		String sql = "insert into questionario (cod_pesquisa, pergunta, tipo_pergunta, cod_conceito)"
				+ "values (?, ?, ?,?)";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, cod_pesquisa);
			statement.setString(2, questionario.getPergunta());
			statement.setString(3, questionario.getTipoPergunta());
			statement.setInt(4, questionario.getConceito());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			//System.out.println("Falha ao inserir");
			e.printStackTrace();
		}
	}//cadastro
	
	/**
	 * Método para obter todas as categorias de perguntas feitas durante a pesquisa
	 * @param nomePesquisa nome da pesquisa
	 * @return lista de perguntas
	 */
	public List<String> obtemPerguntas(String nomePesquisa){
		String sql = "select distinct pergunta from questionario inner join pesquisa on "
				+ "questionario.cod_pesquisa=pesquisa.codigo inner join titulo_pesquisa on "
				+ "titulo_pesquisa.codigo=pesquisa.codigo_pesquisa where titulo_pesquisa.nome="
				+ "? group by pergunta";
		
		List<String> pesrguntas = new ArrayList<>();

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, nomePesquisa);
			ResultSet rs = statement.executeQuery();

			while (rs.next())
				pesrguntas.add(rs.getString("pergunta"));
			rs.close();
			statement.close();
			return pesrguntas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Obtém todas as respostas de um determinado curso
	 * @param curso categoria do cursi
	 * @param nomePesquisa nome da pesquisa que vai obter o dado
	 * @return lista de cursos
	 */
	public List<Pesquisa> obtemAvaliacaoPorCurso(String curso, String nomePesquisa){
		String sql = "select pergunta, curso, conceito.peso from questionario inner join"
				+ " conceito on cod_conceito=conceito.codigo inner join pesquisa on "
				+ "cod_pesquisa=pesquisa.codigo inner join titulo_pesquisa on "
				+ "titulo_pesquisa.codigo=pesquisa.codigo_pesquisa where curso ilike ? "
				+ "and titulo_pesquisa.nome ilike ?";
		
		List<Pesquisa> pesquisaList = new ArrayList<>();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, "%" + curso + "%");
			statement.setString(2, "%" + nomePesquisa+ "%");
			ResultSet rs = statement.executeQuery();

			while (rs.next()){
				Pesquisa pesquisa = new Pesquisa();
				pesquisa.setCurso(rs.getString("curso"));
				Questionario questionario = new Questionario();
				questionario.setPergunta(rs.getString("pergunta"));
				questionario.setConceito(rs.getInt("peso"));
				pesquisa.insere(questionario);
				pesquisaList.add(pesquisa);
			}
				
			rs.close();
			statement.close();
			return pesquisaList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}	
	
	/**
	 * Obtém todas as avaliações dadas por uma determinada categoria
	 * @param nomePesquisa pesquisa que irá obter os dados
	 * @return lista de avaliações
	 */
	public List<Pesquisa> obtemAvaliacaoPorCategoria(String nomePesquisa){
		String sql = "select pergunta, questionado, conceito.peso from questionario inner join"
				+ " conceito on cod_conceito=conceito.codigo inner join pesquisa on "
				+ "cod_pesquisa=pesquisa.codigo inner join titulo_pesquisa on "
				+ "titulo_pesquisa.codigo=pesquisa.codigo_pesquisa where titulo_pesquisa.nome=?";
		
		List<Pesquisa> pesquisaList = new ArrayList<>();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, nomePesquisa);
			ResultSet rs = statement.executeQuery();

			while (rs.next()){
				Pesquisa pesquisa = new Pesquisa();
				pesquisa.setQuestionado(rs.getString("questionado"));
				Questionario questionario = new Questionario();
				questionario.setPergunta(rs.getString("pergunta"));
				questionario.setConceito(rs.getInt("peso"));
				pesquisa.insere(questionario);
				pesquisaList.add(pesquisa);
			}
				
			rs.close();
			statement.close();
			return pesquisaList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}

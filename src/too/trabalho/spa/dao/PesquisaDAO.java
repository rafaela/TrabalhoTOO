package too.trabalho.spa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import too.trabalho.spa.jdbc.CriaConexao;
import too.trabalho.spa.tipos.Pesquisa;
import too.trabalho.spa.tipos.PesquisaRelatorio;

/**
 * Classe para manipular os objetos Pesquisa no banco de dados 
 * @author Rafaela
 *
 */
public class PesquisaDAO {
	Connection connection = new CriaConexao().conexao();
	
	/**
	 * Insere, no banco, os dados sobre a pesquisa 
	 * @param pesquisa pesquisa a ser inserida
	 */
	public void insere(Pesquisa pesquisa){
		String sql = "insert into pesquisa (codigo_pesquisa, questionado, campus, curso) "
				+ "values (?, ?, ?, ?)";
		
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, pesquisa.getTipoPesquisa());
			statement.setString(2, pesquisa.getQuestionado());
			statement.setString(3, pesquisa.getCampus());
			statement.setString(4, pesquisa.getCurso());

			statement.execute();
			statement.close();
		} catch (SQLException e) {
			System.out.println("Falha ao inserir a pesquisa");
			//e.printStackTrace();
		}
	}//cadastro
	
	/**
	 * Lista todos os dados da pesquisa
	 * @return <code>List</code> contendo a pesquisa
	 */
	public List<Pesquisa> listaPesquisa(){
		List<Pesquisa> pesquisaList = new ArrayList<>();
		Pesquisa pesquisa;
		String sql = "select * from pesquisa";

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				pesquisa = new Pesquisa();
				pesquisa.setCodigo(rs.getInt("codigo"));
				pesquisa.setTipoPesquisa(rs.getInt("codigo_pesquisa"));
				pesquisa.setQuestionado(rs.getString("questionado"));
				pesquisa.setCampus(rs.getString("campus"));
				pesquisa.setCurso(rs.getString("curso"));
				
				pesquisaList.add(pesquisa);

			}

			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pesquisaList;
	}//listaPesquisa
	
	/**
	 * Obtém o código da resposta da pesquisa no banco de dados
	 * @return uma lista contendo os códigos
	 */
	public List<Integer> obtemCodigo(){
		String sql = "select codigo from pesquisa";
		List<Integer> codigosList = new ArrayList<>();

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next())
				codigosList.add(new Integer(rs.getInt("codigo")));
			rs.close();
			statement.close();

			return codigosList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Obtém os cursos e o número de participante de cada curso
	 * @param pesquisa nome da pesquisa
	 * @return lista contendo os cursos e o número de participantes
	 */
	public List<PesquisaRelatorio> obtemQdtCursos(String pesquisa){
		String sql = "select * from qtd_curso(?)";
		List<PesquisaRelatorio> relatorios = new ArrayList<>();
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, pesquisa);
			ResultSet rs = statement.executeQuery();
			
			
			while (rs.next()){
				PesquisaRelatorio relatorio = new PesquisaRelatorio();
				relatorio.setNome(rs.getString("curso"));
				relatorio.setQuantidade(rs.getInt("quantidade"));
				relatorios.add(relatorio);
			}	
			rs.close();
			statement.close();
			
			return relatorios;
		} catch (SQLException e) {
			System.out.println("Falha ao obter os dados");
			return null;
		}
	}
	
	/**
	 * Obtém a categoria e o número de participante de cada curso
	 * @param nomePesquisa nome da pesquisa que irá obter os dados
	 * @return lista contendo a categoria e o número de participantes
	 */
	public List<PesquisaRelatorio> obtemQuestionados(String nomePesquisa){
		String sql = "select * from qtd_questionado(?)";
		List<PesquisaRelatorio> relatorios = new ArrayList<>();
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, nomePesquisa);
			
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()){
				PesquisaRelatorio relatorio = new PesquisaRelatorio();
				relatorio.setNome(rs.getString("questionado"));
				relatorio.setQuantidade(rs.getInt("quantidade"));
				relatorios.add(relatorio);
			}	
			rs.close();
			statement.close();
			
			return relatorios;
		} catch (SQLException e) {
			System.out.println("Falha ao obter os dados");
			return null;
		}
	}
	
	/**
	 * Obtém os cursos de determinado tipo
	 * @param curso tipo do curso 
	 * @param nomePesquisa que irá obter os dados
	 * @return os curso obtidos
	 */
	public List<String> obtemCursos(String curso, String nomePesquisa){
		String sql = "select distinct curso from pesquisa inner join titulo_pesquisa on "
				+ "titulo_pesquisa.codigo=pesquisa.codigo_pesquisa where curso ilike ? and "
				+ "titulo_pesquisa.nome=? group by curso";
		List<String> cursosList = new ArrayList<>();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, curso + "%");
			statement.setString(2, nomePesquisa);
			ResultSet rs = statement.executeQuery();

			while (rs.next())
				cursosList.add(rs.getString("curso"));
				
			rs.close();
			statement.close();
			return cursosList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * Obtém a categoria das pessoas que responderam a pesquisa
	 * @param nomePesquisa pesquisa que vai obter os dados
	 * @return lista contendo as categorias
	 */
	public List<String> obtemCategoria(String nomePesquisa){
		String sql = "select distinct questionado from pesquisa inner join titulo_pesquisa on "
				+ "titulo_pesquisa.codigo=pesquisa.codigo_pesquisa where titulo_pesquisa.nome=?"
				+ " group by questionado;";
		List<String> questionadosList = new ArrayList<>();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, nomePesquisa);
			ResultSet rs = statement.executeQuery();

			while (rs.next())
				questionadosList.add(rs.getString("questionado"));
				
			rs.close();
			statement.close();
			return questionadosList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}

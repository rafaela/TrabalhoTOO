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
	 * @param pesquisa
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
			//System.out.println("Falha ao inserir");
			e.printStackTrace();
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

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return codigosList;
	}
	
	/**
	 * Obtém os cursos e o número de participante de cada curso
	 * @return lista contendo os cursos e o número de participantes
	 */
	public List<PesquisaRelatorio> obtemCursos(){
		String sql = "select * from qtd_curso()";
		List<PesquisaRelatorio> relatorios = new ArrayList<>();
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sql);
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
	 * @return lista contendo a categoria e o número de participantes
	 */
	public List<PesquisaRelatorio> obtemQuestionados(){
		String sql = "select * from qtd_questionado()";
		List<PesquisaRelatorio> relatorios = new ArrayList<>();
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sql);
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
}

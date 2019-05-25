package too.trabalho.spa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import too.trabalho.avaliacao.jdbc.CriaConexao;
import too.trabalho.spa.tipos.Pesquisa;

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
		String sql = "insert into pesquisa (questionado, campus, curso) values (?, ?, ?)";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, pesquisa.getQuestionado());
			statement.setString(2, pesquisa.getCampus());
			statement.setString(3, pesquisa.getCurso());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			System.out.println("Falha ao inserir");
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
}

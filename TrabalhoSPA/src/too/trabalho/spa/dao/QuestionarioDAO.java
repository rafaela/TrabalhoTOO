package too.trabalho.spa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import too.trabalho.spa.jdbc.CriaConexao;
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
	 * @param questionario
	 * @param cod_pesquisa
	 */
	public void insere(int cod_pesquisa, int cod_pergunta, Questionario questionario){
		String sql = "insert into questionario (cod_pesquisa, cod_pergunta, cod_conceito)"
				+ "values (?, ?, ?)";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, cod_pesquisa);
			statement.setInt(2, cod_pergunta);
			statement.setInt(3, questionario.getConceito());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			//System.out.println("Falha ao inserir");
			e.printStackTrace();
		}
	}//cadastro
	
}

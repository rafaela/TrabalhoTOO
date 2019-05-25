package too.trabalho.spa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import too.trabalho.avaliacao.jdbc.CriaConexao;
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
	public void insere(Questionario questionario, int cod_pesquisa){
		String sql = "insert into questionario (cod_pesquisa,pergunta, conceito,"
				+ " cod_pergunta) values (?, ?, ?, ?)";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, cod_pesquisa);
			statement.setString(2, questionario.getPergunta());
			statement.setString(3, questionario.getConceito());
			statement.setInt(4, questionario.getCodigo());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			//System.out.println("Falha ao inserir");
			e.printStackTrace();
		}
	}//cadastro
	
}

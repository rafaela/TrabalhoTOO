package too.trabalho.spa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import too.trabalho.spa.jdbc.CriaConexao;
import too.trabalho.spa.tipos.Questionario;

/**
 * Classe para a manipulação da tabela de perguntas.
 * @author Rafaela
 *
 */
public class PerguntaDAO {
	Connection connection = new CriaConexao().conexao();
	
	/**
	 * Insere, no banco, os dados sobre as perguntas
	 * @param questionario
	 * @param cod_pesquisa
	 */
	public void insere(Questionario questionario){
		String sql = "insert into pergunta (pergunta, tipo_pergunta)"
				+ "values (?, ?)";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, questionario.getPergunta());
			statement.setString(2, questionario.getTipoPergunta());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			//System.out.println("Falha ao inserir");
			e.printStackTrace();
		}
	}//cadastro
	
	/**
	 * Obtém o código da pergunta no banco de dados
	 * @return uma lista contendo os códigos
	 */
	public List<Integer> obtemCodigo(){
		String sql = "select codigo from pergunta";
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

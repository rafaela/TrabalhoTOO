package too.trabalho.avaliacao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Cria a conexao do programa com o banco de dados
 * @author Rafaela 
 */
public class CriaConexao {
	public Connection conexao(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/spadb",
					"spaadmin", "SPA#Barbacena@IFSudesteMG");
		} catch (SQLException e) {
			System.out.println("Não foi possivel conectar ao banco");
		}
		
		return connection;
	}
	
}



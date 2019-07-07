package too.trabalho.spa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import too.trabalho.spa.jdbc.CriaConexao;
import too.trabalho.spa.tipos.Conceito;

/**
 * Classe para a manipulação da tabela de conceitos.
 * @author Rafaela
 *
 */
public class ConceitoDAO {
	Connection connection = new CriaConexao().conexao();
	
	/**
	 * Lista todos os conceitos cadastrados
	 * @return <code>List</code> contendo a pesquisa
	 */
	public List<Conceito> listaConceito(){
		List<Conceito> conceitoList = new ArrayList<>();
		String sql = "select * from conceito";

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Conceito conceito = new Conceito();
				conceito.setCodigo(rs.getInt("codigo"));
				conceito.setConceito(rs.getString("nome"));
				conceito.setPeso(rs.getInt("peso"));
				
				conceitoList.add(conceito);

			}

			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conceitoList;
	}//listaConceito

}

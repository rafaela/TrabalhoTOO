package too.trabalho.spa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import too.trabalho.spa.jdbc.CriaConexao;
import too.trabalho.spa.tipos.TipoPesquisa;

public class TituloPesquisaDAO {
	Connection connection = new CriaConexao().conexao();
	
	/**
	 * Insere, no banco, o título da pesquisa e o código do arquivo
	 * @param tp Tipo da pesquisa
	 */
	public void insere(TipoPesquisa tp){
		String sql = "insert into titulo_pesquisa (nome, hash)"
				+ "values (?, ?)";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, tp.getNome());
			statement.setString(2, tp.getHash());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			//System.out.println("Falha ao inserir");
			e.printStackTrace();
		}
	}//cadastro
	
	/**
	 * Verifica se um determinado arquivo já está salvo no banco
	 * @param hash
	 * @return o título, o código e o hash do arquivo inserido.
	 */
	public TipoPesquisa pesquisar(String hash){
		TipoPesquisa tp = new TipoPesquisa();
		String sql =  "select * from titulo_pesquisa where hash = ?";
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, hash);
			
			ResultSet rs = statement.executeQuery();
			rs.next();
			if(rs.next() != false){
				tp.setCodigo(rs.getInt("codigo"));
				tp.setNome(rs.getString("nome"));
				tp.setHash(rs.getString("hash"));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tp;
	}
}

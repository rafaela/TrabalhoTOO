package too.trabalho.spa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			System.out.println("Falha ao inserir na tabela titulo");
		}
	}//cadastro
	
	/**
	 * Verifica se um determinado arquivo já está salvo no banco
	 * @param hash identificação do arquivo
	 * @return o título, o código e o hash do arquivo inserido.
	 */
	public TipoPesquisa pesquisar(String hash){
		String sql =  "select * from titulo_pesquisa where hash=?";
		
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, hash);
			
			ResultSet rs = statement.executeQuery();
	
			if(rs.next()){
				TipoPesquisa tp = new TipoPesquisa();
	
				tp.setCodigo(rs.getInt("codigo"));
				tp.setNome(rs.getString("nome"));
				tp.setHash(rs.getString("hash"));

				rs.close();
				statement.close();
				return tp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Obtém os nomes das pesquisas cadastradas
	 * @return lista com o nome das pesquisas
	 */
	public List<String> obtemNome(){
		String sql =  "select nome from titulo_pesquisa";
		
		PreparedStatement statement;
		try {
			List<String> nomes = new ArrayList<>();
			
			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				nomes.add(rs.getString("nome"));
			}
			rs.close();
			statement.close();
			return nomes;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

package livraria.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import livraria.java.ConnectionFactory;
import livraria.java.models.Livro;

public class LivroDao {
	public ArrayList<Livro> getLivros(String categoria) {
		Connection con = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(getSelectQuery(categoria));
			if (categoria != null && categoria.isEmpty())
				ps.setString(1, categoria);
			
			ArrayList<Livro> livros = new ArrayList<Livro>();
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				livros.add(parseLivro(rs));
			
			rs.close();
			ps.close();
			con.close();
			return livros;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				throw new RuntimeException(e2);
			}
		}
	}

	private static String getSelectQuery(String categoria) {
		if (categoria == null || categoria.isEmpty())
			return "SELECT * FROM bdlivraria.tblivros;";
		
		return "SELECT * FROM bdlivraria.tblivros WHERE `categoria` = ?;";
	}
	
	private static Livro parseLivro(ResultSet rs) throws SQLException {
		Livro livro = new Livro();
		livro.setId(rs.getInt("codlivro"));
		livro.setTitulo(rs.getString("titulo"));
		livro.setAutor(rs.getString("autor"));
		livro.setCategoria(rs.getString("categoria"));
		livro.setValor(rs.getFloat("valor"));
		return livro;
	}
	
	public ArrayList<String> getCategorias(){
		Connection con = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM bdlivraria.tbcategorias;");
			
			ArrayList<String> livros = new ArrayList<String>();
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				livros.add(rs.getString(0));
			
			rs.close();
			ps.close();
			return livros;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				throw new RuntimeException(e2);
			}
		}
	}
	
	public void removeLivro(int id) {
		Connection con = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM `bdlivraria`.`tblivros` WHERE `codlivro` = '?';");
			ps.setInt(1, id);
			
			ps.executeQuery().close();			
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				throw new RuntimeException(e2);
			}
		}
	}
	
	public static void writeLivro(Livro livro) {
		Connection con = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO `bdlivraria`.`tblivros` (`titulo`, `autor`, `categoria`, `valor`) VALUES ('?', '?', '?', '?');");
			ps.setString(1, livro.getTitulo());
			ps.setString(2, livro.getAutor());
			ps.setString(3, livro.getCategoria());
			ps.setFloat(4, livro.getValor());
			
			ps.executeQuery().close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				throw new RuntimeException(e2);
			}
		}
	}
}

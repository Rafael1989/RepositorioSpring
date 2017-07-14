package br.com.caelum.tarefas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.caelum.tarefas.ConnectionFactory;
import br.com.caelum.tarefas.exception.DAOException;
import br.com.caelum.tarefas.model.Usuario;

public class UsuarioDAO {
	
	private Connection connection;

	public UsuarioDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public boolean existeUsuario(Usuario usuario) {
		try {
			String sql = "select * from usuarios where id = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, usuario.getId());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				return true;
			}
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return false;
	}

}

package br.com.caelum.tarefas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.tarefas.ConnectionFactory;
import br.com.caelum.tarefas.exception.DAOException;
import br.com.caelum.tarefas.model.Tarefa;

public class TarefaDAO {
	
	private Connection connection;

	public TarefaDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Tarefa tarefa) {
		String sql = "insert into tarefas "+
				"(descricao,finalizado,dataFinalizacao)"+
				" values(?,?,?)";
		try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.setDate(3, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));
			
			stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new DAOException(e);
		}
	}

	public List<Tarefa> lista() {
		try{
			List<Tarefa> tarefas = new ArrayList<>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from tarefas where descricao like 'C%'");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getLong("id"));
				tarefa.setDescricao(rs.getString("nome"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataFinalizacao"));
				tarefa.setDataFinalizacao(data);
				
				tarefas.add(tarefa);
			}
			
			rs.close();
			stmt.close();
			return tarefas;
		}catch(SQLException e){
			throw new DAOException(e);
		}
	}
	
}

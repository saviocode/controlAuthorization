package com.controleAutorizacao.dao;

import com.controleAutorizacao.conexao.Conexao;
import com.controleAutorizacao.entidade.Paciente;
import com.controleAutorizacao.repository.PacienteDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao extends DaoCrud<Paciente> implements PacienteDAO {
    private Connection connection;

    @Override
    public String getEntityName() {
        return "Paciente";
    }

    public PacienteDao() {
        connection = Conexao.getConnection();
    }

    public void salvarPaciente(Paciente paciente) {
        try {
            String sql = "insert into " + getEntityName() + "(cpf, idade, nome, sexo) values(?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, paciente.getCpf());
            ps.setInt(2, paciente.getIdade());
            ps.setString(3, paciente.getNome());
            ps.setString(4, paciente.getSexo());
            ps.execute();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            ex.printStackTrace();
        }

    }

    public List<Paciente> listarPaciente() {
        List<Paciente> lista = new ArrayList<Paciente>();
        try {
            String sql = "select * from " + getEntityName();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setCpf(rs.getString("cpf"));
                paciente.setIdade(rs.getInt("idade"));
                paciente.setNome(rs.getString("nome"));
                paciente.setSexo(rs.getString("sexo"));

                lista.add(paciente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }


    public void atualiza(Paciente paciente) {
        try {
            String sql = "update " + getEntityName() + " set cpf = ?, idade = ?, nome = ?, sexo = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, paciente.getCpf());
            ps.setInt(2, paciente.getIdade());
            ps.setString(3, paciente.getNome());
            ps.setString(4, paciente.getSexo());
            ps.execute();
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }

        }
    }

    public void excluir(String id) {
        try {
            String sql = "delete from " + getEntityName() + " where id = '" + id + "'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute();
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        }
    }
}

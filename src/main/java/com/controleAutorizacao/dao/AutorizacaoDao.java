package com.controleAutorizacao.dao;

import com.controleAutorizacao.conexao.Conexao;
import com.controleAutorizacao.entidade.Autorizacao;
import com.controleAutorizacao.entidade.Paciente;
import com.controleAutorizacao.entidade.Procedimento;
import com.controleAutorizacao.repository.AutorizacaoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorizacaoDao implements AutorizacaoDAO {
    private Connection connection;

    public String getEntityName() {
        return "Autorizacao";
    }

    public AutorizacaoDao() {
        connection = Conexao.getConnection();
    }

    public void salvarAutorizacao(Autorizacao autorizacao) {
        try {
            String sql = "insert into " + getEntityName() + "(autorizado, data, paciente_id, procedimento_id) values(?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, autorizacao.getAutorizado());
            ps.setDate(2, (Date) autorizacao.getData());
            ps.setInt(3, autorizacao.getPaciente().getId());
            ps.setInt(4, autorizacao.getProcedimento().getId());
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

    public List<Autorizacao> listarAutorizacao() {
        List<Autorizacao> lista = new ArrayList<Autorizacao>();
        try {
            String sql = "select * from " + getEntityName();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Autorizacao autorizacao = new Autorizacao();
                autorizacao.setAutorizado(rs.getBoolean("autorizado"));
                autorizacao.setData(rs.getDate("data"));
                autorizacao.setPaciente((Paciente) rs.getObject("paciente_id"));
                autorizacao.setProcedimento((Procedimento) rs.getObject("procedimento_id"));

                lista.add(autorizacao);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }


    public void atualiza(Autorizacao autorizacao) {
        try {
            String sql = "update " + getEntityName() + " set autorizado = ?, data = ?, paciente_id = ?, procedimento_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, autorizacao.getAutorizado());
            ps.setDate(2, (Date) autorizacao.getData());
            ps.setInt(3, autorizacao.getPaciente().getId());
            ps.setInt(4, autorizacao.getProcedimento().getId());
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

    public Autorizacao buscarPorId(String id) {
        try {
            String sql = "select * from " + getEntityName() + " where idEndereco = " + id + " ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Autorizacao autorizacao = new Autorizacao();
                autorizacao.setAutorizado(rs.getBoolean("autorizado"));
                autorizacao.setData(rs.getDate("data"));
                autorizacao.setPaciente((Paciente) rs.getObject("paciente_id"));
                autorizacao.setProcedimento((Procedimento) rs.getObject("procedimento_id"));
                return autorizacao;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


}

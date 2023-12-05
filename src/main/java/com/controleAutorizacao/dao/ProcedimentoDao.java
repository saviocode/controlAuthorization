package com.controleAutorizacao.dao;

import com.controleAutorizacao.conexao.Conexao;
import com.controleAutorizacao.entidade.Procedimento;
import com.controleAutorizacao.repository.ProcedimentoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcedimentoDao extends DaoCrud<Procedimento> implements ProcedimentoDAO {
    private Connection connection;
    @Override
    public String getEntityName() {
        return "Procedimento";
    }
    public ProcedimentoDao() {
        connection = Conexao.getConnection();
    }

    public void salvarProcedimento(Procedimento procedimento) {
        try {
            String sql = "insert into " + getEntityName() + "(codigo, descricao, nome) values(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, procedimento.getCodigo());
            ps.setString(2, procedimento.getDescricao());
            ps.setString(3, procedimento.getNome());
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

    public List<Procedimento> listarProcedimento() {
        List<Procedimento> lista = new ArrayList<>();
        try {
            String sql = "select * from " + getEntityName();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Procedimento procedimento = new Procedimento();
                procedimento.setCodigo(rs.getInt("codigo"));
                procedimento.setNome(rs.getString("nome"));
                procedimento.setDescricao(rs.getString("descricao"));
                lista.add(procedimento);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }


    public void atualiza(Procedimento procedimento) {
        try {
            String sql = "update " + getEntityName() + " set codigo = ?, descricao = ?, nome = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, procedimento.getCodigo());
            ps.setString(2, procedimento.getDescricao());
            ps.setString(3, procedimento.getNome());
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

package com.controleAutorizacao.dao.jdbc;

import com.controleAutorizacao.repository.ProcedimentoDAO;
import com.controleAutorizacao.entidade.Procedimento;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ProcedimentoJDBC extends JDBCCrud<Procedimento> implements ProcedimentoDAO {
    @Override
    public String getEntityName() {
        return "Procedimento";
    }

    public Procedimento inserirProcedimento(Procedimento procedimento) {
        String descricao = procedimento.getDescricao();
        String nome = procedimento.getNome();

        String sql = "INSERT INTO " + getEntityName() + " (descricao,nome) VALUES (" + descricao + ", " + nome + ")";
        Query query = super.getQuery(sql);
        query.setParameter("descricao", descricao);
        query.setParameter("nome", nome);

        super.close();
        return procedimento;
    }

    public List<Procedimento> buscarProcedimento(Procedimento procedimento) {
        List<Procedimento> listaProcedimento = new ArrayList<>();
        String descricao = procedimento.getDescricao();
        String nome = procedimento.getNome();

        String sql = "select * from " + getEntityName();
        Query query = super.getQuery(sql);
        query.setParameter("id", descricao);
        query.setParameter("sexo", nome);

        List list = query.getResultList();
        for (Object object : list) {
            listaProcedimento.add((Procedimento) object);
        }
        super.close();
        return listaProcedimento;
    }
}

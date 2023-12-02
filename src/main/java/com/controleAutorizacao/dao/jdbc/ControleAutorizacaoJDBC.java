package com.controleAutorizacao.dao.jdbc;

import com.controleAutorizacao.dao.ControleAutorizacaoDAO;
import com.controleAutorizacao.entidade.ControleAutorizacao;
import com.controleAutorizacao.entidade.Paciente;
import com.controleAutorizacao.entidade.Procedimento;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

public class ControleAutorizacaoJDBC extends JDBCCrud<ControleAutorizacao> implements ControleAutorizacaoDAO {
    @Override
    public String getEntityName() {
        return "ControleAutorizacao";
    }

    @Override
    public List<ControleAutorizacao> buscarAutorizacao(Paciente paciente, Procedimento procedimento) {
        List<ControleAutorizacao> listaControleAutorizacao = new ArrayList<>();
        int idProcedimento = 0;
        String sexo = null;
        int idade = 0;
        try {
            idProcedimento = procedimento.getId();
            sexo = paciente.getSexo();
            idade = paciente.getIdade();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String jpql = "select c from " + getEntityName()
                + " c where c.procedimento.id =:id AND c.sexo =:sexo AND c.idade =:idade";
        Query query = super.getQuery(jpql);
        query.setParameter("id", idProcedimento);
        query.setParameter("sexo", sexo);
        query.setParameter("idade", idade);

        @SuppressWarnings("rawtypes") List list = query.getResultList();
        for (Object object : list) {
            listaControleAutorizacao.add((ControleAutorizacao) object);
        }
        super.close();
        return listaControleAutorizacao;
    }
}

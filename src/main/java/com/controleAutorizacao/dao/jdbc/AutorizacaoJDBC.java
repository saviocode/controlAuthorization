package com.controleAutorizacao.dao.jdbc;

import com.controleAutorizacao.repository.AutorizacaoDAO;
import com.controleAutorizacao.entidade.Autorizacao;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AutorizacaoJDBC extends JDBCCrud<Autorizacao> implements AutorizacaoDAO {
    @Override
    public String getEntityName() {
        return "Autorizacao";
    }
    public boolean inserirAutorizacao(Autorizacao autorizacao) {
        Boolean autorizado = autorizacao.getAutorizado();
        Date data = autorizacao.getData();
        int idPaciente = autorizacao.getPaciente().getId();
        int idProcedimento =  autorizacao.getProcedimento().getId();

        String sql = "INSERT INTO controle_autorizacao (idade, permitido, sexo, procedimento_id) VALUES (" + autorizado + ", " + data + ", " + idPaciente + ", " + idProcedimento + ")";
        Query query = super.getQuery(sql);
        query.setParameter("autorizado", autorizado);
        query.setParameter("data", data);
        query.setParameter("paciente_id", idPaciente);
        query.setParameter("procedimento_id", idProcedimento);

        super.close();
        return  true;
    }

    public List<Autorizacao> buscarAutorizacao(Autorizacao autorizacao) {
        List<Autorizacao> listaAutorizacao = new ArrayList<>();
        Boolean autorizado = autorizacao.getAutorizado();
        Date data = autorizacao.getData();
        int idPaciente = autorizacao.getPaciente().getId();
        int idProcedimento =  autorizacao.getProcedimento().getId();

        String sql = "select * from " + getEntityName();
        Query query = super.getQuery(sql);
        query.setParameter("autorizado", autorizado);
        query.setParameter("data", data);
        query.setParameter("paciente_id", idPaciente);
        query.setParameter("procedimento_id", idProcedimento);

        List list = query.getResultList();
        for (Object object : list) {
            listaAutorizacao.add((Autorizacao) object);
        }
        super.close();
        return listaAutorizacao;
    }


}

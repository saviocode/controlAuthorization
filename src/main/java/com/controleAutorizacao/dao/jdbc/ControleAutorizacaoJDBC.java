package com.controleAutorizacao.dao.jdbc;

import com.controleAutorizacao.repository.ControleAutorizacaoDAO;
import com.controleAutorizacao.entidade.ControleAutorizacao;
import com.controleAutorizacao.entidade.Paciente;
import com.controleAutorizacao.entidade.Procedimento;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ControleAutorizacaoJDBC extends JDBCCrud<ControleAutorizacao> implements ControleAutorizacaoDAO {
    @Override
    public String getEntityName() {
        return "ControleAutorizacao";
    }

    public ControleAutorizacao inserirControleAutorizacao(ControleAutorizacao controleAutorizacao) {
        int idade = controleAutorizacao.getIdade();
        Boolean isPermitido = controleAutorizacao.getPermitido();
        String sexo = controleAutorizacao.getSexo();
        int idProcedimento =  controleAutorizacao.getProcedimento().getId();

        String sql = "INSERT INTO controle_autorizacao (idade, permitido, sexo, procedimento_id) VALUES (" + idade + ", " + isPermitido + ", " + sexo + ", " + idProcedimento + ")";
        Query query = super.getQuery(sql);
        query.setParameter("idade", idade);
        query.setParameter("permitido", isPermitido);
        query.setParameter("sexo", sexo);
        query.setParameter("procedimento_id", idProcedimento);
        System.out.println("Usuário inserido com sucesso!");
        super.close();
        return controleAutorizacao;
    }

    @Override
    public List<ControleAutorizacao> buscarAutorizacao(Paciente paciente, Procedimento procedimento) {
        List<ControleAutorizacao> listaControleAutorizacao = new ArrayList<>();
        int idProcedimento =  procedimento.getId();
        String sexo = paciente.getSexo();
        int idade = paciente.getIdade();

        String sql = "select c from " + getEntityName()
                + " c where c.procedimento.id =:id AND c.sexo =:sexo AND c.idade =:idade";
        Query query = super.getQuery(sql);
        query.setParameter("id", idProcedimento);
        query.setParameter("sexo", sexo);
        query.setParameter("idade", idade);

        List list = query.getResultList();
        for (Object object : list) {
            listaControleAutorizacao.add((ControleAutorizacao) object);
        }
        super.close();
        return listaControleAutorizacao;
    }
}

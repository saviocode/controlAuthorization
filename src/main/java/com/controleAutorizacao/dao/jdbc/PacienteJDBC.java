package com.controleAutorizacao.dao.jdbc;

import com.controleAutorizacao.repository.PacienteDAO;
import com.controleAutorizacao.entidade.Paciente;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class PacienteJDBC extends JDBCCrud<Paciente> implements PacienteDAO {
    @Override
    public String getEntityName() {
        return "Paciente";
    }

    public Paciente inserirPaciente(Paciente paciente) {
        String cpf = paciente.getCpf();
        int idade = paciente.getIdade();
        String nome = paciente.getNome();
        String sexo = paciente.getSexo();

        String sql = "INSERT INTO " + getEntityName() + " (cpf,idade,nome,sexo) " +
                "VALUES (" + cpf + ", " + idade + ", " + nome + ", " + sexo + ")";
        Query query = super.getQuery(sql);
        query.setParameter("cpf", cpf);
        query.setParameter("idade", idade);
        query.setParameter("nome", nome);
        query.setParameter("sexo", sexo);

        super.close();
        return paciente;
    }

    public List<Paciente> buscarProcedimento(Paciente paciente) {
        List<Paciente> listaPaciente = new ArrayList<>();
        String cpf = paciente.getCpf();
        int idade = paciente.getIdade();
        String nome = paciente.getNome();
        String sexo = paciente.getSexo();

        String sql = "select * from " + getEntityName();
        Query query = super.getQuery(sql);
        query.setParameter("cpf", cpf);
        query.setParameter("idade", idade);
        query.setParameter("nome", nome);
        query.setParameter("sexo", sexo);

        List list = query.getResultList();
        for (Object object : list) {
            listaPaciente.add((Paciente) object);
        }
        super.close();
        return listaPaciente;
    }
}

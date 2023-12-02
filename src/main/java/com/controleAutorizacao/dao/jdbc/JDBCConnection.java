package com.controleAutorizacao.dao.jdbc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JDBCConnection {
    private EntityManagerFactory conexao;

    private EntityManagerFactory conectar() {
        try {
            if (conexao != null && conexao.isOpen()) {
                return conexao;
            }
        } catch (Exception e) {
        }

        conexao = Persistence.createEntityManagerFactory("controleAutorizacao");
        return conexao;
    }
    protected EntityManager getEntityManager() {
        return conectar().createEntityManager();
    }
    protected Query getQuery(String jpql) {

        return this.getEntityManager().createQuery(jpql);
    }
    protected void close() {
        this.conexao.close();
    }
}
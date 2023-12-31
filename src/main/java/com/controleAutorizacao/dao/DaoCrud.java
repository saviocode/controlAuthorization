package com.controleAutorizacao.dao;

import com.controleAutorizacao.conexao.JDBCConnection;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public  class DaoCrud<T> extends JDBCConnection {

    public boolean salvar(T t) {
        try {
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(T t) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @SuppressWarnings("unchecked")
    public List<T> buscar() {
        String jpql = "select c from " + getEntityName() + " c";
        Query query = super.getQuery(jpql);
        @SuppressWarnings("rawtypes") List list = query.getResultList();

        List<T> listObjetos = new ArrayList<T>();
        for (Object object : list) {
            listObjetos.add((T) object);
        }
        super.close();
        return listObjetos;
    }

    @SuppressWarnings("unchecked")
    public T buscarPorId(int id) {
        String jpql = "select c from " + getEntityName() + " c where c.id = " + id;
        Query query = super.getQuery(jpql);
        @SuppressWarnings("rawtypes") List list = query.getResultList();
        for (Object object : list) {
            return ((T) object);
        }
        super.close();
        return null;
    }

    public boolean excluirPorId(int id) {
        try {
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE " + getEntityName() + " c where c.id =:id ");
            query.setParameter("id", id);
            query.executeUpdate();
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<T> buscarPorNome(String nome) {
        List<T> lista = new ArrayList<>();
        String jpql = "select c from " + getEntityName() + " c where c.nome like :nome";
        Query query = super.getQuery(jpql);
        query.setParameter("nome", "".equals(nome) ? nome : nome + "%");

        @SuppressWarnings("rawtypes") List list = query.getResultList();
        for (Object object : list) {
            lista.add((T) object);
        }
        super.close();
        return lista;
    }

    public String getEntityName() {
        return null;
    }

}

package com.controleAutorizacao.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "paciente")
public class Paciente extends EntidadeGeneric implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(nullable = false, length = 14)
    private String nome;
    @Column(nullable = false, length = 11)
    private String cpf;
    @Column(nullable = false, length = 3)
    private int idade;
    @Column(nullable = false, length = 1)
    private String sexo;

    public Paciente(String nome, String cpf, int idade, String sexo) {
        this.nome = nome;
        this.cpf = cpf.replaceAll("[^0-9]", "");
        this.idade = idade;
        this.sexo = sexo;
    }

    public Paciente() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf.replaceAll("[^0-9]", "");
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}

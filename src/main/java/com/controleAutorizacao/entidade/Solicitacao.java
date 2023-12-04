package com.controleAutorizacao.entidade;

public class Solicitacao {

    private String nome;

    private Long cdProcedimento;

    private Long idade;

    private String sexo;

    private Boolean sucesso;

    public Solicitacao() {
    }

    public Solicitacao(Long cdProcedimento, Long idade, String sexo) {
        this.cdProcedimento = cdProcedimento;
        this.idade = idade;
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCdProcedimento() {
        return cdProcedimento;
    }

    public void setCdProcedimento(Long cdProcedimento) {
        this.cdProcedimento = cdProcedimento;
    }

    public Long getIdade() {
        return idade;
    }

    public void setIdade(Long idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Boolean getSucesso() {
        return sucesso;
    }

    public void setSucesso(Boolean sucesso) {
        this.sucesso = sucesso;
    }

}

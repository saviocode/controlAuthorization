package com.controleAutorizacao.entidade;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "regras")
public class Regra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 8)
    private Long cdProcedimento;

    @Column(length = 3)
    private Long idade;

    @Column(length = 1)
    private String sexo;

    @Column(length = 1)
    private Boolean permitido = Boolean.FALSE;

    @Column(length = 50)
    private String usuario;

    public Regra() {
    }

    public Regra(String usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getPermitido() {
        return permitido;
    }

    public void setPermitido(Boolean permitido) {
        this.permitido = permitido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
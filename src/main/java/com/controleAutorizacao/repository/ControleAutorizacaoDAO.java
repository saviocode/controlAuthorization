package com.controleAutorizacao.repository;

import com.controleAutorizacao.entidade.ControleAutorizacao;
import com.controleAutorizacao.entidade.Paciente;
import com.controleAutorizacao.entidade.Procedimento;
import java.util.List;

public interface ControleAutorizacaoDAO
{
	public List<ControleAutorizacao> buscarAutorizacao(Paciente paciente, Procedimento procedimento);
	public ControleAutorizacao inserirControleAutorizacao(ControleAutorizacao controleAutorizacao);
}

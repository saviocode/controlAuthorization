package com.controlauthorization.dao;

import com.controlauthorization.entity.ControleAutorizacao;
import com.controlauthorization.entity.Paciente;
import com.controlauthorization.entity.Procedimento;
import java.util.List;

public interface ControleAutorizacaoDAO
{
	public List<ControleAutorizacao> buscarAutorizacao(Paciente paciente, Procedimento procedimento);
}

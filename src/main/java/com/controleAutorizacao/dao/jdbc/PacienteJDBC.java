package com.controleAutorizacao.dao.jdbc;

import com.controleAutorizacao.dao.PacienteDAO;
import com.controleAutorizacao.entidade.Paciente;

public class PacienteJDBC extends JDBCCrud<Paciente> implements PacienteDAO
{
	@Override
	public String getEntityName()
	{
		return "Paciente";
	}
}

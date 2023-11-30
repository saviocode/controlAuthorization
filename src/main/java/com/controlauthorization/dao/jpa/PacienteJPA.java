package com.controlauthorization.dao.jpa;

import com.controlauthorization.dao.PacienteDAO;
import com.controlauthorization.entity.Paciente;

public class PacienteJPA extends JPAAbstract<Paciente> implements PacienteDAO
{
	@Override
	public String getEntityName()
	{
		return "Paciente";
	}
}

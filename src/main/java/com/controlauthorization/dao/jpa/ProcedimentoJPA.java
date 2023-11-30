package com.controlauthorization.dao.jpa;

import com.controlauthorization.dao.ProcedimentoDAO;
import com.controlauthorization.entity.Procedimento;

public class ProcedimentoJPA extends JPAAbstract<Procedimento> implements ProcedimentoDAO
{
	@Override
	public String getEntityName()
	{
		return "Procedimento";
	}
}

package com.controleAutorizacao.dao.jdbc;

import com.controleAutorizacao.dao.ProcedimentoDAO;
import com.controleAutorizacao.entidade.Procedimento;

public class ProcedimentoJDBC extends JDBCCrud<Procedimento> implements ProcedimentoDAO
{
	@Override
	public String getEntityName()
	{
		return "Procedimento";
	}
}

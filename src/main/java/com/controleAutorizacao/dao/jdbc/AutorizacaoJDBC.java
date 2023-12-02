package com.controleAutorizacao.dao.jdbc;

import com.controleAutorizacao.dao.AutorizacaoDAO;
import com.controleAutorizacao.entidade.Autorizacao;

public class AutorizacaoJDBC extends JDBCCrud<Autorizacao> implements AutorizacaoDAO
{
	@Override
	public String getEntityName()
	{
		return "Autorizacao";
	}
}

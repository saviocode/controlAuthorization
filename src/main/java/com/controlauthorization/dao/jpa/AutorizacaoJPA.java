package com.controlauthorization.dao.jpa;

import com.controlauthorization.dao.AutorizacaoDAO;
import com.controlauthorization.entity.Autorizacao;

public class AutorizacaoJPA extends JPAAbstract<Autorizacao> implements AutorizacaoDAO
{
	@Override
	public String getEntityName()
	{
		return "Autorizacao";
	}
}

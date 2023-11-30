package com.controlauthorization.services.controlAuthorization;

import com.controlauthorization.dao.jpa.ControleAutorizacaoJPA;
import com.controlauthorization.entity.ControleAutorizacao;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controleautorizacao/consulta")
public class Consulta extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void init()
	{

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setCharacterEncoding("UTF-8");

		List<ControleAutorizacao> controleAutorizacoes = new ControleAutorizacaoJPA().buscar();
		ObjectMapper objectMapper = new ObjectMapper();

		String controleAutorizacaoJson = objectMapper.writeValueAsString(controleAutorizacoes);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(controleAutorizacaoJson);
	}
}


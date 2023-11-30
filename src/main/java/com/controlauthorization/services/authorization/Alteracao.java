package com.controlauthorization.services.authorization;

import com.controlauthorization.dao.jpa.AutorizacaoJPA;
import com.controlauthorization.dao.jpa.PacienteJPA;
import com.controlauthorization.dao.jpa.ProcedimentoJPA;
import com.controlauthorization.entity.Autorizacao;
import com.controlauthorization.entity.Paciente;
import com.controlauthorization.entity.Procedimento;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet("/autorizacao/alteracao")
public class Alteracao extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void init()
	{

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setCharacterEncoding("UTF-8");

		try
		{
			String jsonPayload = request.getReader().lines()
				.reduce("", (accumulator, actual) -> accumulator + actual);

			JSONObject jsonObject = new JSONObject(jsonPayload);

			int idProcedimento = jsonObject.getInt("idProcedimento");
			Procedimento procedimento = new ProcedimentoJPA().buscarPorId(idProcedimento);
			int idPaciente = jsonObject.getInt("idPaciente");
			Paciente paciente = new PacienteJPA().buscarPorId(idPaciente);
			int idAutorizacao = jsonObject.getInt("idAutorizacao");
			Autorizacao autorizacao = new AutorizacaoJPA().buscarPorId(idAutorizacao);
			autorizacao.setProcedimento(procedimento);
			autorizacao.setPaciente(paciente);

			if (new AutorizacaoJPA().atualizar(autorizacao))
			{
				response.getWriter().write("Atualização realizado com sucesso!");
			}
			else
			{
				response.getWriter().write("Erro ao realizar a atualização!");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

package com.controleAutorizacao.services.servlet.autorizacao;

import com.controleAutorizacao.dao.jdbc.AutorizacaoJDBC;
import com.controleAutorizacao.dao.jdbc.PacienteJDBC;
import com.controleAutorizacao.dao.jdbc.ProcedimentoJDBC;
import com.controleAutorizacao.entidade.Autorizacao;
import com.controleAutorizacao.entidade.Paciente;
import com.controleAutorizacao.entidade.Procedimento;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/autorizacao/alteracao")
public class AlteracaoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");

        try {
            String jsonPayload = request.getReader().lines()
                    .reduce("", (accumulator, actual) -> accumulator + actual);

            JSONObject jsonObject = new JSONObject(jsonPayload);

            int idProcedimento = jsonObject.getInt("idProcedimento");
            Procedimento procedimento = new ProcedimentoJDBC().buscarPorId(idProcedimento);
            int idPaciente = jsonObject.getInt("idPaciente");
            Paciente paciente = new PacienteJDBC().buscarPorId(idPaciente);
            int idAutorizacao = jsonObject.getInt("idAutorizacao");
            Autorizacao autorizacao = new AutorizacaoJDBC().buscarPorId(idAutorizacao);
            autorizacao.setProcedimento(procedimento);
            autorizacao.setPaciente(paciente);

            if (new AutorizacaoJDBC().atualizar(autorizacao)) {
                response.getWriter().write("Atualização realizado com sucesso!");
            } else {
                response.getWriter().write("Erro ao realizar a atualização!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

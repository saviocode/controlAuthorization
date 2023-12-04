package com.controleAutorizacao.controller.autorizacao;

import com.controleAutorizacao.dao.jdbc.AutorizacaoJDBC;
import com.controleAutorizacao.dao.jdbc.PacienteJDBC;
import com.controleAutorizacao.dao.jdbc.ProcedimentoJDBC;
import com.controleAutorizacao.entidade.Autorizacao;
import com.controleAutorizacao.entidade.Paciente;
import com.controleAutorizacao.entidade.Procedimento;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/autorizacao/cadastro")
public class CadastroServlet extends HttpServlet {
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

            Autorizacao autorizacao = new Autorizacao(procedimento, paciente);
            //validar conforme o tipo de procedimento e paciente
            boolean autorizacaoCadastrada = autorizacao.validar();

            if (autorizacaoCadastrada) {
                if (new AutorizacaoJDBC().inserirAutorizacao(autorizacao)) {
                    JSONObject jsonResponse = new JSONObject();
                    jsonResponse.put("message", "Cadastro realizado com sucesso!");

                    response.setStatus(200);
                    response.setContentType("application/json");
                    response.getWriter().write(jsonResponse.toString());
                } else {
                    JSONObject jsonResponse = new JSONObject();
                    jsonResponse.put("message", "Erro ao realizar cadastro!");

                    response.setStatus(500);
                    response.setContentType("application/json");
                    response.getWriter().write(jsonResponse.toString());
                }
            } else {
                response.getWriter().write(
                        "Cadastro não permitido! Não existe um critério para permitir ou negar o procedimento!");
            }
        } catch (JSONException e) {
            JSONObject jsonResponse = new JSONObject();
            try {
                jsonResponse.put("message", "Cadastro não permitido! Não existe um critério para permitir ou negar o procedimento!");
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            }

            response.setStatus(400);
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse.toString());
        }
    }

}


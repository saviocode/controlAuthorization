package com.controleAutorizacao.services.servlet.procedimento;

import com.controleAutorizacao.dao.jdbc.ProcedimentoJDBC;
import com.controleAutorizacao.entidade.Procedimento;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/procedimento/consultaPorNome")
public class ConsultaPorNomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");

        try {
            String jsonPayload = request.getReader().lines()
                    .reduce("", (accumulator, actual) -> accumulator + actual);

            JSONObject jsonObject = new JSONObject(jsonPayload);

            String nomeProcedimento = jsonObject.getString("nomeProcedimento");
            List<Procedimento> procedimentos = new ProcedimentoJDBC().buscarPorNome(nomeProcedimento);
            ObjectMapper objectMapper = new ObjectMapper();

            String procedimentosJson = objectMapper.writeValueAsString(procedimentos);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(procedimentosJson);
        } catch (Exception e) {
            response.getWriter().write("Erro ao realizar consulta!");
        }
    }
}


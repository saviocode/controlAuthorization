package com.controleAutorizacao.services.servlet.autorizacao;

import com.controleAutorizacao.dao.jdbc.AutorizacaoJDBC;
import com.controleAutorizacao.entidade.Autorizacao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/autorizacao/consultaPorId")
public class ConsultaPorIdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");

        try {
            String jsonPayload = request.getReader().lines()
                    .reduce("", (accumulator, actual) -> accumulator + actual);

            JSONObject jsonObject = new JSONObject(jsonPayload);

            int idAutorizacao = jsonObject.getInt("idAutorizacao");
            Autorizacao autorizacao = new AutorizacaoJDBC().buscarPorId(idAutorizacao);
            ObjectMapper objectMapper = new ObjectMapper();

            String autorizacaoJson = objectMapper.writeValueAsString(autorizacao);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(autorizacaoJson);
        } catch (Exception e) {
            response.getWriter().write("Erro ao realizar consulta!");
        }
    }
}


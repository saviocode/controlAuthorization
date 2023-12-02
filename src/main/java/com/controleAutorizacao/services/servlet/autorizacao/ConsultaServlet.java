package com.controleAutorizacao.services.servlet.autorizacao;

import com.controleAutorizacao.dao.jdbc.AutorizacaoJDBC;
import com.controleAutorizacao.entidade.Autorizacao;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/autorizacao/consulta")
public class ConsultaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");

        List<Autorizacao> autorizacoes = new AutorizacaoJDBC().buscar();
        ObjectMapper objectMapper = new ObjectMapper();

        String autorizacoesJson = objectMapper.writeValueAsString(autorizacoes);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(autorizacoesJson);
    }
}


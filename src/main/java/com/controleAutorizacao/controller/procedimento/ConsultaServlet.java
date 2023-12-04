package com.controleAutorizacao.controller.procedimento;

import com.controleAutorizacao.dao.jdbc.ProcedimentoJDBC;
import com.controleAutorizacao.entidade.Procedimento;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/procedimento/consulta")
public class ConsultaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");

        List<Procedimento> procedimentos = new ProcedimentoJDBC().buscar();
        ObjectMapper objectMapper = new ObjectMapper();

        String procedimentosJson = objectMapper.writeValueAsString(procedimentos);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(procedimentosJson);
    }
}


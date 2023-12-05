package com.controleAutorizacao.controller.controleAutorizacao;

import com.controleAutorizacao.dao.ControleAutorizacaoDao;
import com.controleAutorizacao.entidade.ControleAutorizacao;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/controleautorizacao/consulta")
public class ConsultaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");

        List<ControleAutorizacao> controleAutorizacoes = new ControleAutorizacaoDao().buscar();
        ObjectMapper objectMapper = new ObjectMapper();

        String controleAutorizacaoJson = objectMapper.writeValueAsString(controleAutorizacoes);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(controleAutorizacaoJson);
    }
}


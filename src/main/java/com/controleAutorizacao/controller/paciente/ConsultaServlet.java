package com.controleAutorizacao.controller.paciente;

import com.controleAutorizacao.dao.PacienteDao;
import com.controleAutorizacao.entidade.Paciente;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/paciente/consulta")
public class ConsultaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");

        List<Paciente> pacientes = new PacienteDao().buscar();
        ObjectMapper objectMapper = new ObjectMapper();

        String procedimentosJson = objectMapper.writeValueAsString(pacientes);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(procedimentosJson);
    }
}


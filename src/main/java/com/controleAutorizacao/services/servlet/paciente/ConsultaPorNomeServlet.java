package com.controleAutorizacao.services.servlet.paciente;

import com.controleAutorizacao.dao.jdbc.PacienteJDBC;
import com.controleAutorizacao.entidade.Paciente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/paciente/consultaPorNome")
public class ConsultaPorNomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");

        try {
            String jsonPayload = request.getReader().lines()
                    .reduce("", (accumulator, actual) -> accumulator + actual);

            JSONObject jsonObject = new JSONObject(jsonPayload);

            String nomePaciente = jsonObject.getString("nomePaciente");
            List<Paciente> pacientes = new PacienteJDBC().buscarPorNome(nomePaciente);
            ObjectMapper objectMapper = new ObjectMapper();

            String pacientesJson = objectMapper.writeValueAsString(pacientes);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(pacientesJson);
        } catch (Exception e) {
            response.getWriter().write("Erro ao realizar consulta!");
        }
    }
}


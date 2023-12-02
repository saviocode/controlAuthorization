package com.controleAutorizacao.services.servlet.paciente;

import com.controleAutorizacao.dao.jdbc.PacienteJDBC;
import com.controleAutorizacao.entidade.Paciente;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/paciente/cadastro")
public class CadastroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");

        try {
            String jsonPayload = request.getReader().lines()
                    .reduce("", (accumulator, actual) -> accumulator + actual);

            JSONObject jsonObject = new JSONObject(jsonPayload);

            String nome = jsonObject.getString("nome");
            String cpf = jsonObject.getString("cpf");
            int idade = jsonObject.getInt("idade");
            String sexo = jsonObject.getString("sexo");

            Paciente paciente = new Paciente(nome, cpf, idade, sexo);
            if (new PacienteJDBC().salvar(paciente)) {
                response.getWriter().write("Cadastro realizado com sucesso!");
            } else {
                response.getWriter().write("Erro ao realizar cadastro!");
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}


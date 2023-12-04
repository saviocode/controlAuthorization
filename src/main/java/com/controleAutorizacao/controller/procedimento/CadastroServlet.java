package com.controleAutorizacao.controller.procedimento;

import com.controleAutorizacao.dao.jdbc.ProcedimentoJDBC;
import com.controleAutorizacao.entidade.Procedimento;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/procedimento/cadastro")
public class CadastroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");

        try {
            String jsonPayload = request.getReader().lines()
                    .reduce("", (accumulator, actual) -> accumulator + actual);

            JSONObject jsonObject = new JSONObject(jsonPayload);

            String nome = jsonObject.getString("nome");
            String descricao = jsonObject.getString("descricao");
            Procedimento procedimento = new Procedimento(nome, descricao);
            if (new ProcedimentoJDBC().salvar(procedimento)) {
                response.getWriter().write("Cadastro realizado com sucesso!");
            } else {
                response.getWriter().write("Erro ao realizar cadastro!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

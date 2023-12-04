package com.controleAutorizacao.controller.controleAutorizacao;

import com.controleAutorizacao.dao.jdbc.ControleAutorizacaoJDBC;
import com.controleAutorizacao.dao.jdbc.ProcedimentoJDBC;
import com.controleAutorizacao.entidade.ControleAutorizacao;
import com.controleAutorizacao.entidade.Procedimento;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controleautorizacao/cadastro")
public class CadastroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");

        try {
            String jsonPayload = request.getReader().lines()
                    .reduce("", (accumulator, actual) -> accumulator + actual);

            JSONObject jsonObject = new JSONObject(jsonPayload);

            int idade = jsonObject.getInt("idade");
            String sexo = jsonObject.getString("sexo");
            boolean permitido = jsonObject.getBoolean("permitido");
            int idProcedimento = jsonObject.getInt("idProcedimento");
            Procedimento procedimento = new ProcedimentoJDBC().buscarPorId(idProcedimento);
            ControleAutorizacao controleAutorizacao = new ControleAutorizacao(procedimento, idade, sexo,
                    permitido);

            if (new ControleAutorizacaoJDBC().salvar(controleAutorizacao)) {
                response.getWriter().write("Cadastro realizado com sucesso!");
            } else {
                response.getWriter().write("Erro ao realizar cadastro!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

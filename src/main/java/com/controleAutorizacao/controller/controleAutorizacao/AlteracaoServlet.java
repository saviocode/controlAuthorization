package com.controleAutorizacao.controller.controleAutorizacao;

import com.controleAutorizacao.dao.ControleAutorizacaoDao;
import com.controleAutorizacao.dao.ProcedimentoDao;
import com.controleAutorizacao.entidade.ControleAutorizacao;
import com.controleAutorizacao.entidade.Procedimento;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controleautorizacao/alteracao")
public class AlteracaoServlet extends HttpServlet {
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
            Procedimento procedimento = new ProcedimentoDao().buscarPorId(idProcedimento);
            int idControleAutorizacao = jsonObject.getInt("idControleAutorizacao");
            ControleAutorizacao controleAutorizacao = new ControleAutorizacaoDao().buscarPorId(
                    idControleAutorizacao);
            controleAutorizacao.setIdade(idade);
            controleAutorizacao.setSexo(sexo);
            controleAutorizacao.setPermitido(permitido);
            controleAutorizacao.setProcedimento(procedimento);

            if (new ControleAutorizacaoDao().atualizar(controleAutorizacao)) {
                response.getWriter().write("Atualização realizado com sucesso!");
            } else {
                response.getWriter().write("Erro ao realizar a atualização!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

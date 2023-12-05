package com.controleAutorizacao.controller.procedimento;

import com.controleAutorizacao.dao.ProcedimentoDao;
import com.controleAutorizacao.entidade.Procedimento;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/procedimento/alteracao")
public class AlteracaoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");

        try {
            String jsonPayload = request.getReader().lines()
                    .reduce("", (accumulator, actual) -> accumulator + actual);

            JSONObject jsonObject = new JSONObject(jsonPayload);

            String nome = jsonObject.getString("nome");
            String descricao = jsonObject.getString("descricao");
            int idProcedimento = jsonObject.getInt("idProcedimento");
            Procedimento procedimento = new ProcedimentoDao().buscarPorId(idProcedimento);
            procedimento.setNome(nome);
            procedimento.setDescricao(descricao);
            if (new ProcedimentoDao().atualizar(procedimento)) {
                response.getWriter().write("Atualização realizado com sucesso!");
            } else {
                response.getWriter().write("Erro ao realizar a atualização!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

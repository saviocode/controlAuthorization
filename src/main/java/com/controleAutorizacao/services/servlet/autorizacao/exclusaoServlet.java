package com.controleAutorizacao.services.servlet.autorizacao;

import com.controleAutorizacao.dao.jdbc.AutorizacaoJDBC;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/autorizacao/excluir")
public class exclusaoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");

        try {
            String jsonPayload = request.getReader().lines()
                    .reduce("", (accumulator, actual) -> accumulator + actual);

            JSONObject jsonObject = new JSONObject(jsonPayload);

            int idAutorizacao = jsonObject.getInt("idAutorizacao");
            if (new AutorizacaoJDBC().excluirPorId(idAutorizacao)) {
                response.getWriter().write("Autorização excluida com sucesso!");
            } else {
                response.getWriter().write("Erro ao excluir a autorização!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

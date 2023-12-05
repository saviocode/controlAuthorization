package com.controleAutorizacao.controller.paciente;

import com.controleAutorizacao.dao.PacienteDao;
import com.controleAutorizacao.entidade.Paciente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/paciente")
public class PacienteServlet extends HttpServlet {
    private PacienteDao dao = new PacienteDao();
    private Paciente paciente = new Paciente();

    public PacienteServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accao = request.getParameter("accao") != null ? request.getParameter("accao") : "listar";
        String id = request.getParameter("paci");

        RequestDispatcher view = request.getRequestDispatcher("/cadastroPaciente.jsp");
        if (accao.equals("delete")) {
            dao.excluir(id);
            request.setAttribute("pacientes", dao.listarPaciente());

        } else if (accao.equals("edit")) {
            Paciente pacienteEntidade = dao.buscarPorId(Integer.parseInt(id));
            request.setAttribute("paci", pacienteEntidade);
            request.setAttribute("pacientes", dao.listarPaciente());

        } else if (accao.equals("listar")) {
            request.setAttribute("pacientes", dao.listarPaciente());

        }
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accao = request.getParameter("accao");

        if (accao != null && accao.equals("reset")) {
            RequestDispatcher view
                    = request.getRequestDispatcher("/cadastroPaciente.jsp");
            request.setAttribute("pacientes", dao.listarPaciente());
            view.forward(request, response);
        } else {

            String id = request.getParameter("id");
            String cpf = request.getParameter("cpf");
            String idade = request.getParameter("idade");
            String nome = request.getParameter("nome");
            String sexo = request.getParameter("sexo");

            Paciente paciente1 = new Paciente();
            paciente1.setId(Math.toIntExact(!id.isEmpty() ? Long.parseLong(id) : null));
            paciente1.setCpf(cpf);
            paciente1.setIdade(Integer.parseInt(idade));
            paciente1.setNome(nome);
            paciente1.setSexo(sexo);

            if (sexo != null && !sexo.isEmpty()) {
                paciente1.setSexo(sexo);
            }
            if (!id.isEmpty()) {
                paciente1.setIdade(Integer.parseInt(idade));

            }
            boolean podeInserir = true;

            if (nome == null || nome.isEmpty()) {
                request.setAttribute("msg", "Preencha o Campo Nome !");
                podeInserir = false;

            } else if (idade.isEmpty()) {
                request.setAttribute("msg", "Preencha o Campo Idade!");
                podeInserir = false;

            } else if (sexo == null || sexo.isEmpty()) {
                request.setAttribute("msg", "Preencha o Campo Sexo!");
                podeInserir = false;

            }
            if (!podeInserir) {
                request.setAttribute("prod", paciente1);
            }

            try {
                RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");// para continuar na mesma pagina
                request.setAttribute("pacientes", dao.listarPaciente());// para exibir os dados na pag
                view.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

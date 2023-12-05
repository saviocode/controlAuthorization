<%--
  Created by IntelliJ IDEA.
  User: savio
  Date: 04/12/2023
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/autorizacao.css">
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script src="js/jquery.maskMoney.min.js" type="text/javascript"></script>
    <title>Cadastro de Paciente</title>
</head>
<body>
<header>
    <a href="index.jsp">
        <img src="css/img/logo00.png" width="150px" height="150px" title="Home">
    </a>
    <a href="index.jsp">Sair</a>
</header>
<form action="PacienteServlet" method="post" autocomplete="off" id="formPaci"
>
    <h1>Cadastro de Paciente</h1>
    <div class="field">
        <label for="cpf">CPF</label>
        <input type="text" id="cpf" name="cpf" value="${paci.cpf}">
    </div>
    <div class="field-group">
        <div class="field">
            <label for="nome">Nome do Paciente</label>
            <input type="text" id="nome" name="nome" value="${paci.nome}">
        </div>
    </div>
    <div class="field">
        <div class="field-group">
            <div class="field">
                <label for="idade">Idade</label>
                <input type="number" id="idade" name="idade" value="${paci.idade}">
            </div>
            <div class="field">
                <label for="sexo">Sexo</label>
                <input type="text" id="sexo" name="sexo" value="${paci.sexo}">
            </div>
        </div>
    </div>
    <button type="submit" id="salvar"
            onclick="return validarCampos()">Salvar
    </button>
    <button type="submit" id="canc"
            onclick="document.getElementById('formProd').action =
                                    'ProdutoServlet?accao=reset'">Cancelar
    </button>
</form>
<center>
    <table id="table1">
        <caption>Pacientes Cadastrados</caption>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Idade</th>
            <th>Sexo</th>
            <th>Acções</th>
        </tr>
        <c:forEach items="${pacientes}" var="paci">
            <tr id="tr2">
                <td id="td1"><c:out value="${paci.id}"></c:out></td>
                <td id="td2"><c:out value="${paci.nome}"></c:out></td>
                <td id="td3"><c:out value="${paci.sexo}"></c:out></td>
                <td id="td4"><f:formatNumber type="number" value="${paci.idade}"/></td>
                <td id="td5">
                    <a href="PacienteServlet?accao=delete&paci=${paci.id}"
                       onclick="return confirm('Deseja Excluir Esse Registo?')">
                        <img src="css/img/delete.png" width="20px" height="20px" title="Exlcuir"></a>
                    <a href="PacienteServlet?accao=edit&paci=${paci.idProd}">
                        <img src="css/img/edit.png" width="20px" height="20px" title="Editar"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</center>

<script type="text/javascript">
    function validarCampos() {
        if (document.getElementById('nome').value === '') {
            alert("Preencha o Campo Nome !!!");
            return false;

        } else if (document.getElementById('idade').value === '') {
            alert('Preencha o Campo idade !!!');
            return false;

        } else if (document.getElementById('sexo').value === '') {
            alert('Preencha o Campo sexo');
            return false;

        } else {
            document.getElementById('formPaci').submit();
        }
        return true;
    }
</script>
</body>
</html>
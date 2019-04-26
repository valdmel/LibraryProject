<%@page import="java.util.List"%>
<%@page import="br.mackenzie.fci.ec.lp2.model.Usuario"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--LISTAR USUÁRIO-->

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/menu.css">
        <link rel="stylesheet" type="text/css" href="CSS/tabela.css">
        <title>Projeto Livraria</title>
    </head>

    <body>
        <div class="background_image">
        </div>

        <!--MENU-->

        <ul class="menu cf">
            <li>
                <a href="#">Usu&aacute;rios</a>
                <ul class="submenu">
                    <li><a href="${pageContext.request.contextPath}/usuario!alterar.action">Alterar</a></li>
                    <li><a href="${pageContext.request.contextPath}/usuario!listar.action">Listar</a></li>
                    <li><a href="${pageContext.request.contextPath}/usuario!remover.action">Remover</a></li>
                </ul>
            </li>
        </ul>

        <!--DESENHAR TABELA-->

        <table style="width:100%" bgcolor="darkkhaki">
            <tr>
                <th>ID_USUARIO</th>
                <th>NOME_USUARIO</th>
                <th>LOGIN_USUARIO</th>
                <th>SENHA_USUARIO</th>
                <th>ID_TIPO_USUARIO</th>
                <th>NOME_TIPO_USUARIO</th>
            </tr>

            <c:forEach var="usuario" items="${usuarios}">
                <tr>
                    <td align="center"><c:out value="${usuario.ID}"/></td>
                    <td align="center"><c:out value="${usuario.nome}"/></td>
                    <td align="center"><c:out value="${usuario.login}"/></td>
                    <td align="center"><c:out value="${usuario.senha}"/></td>
                    <td align="center"><c:out value="${usuario.IDTipoUsuario.ID}"/></td>
                    <td align="center"><c:out value="${usuario.IDTipoUsuario.nome}"/></td>
                </tr>
            </c:forEach>
        </table>

        <div class="footer">
            Copyright © Projeto Livraria 2016 - Linguagem de Programação II
        </div>
    </body>
</html>

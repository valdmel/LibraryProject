<%@page import="java.util.List"%>
<%@page import="br.mackenzie.fci.ec.lp2.model.Autor"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--LISTAR AUTOR-->

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
            <li><a href="${pageContext.request.contextPath}/menu.jsp">Página Principal</a></li>

            <li>
                <a href="#">Autores</a>
                <ul class="submenu">
                    <li><a href="${pageContext.request.contextPath}/autor!alterar.action">Alterar</a></li>
                    <li><a href="${pageContext.request.contextPath}/autor!novo.action">Inserir</a></li>
                    <li><a href="${pageContext.request.contextPath}/autor!listar.action">Listar</a></li>
                    <li><a href="${pageContext.request.contextPath}/autor!remover.action">Remover</a></li>
                </ul>
            </li>

            <li>
                <a href="#">Livros</a>
                <ul class="submenu">
                    <li><a href="${pageContext.request.contextPath}/livro!alterar.action">Alterar</a></li>
                    <li><a href="${pageContext.request.contextPath}/livro!novo.action">Inserir</a></li>
                    <li><a href="${pageContext.request.contextPath}/livro!listar.action">Listar</a></li>
                    <li><a href="${pageContext.request.contextPath}/livro!remover.action">Remover</a></li>
                </ul>
            </li>

            <li>
                <a href="#">Empr&eacute;stimos</a>
                <ul class="submenu">
                    <li><a href="${pageContext.request.contextPath}/emprestimo!alterar.action">Alterar</a></li>
                    <li><a href="${pageContext.request.contextPath}/emprestimo!novo.action">Inserir</a></li>
                    <li><a href="${pageContext.request.contextPath}/emprestimo!listar.action">Listar</a></li>
                    <li><a href="${pageContext.request.contextPath}/emprestimo!remover.action">Remover</a></li>
                </ul>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/quemsomos.jsp">Quem Somos</a>
            </li>
        </ul> 

        <!--DESENHAR TABELA-->

        <table style="width:100%" bgcolor="darkkhaki">
            <tr>
                <th>ID_AUTOR</th>
                <th>NOME_AUTOR</th>
            </tr>

            <c:forEach var="autor" items="${autores}">
                <tr>
                    <td align="center"><c:out value="${autor.ID}"/></td>
                    <td align="center"><c:out value="${autor.nome}"/></td>
                </tr>
            </c:forEach>
        </table>

        <div class="footer">
            Copyright © Projeto Livraria 2016 - Linguagem de Programação II
        </div>
    </body>
</html>

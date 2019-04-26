<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--REMOVER AUTOR-->

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/menu.css">
        <title>Projeto Livraria</title>
    </head>

    <body>
        <div class="background_image">
        </div>

        <!--MENU-->

        <ul class="menu cf">
            <li><a href="${pageContext.request.contextPath}/menu.jsp">Página Principal</a></li>

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
                <a href="#">Autores</a>
                <ul class="submenu">
                    <li><a href="${pageContext.request.contextPath}/autor!alterar.action">Alterar</a></li>
                    <li><a href="${pageContext.request.contextPath}/autor!novo.action">Inserir</a></li>
                    <li><a href="${pageContext.request.contextPath}/autor!listar.action">Listar</a></li>
                    <li><a href="${pageContext.request.contextPath}/autor!remover.action">Remover</a></li>
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
                <a href="#">Usu&aacute;rios</a>
                <ul class="submenu">
                    <li><a href="${pageContext.request.contextPath}/usuario!alterar.action">Alterar</a></li>
                    <li><a href="${pageContext.request.contextPath}/usuario!novo.action">Inserir</a></li>
                    <li><a href="${pageContext.request.contextPath}/usuario!listar.action">Listar</a></li>
                    <li><a href="${pageContext.request.contextPath}/usuario!remover.action">Remover</a></li>
                </ul>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/quemsomos.jsp">Quem Somos</a>
            </li>
        </ul>
            
        <form method="POST" action="${pageContext.request.contextPath}/autor!confirmarRemocao.action">
            <label for="ID">ID do Autor:&nbsp;</label>
            <input type="text" name="ID" size="10"/>
            <input type="submit" value="Remover"/>
        </form>

        <div class="footer">
            Copyright © Projeto Livraria 2016 - Linguagem de Programação II
        </div>
    </body>
</html>
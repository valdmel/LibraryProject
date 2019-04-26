<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--INSERIR LIVRO-->

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

        <form method="POST" action="${pageContext.request.contextPath}/livro!inserir.action">
            <!--<label for="autor">Nome do Autor:&nbsp;</label>

            <select name="autores" id="autores">
                <option>--Selecione o Autor--</option>
                <c:forEach var="autor" items="${autores}">
                    <option value="${autor.nome}">${autor.nome}</option>
                </c:forEach>
            </select>-->

            <label for="ID">ID do Autor:&nbsp;</label>
            <input type="text" name="ID" size="10"/>
            
            <br><br>

            <label for="nome">Nome do Livro:&nbsp;</label>
            <input type="text" name="nome" size="40"/>
            <input type="submit" value="Inserir"/>
        </form>

        <div class="footer">
            Copyright © Projeto Livraria 2016 - Linguagem de Programação II
        </div>
    </body>
</html>

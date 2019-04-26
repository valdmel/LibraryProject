<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--REMOVER USUÁRIO-->

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
            <li>
                <a href="#">Usu&aacute;rios</a>
                <ul class="submenu">
                    <li><a href="${pageContext.request.contextPath}/usuario!alterar.action">Alterar</a></li>
                    <li><a href="${pageContext.request.contextPath}/usuario!listar.action">Listar</a></li>
                    <li><a href="${pageContext.request.contextPath}/usuario!remover.action">Remover</a></li>
                </ul>
            </li>
        </ul>

        <form method="POST" action="${pageContext.request.contextPath}/usuario!confirmarRemocao.action">
            <label for="ID">ID do Usuário:&nbsp;</label>
            <input type="text" name="ID" size="10"/>
            <input type="submit" value="Remover"/>
        </form>

        <div class="footer">
            Copyright © Projeto Livraria 2016 - Linguagem de Programação II
        </div>
    </body>
</html>

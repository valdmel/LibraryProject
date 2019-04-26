<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--INSERIR USUÁRIO-->

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/cadastro.css">
        <title>Projeto Livraria</title>
    </head>

    <body>
        <div class="background_image">
        </div>

        <div class="wrapper">
            <div class="header">
                <h1>Bem-vindo!</h1>
            </div>

            <div class="login">
                <h2 class="login-header">Cadastro</h2>

                <form class="login-container" method="POST" action="${pageContext.request.contextPath}/usuario!inserir.action">
                    <p><input type="text" name="nome" size="40" placeholder="Nome completo"></p>
                    <p><input type="text" name="login" size="40" placeholder="Login"></p>
                    <p><input type="text" name="senha" size="40" placeholder="Senha"></p>
                    <p><input type="submit" value="Confirmar"></p>
                </form>

                <form class="login-container2" method="POST" action="${pageContext.request.contextPath}/index.jsp">
                    <p><input type="submit" value="Voltar a pagina anterior"></p>
                </form>
            </div>
        </div>

        <div class="footer">
            <p>Copyright © Projeto Livraria 2016 - Linguagem de Programação II</p>
        </div>
    </body>
</html>
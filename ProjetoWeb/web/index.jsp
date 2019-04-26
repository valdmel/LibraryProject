<!--TELA DE LOGIN-->

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/login.css">
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
                <h2 class="login-header">Login</h2>

                <form class="login-container" method="POST" action="${pageContext.request.contextPath}/usuario!login.action">
                    <p><input type="text" name="login" placeholder="Login"></p>
                    <p><input type="text" name="senha" placeholder="Senha"></p>
                    <p><input type="submit" value="Entrar"></p>
                </form>

                <form class="login-container2" method="POST" action="${pageContext.request.contextPath}/usuario!novo.action">
                    <p><input type="submit" value="Novo usuário? Cadastre-se!"></p>
                </form>
            </div>
        </div>

        <div class="footer">
            <p>Copyright © Projeto Livraria 2016 - Linguagem de Programação II</p>
        </div>
    </body>
</html>
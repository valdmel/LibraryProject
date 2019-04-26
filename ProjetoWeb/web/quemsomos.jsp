<!--TELA DE QUEM SOMOS-->

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/menu.css">
        <link rel="stylesheet" type="text/css" href="CSS/quemsomos.css">
        <title>Projeto Livraria</title>
    </head>

    <body>
        <div class="background_image">
        </div>

        <!--MENU-->

        <ul class="menu cf">
            <li><a href="${pageContext.request.contextPath}/menu.jsp">Página Principal</a></li>

            <li>
                <a href="">Autores</a>
                <ul class="submenu">
                    <li><a href="${pageContext.request.contextPath}/autor!alterar.action">Alterar</a></li>
                    <li><a href="${pageContext.request.contextPath}/autor!novo.action">Inserir</a></li>
                    <li><a href="${pageContext.request.contextPath}/autor!listar.action">Listar</a></li>
                    <li><a href="${pageContext.request.contextPath}/autor!remover.action">Remover</a></li>
                </ul>
            </li>

            <li>
                <a>Livros</a>
                <ul class="submenu">
                    <li><a href="${pageContext.request.contextPath}/livro!alterar.action">Alterar</a></li>
                    <li><a href="${pageContext.request.contextPath}/livro!novo.action">Inserir</a></li>
                    <li><a href="${pageContext.request.contextPath}/livro!listar.action">Listar</a></li>
                    <li><a href="${pageContext.request.contextPath}/livro!remover.action">Remover</a></li>
                </ul>
            </li>

            <li>
                <a>Empr&eacute;stimos</a>
                <ul class="submenu">
                    <li><a href="${pageContext.request.contextPath}/emprestimo!alterar.action">Alterar</a></li>
                    <li><a href="${pageContext.request.contextPath}/emprestimo!novo.action">Inserir</a></li>
                    <li><a href="${pageContext.request.contextPath}/emprestimo!listar.action">Listar</a></li>
                    <li><a href="${pageContext.request.contextPath}/emprestimo!remover.action">Remover</a></li>
                </ul>
            </li>

            <li><a href="${pageContext.request.contextPath}/quemsomos.jsp">Quem Somos</a></li></ul>   

    <center>
        <h1>Quem somos</h1>

        <br>

        <p>O Projeto Livraria iniciou-se em 2016 para a matéria de Linguagem de Programação II. 
            Foi realizado por Valdemar de Melo Neves Junior e Patrick Gainher, 
            estudantes de Sistemas de Informação e Ciência da Computação, respectivamente.</p>

        <br><br>

        <h1>Bônus</h1>

        <video width="480" height="320" autoplay controls loop>
            <source src="http://img-9gag-fun.9cache.com/photo/aBYbv5A_460sv_v1.mp4" type="video/mp4">
        </video>

        <br><br>

        <p>A felicidade de quando o código roda sem bugs. :D</p>

    </center>

    <div class="footer">
        Copyright © Projeto Livraria 2016 - Linguagem de Programação II
    </div>
</body>
</html>
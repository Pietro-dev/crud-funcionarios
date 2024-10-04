<%-- 
    Document   : index
    Created on : 1 de out. de 2024, 20:54:16
    Author     : alunocmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css" type="text/css"/>
        <link href="assets/css/main.css" rel="stylesheet" type="text/css"/>
        <script src="assets/js/jquery-3.7.1.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/main.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <nav>
            <ul>
                <li><h5>GESTÃO DE FUNCIONÁRIOS</h5></li>
                <li><a href="index.jsp">Home</a></li>
                <li><span id="btn_getAllFuncionarios">Funcionários</span></li>
            </ul>
        </nav>
        <div class="container tabela-funcionarios mt-5">
            <!-- Tabela criada dinâmicamente via JS -->
        </div>
        <!-- Modal create funcionario -->
        <div class="modal fade" id="modal_create_funcionario" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content bg-dark text-white">
                    <div class="modal-header text-white">
                        <h5 class="modal-title" id="exampleModalLabel">Cadastrar Funcionário</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Your modal content here -->
                        texto exemplo
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                        <button type="button" class="btn btn-success">Cadastrar</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

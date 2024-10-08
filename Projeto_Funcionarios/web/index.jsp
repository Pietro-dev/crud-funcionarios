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
        <main>
            
        </main>
        <div class="container tabela-funcionarios mt-5">
            <!-- Tabela criada dinâmicamente via JS -->
        </div>
        <!-- Modal create funcionario -->
        <div class="modal fade" id="modal_create_funcionario" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content bg-dark text-white">
                    <div class="modal-header text-white">
                        <h5 class="modal-title" id="exampleModalLabel">Cadastrar Funcionário</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container">
                            <div class="row">
                                <div class="">
                                    <span>Nome: </span>
                                </div>
                                 <div class="mt-2">
                                     <input type="text" class="form-control" placeholder="nome completo" id="nome">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="">
                                    <span>Cargo: </span>
                                </div>
                                 <div class=" mt-2">
                                     <input type="text" class="form-control" placeholder="supervisor, analista, desenvolvedor..." id="cargo">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="">
                                    <span>Data de Admissão: </span>
                                </div>
                                 <div class=" mt-2">
                                     <input type="text" class="form-control" placeholder="dd/mm/yyyy" id="data_admissao">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="">
                                    <span>Salário: </span>
                                </div>
                                 <div class=" mt-2">
                                     <input type="text" class="form-control" placeholder="1000.00" id="salario">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="">
                                    <span>Departamento: </span>
                                </div>
                                 <div class="mt-2">
                                     <input type="text" class="form-control" placeholder="TI, RH..." id="departamento">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="">
                                    <span>E-mail: </span>
                                </div>
                                 <div class="mt-2">
                                     <input type="email" class="form-control" placeholder="mail@email.com" id="email">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="">
                                    <span>Telefone: </span>
                                </div>
                                 <div class="mt-2">
                                     <input type="tel" class="form-control" placeholder="1198765432" id="telefone">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="">
                                    <span>Endereço: </span>
                                </div>
                                 <div class="mt-2">
                                     <input type="text" class="form-control" placeholder="Rua xyz, nº 100, SP - Mogi das Cruzes" id="endereco">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="">
                                    <span>Data de Nascimento: </span>
                                </div>
                                 <div class="mt-2">
                                     <input type="text" class="form-control custom-input" placeholder="dd/mm/yyyy" id="data_nascimento">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                        <button type="button" class="btn btn-success" id="btn_create_funcionario">Cadastrar</button>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Modal update funcionario -->
        <div class="modal fade" id="modal_edit_funcionario" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content bg-dark text-white">
                    <div class="modal-header text-white">
                        <h5 class="modal-title" id="exampleModalLabel">Atualizar Funcionário</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container">
                            <div class="row">
                                <div class="">
                                    <span>ID: </span>
                                </div>
                                 <div class="mt-2">
                                     <input type="text" class="form-control" id="edit_id" disabled>
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="">
                                    <span>Nome: </span>
                                </div>
                                 <div class="mt-2">
                                     <input type="text" class="form-control update-input" placeholder="nome completo" id="edit_nome">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="">
                                    <span>Cargo: </span>
                                </div>
                                 <div class=" mt-2">
                                     <input type="text" class="form-control update-input" placeholder="supervisor, analista, desenvolvedor..." id="edit_cargo">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="">
                                    <span>Data de Admissão: </span>
                                </div>
                                 <div class=" mt-2">
                                     <input type="text" class="form-control update-input" placeholder="dd/mm/yyyy" id="edit_data_admissao">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="">
                                    <span>Salário: </span>
                                </div>
                                 <div class=" mt-2">
                                     <input type="text" class="form-control update-input" placeholder="1000.00" id="edit_salario">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="">
                                    <span>Departamento: </span>
                                </div>
                                 <div class="mt-2">
                                     <input type="text" class="form-control update-input" placeholder="TI, RH..." id="edit_departamento">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="">
                                    <span>E-mail: </span>
                                </div>
                                 <div class="mt-2">
                                     <input type="email" class="form-control update-input" placeholder="mail@email.com" id="edit_email">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="">
                                    <span>Telefone: </span>
                                </div>
                                 <div class="mt-2">
                                     <input type="tel" class="form-control update-input" placeholder="1198765432" id="edit_telefone">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="">
                                    <span>Endereço: </span>
                                </div>
                                 <div class="mt-2">
                                     <input type="text" class="form-control update-input" placeholder="Rua xyz, nº 100, SP - Mogi das Cruzes" id="edit_endereco">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="">
                                    <span>Data de Nascimento: </span>
                                </div>
                                 <div class="mt-2">
                                     <input type="text" class="form-control custom-input update-input" placeholder="dd/mm/yyyy" id="edit_data_nascimento">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                        <button type="button" class="btn btn-success" id="btn_update_funcionario">Atualizar</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

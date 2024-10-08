$(document).ready(function () {
    callGetAllFuncionarios();
    createFuncionario();
    updateFuncionario();
    buscarFuncionarioPorId();
});

function getAllFuncionarios() {
    console.log("Atualizando a tabela de funcionários");
    $.ajax({
        url: "GetAllFuncionarios",
        type: "GET",
        dataType: "JSON",
        data: "action=getAllFuncionarios"
    }).done(function (response) {
        console.log(response);
        tableFuncionarios(response);
    }).fail(function () {
        console.log("Erro na listagem dos usuários!");
    });
}

function callGetAllFuncionarios() {
    $(document).on("click", "#btn_getAllFuncionarios", function () {
        getAllFuncionarios();
    });
}

function AtivarDeletarFuncionario() {
    $(document).on("click", ".btn-delete-funcionario", function() {
    var id = $(this).closest('tr').find('td:first').text(); // Assume que o ID está na primeira coluna

        $.ajax({
            url: "FuncionarioController?action=delete&id=" + id,
            type: "DELETE",
            success: function (response) {
                if (response.success) {
                    alert("Funcionário deletado com sucesso!");
                    getAllFuncionarios(); // Atualiza a lista de funcionários
                } else {
                    // alert("Erro ao deletar funcionário: " + response.message);
                }
            },
            error: function (err) {
                console.error("Erro ao enviar a requisição:", err);
                alert("Ocorreu um erro");
            }
        });
    });
}
function tableFuncionarios(data) {
    const divFuncionarios = $(".tabela-funcionarios");
    let div = '';
    
    // Primeira linha com o título
    div += '<div class="row mb-3">'; // Linha 1 - Registro de Funcionários
    div += '<div class="col-md-12">';
    div += '<h3>Registro de Funcionários</h3>';
    div += '</div>';
    div += '</div>';

    // Segunda linha com os botões e input de busca
    div += '<div class="row mb-3">'; // Linha 2 - Botões e busca
    div += '<div class="col-md-6">';
    div += '<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal_create_funcionario">Adicionar Funcionário</button>';
    div += '</div>';
    div += '<div class="col-md-6 d-flex justify-content-end">';
    div += '<input type="text" id="funcionario_id" class="form-control w-auto me-2" placeholder="Digite o ID do Funcionário">';
    div += '<button id="btn_buscar_funcionario" class="btn btn-secondary text-nowrap">Buscar Funcionário</button>';
    div += '</div>';
    div += '</div>';
    
    // Adiciona a tabela
    div += '<table class="table mt-3">';
    div += '<thead class="table-dark">';
    div += '<tr>';
    div += '<th>Id</th>';
    div += '<th>Nome</th>';
    div += '<th>Cargo</th>';
    div += '<th>Admissão</th>';
    div += '<th>Salário</th>';
    div += '<th>Departamento</th>';
    div += '<th>Email</th>';
    div += '<th>Telefone</th>';
    div += '<th>Endereço</th>';
    div += '<th>Nascimento</th>';
    div += '<th>Ações</th>';
    div += '</tr>';
    div += '</thead>';
    div += '<tbody>';
    
    // Se data não estiver vazio, preencha a tabela
    if (data.length > 0) {
        for(const value of data) {
            div += '<tr>';
            div += '<td>' + value.id + '</td>';
            div += '<td>' + value.nome + '</td>';
            div += '<td>' + value.cargo + '</td>';
            div += '<td>' + value.dataAdmissao + '</td>';
            div += '<td>' + value.salario + '</td>';
            div += '<td>' + value.departamento + '</td>';
            div += '<td>' + value.email + '</td>';
            div += '<td>' + value.telefone + '</td>';
            div += '<td>' + value.endereco + '</td>';
            div += '<td>' + value.dataNascimento + '</td>';
            div += '<td>';
            div += '<div style="display: flex;">';
            div += '<button class="btn btn-primary me-2" btn-update-funcionario>Editar</button>';
            div += '<button class="btn btn-danger btn-delete-funcionario" id="deletarFuncionario">Deletar</button>';
            div += '</div>';
            div += '</td>';
            div += '</tr>';
        }
    }
    
    div += '</tbody>';
    div += '</table>';
    divFuncionarios.html(div);

    // Ativando a função dos buttons deletar
    AtivarDeletarFuncionario();
    buscarFuncionarioPorId();
}

// criar funcionario
function createFuncionario(){
    $(document).on("click", "#btn_create_funcionario", function(){
        // capturando os dados do formulário
        var nome = $("#nome").val();
        var cargo = $("#cargo").val();
        var data_admissao = $("#data_admissao ").val();
        var salario = $("#salario").val();
        var departamento = $("#departamento").val();
        var email = $("#email").val();
        var telefone = $("#telefone").val();
        var endereco = $("#endereco").val();
        var data_nascimento = $("#data_nascimento").val();
        
        var novoFuncionario = {
            nome: nome,
            cargo: cargo,
            dataAdmissao: data_admissao,
            salario: salario,
            departamento: departamento,
            email: email,
            telefone: telefone,
            endereco: endereco,
            dataNascimento: data_nascimento
        };
        
        $.ajax({
            url: "FuncionarioController?action=create",
            type: "POST",
            data: JSON.stringify(novoFuncionario),
            contentType: "application/json",
            success: function (response) {
            if (response.success) {
              alert("Funcionário cadastrado com sucesso!");
                // fechar o modal
                $("#modal_create_funcionario").modal("hide");
              
                getAllFuncionarios();
            } else {
                console.log(response.message);
                alert("Erro ao cadastrar funcionário:");
            }
            },
            error: function (err) {
            console.error("Erro ao enviar a requisição:", err);
            alert("Ocorreu um erro");
            console.log(novoFuncionario);
          }
        });
    }); 
}

function updateFuncionario() {
        $(document).on("click", ".btn-primary.me-2", function () {
            var row = $(this).closest('tr'); // Seleciona a linha correspondente
            
            var id = row.find('td:first').text();
            var nome = row.find('td:nth-child(2)').text();
            var cargo = row.find('td:nth-child(3)').text();
            var dataAdmissao = row.find('td:nth-child(4)').text();
            var salario = row.find('td:nth-child(5)').text();
            var departamento = row.find('td:nth-child(6)').text();
            var email = row.find('td:nth-child(7)').text();
            var telefone = row.find('td:nth-child(8)').text();
            var endereco = row.find('td:nth-child(9)').text();
            var dataNascimento = row.find('td:nth-child(10)').text();

            $("#edit_id").val(id);
            $("#edit_nome").val(nome);
            $("#edit_cargo").val(cargo);
            $("#edit_data_admissao").val(dataAdmissao);
            $("#edit_salario").val(salario);
            $("#edit_departamento").val(departamento);
            $("#edit_email").val(email);
            $("#edit_telefone").val(telefone);
            $("#edit_endereco").val(endereco);
            $("#edit_data_nascimento").val(dataNascimento);

            // Mostra o modal
            $("#modal_edit_funcionario").modal("show");
        });

// Atualizar funcionário
        $("#btn_update_funcionario").on("click", function () {
            var funcModel = {
                id: $("#edit_id").val(),
                nome: $("#edit_nome").val(),
                cargo: $("#edit_cargo").val(),
                dataAdmissao: $("#edit_data_admissao").val(),
                salario: $("#edit_salario").val(),
                departamento: $("#edit_departamento").val(),
                email: $("#edit_email").val(),
                telefone: $("#edit_telefone").val(),
                endereco: $("#edit_endereco").val(),
                dataNascimento: $("#edit_data_nascimento").val()
            };
            console.log(funcModel.id);
            $.ajax({
                url: "FuncionarioController?action=update",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(funcModel),
                success: function (response) {
                    if (response.success) {
                        alert("Funcionário atualizado com sucesso!");
                        $("#modal_edit_funcionario").modal("hide");
                        getAllFuncionarios(); // Atualiza a lista de funcionários
                    } else {
                        console.log(funcModel);
                        alert("Erro ao atualizar funcionário: " + response.message);
                    }
                },
                error: function (err) {
                    console.error("Erro ao enviar a requisição:", err);
                    alert("Ocorreu um erro");
                }
            });
        });
    }

function buscarFuncionarioPorId() {
    $("#btn_buscar_funcionario").on("click", function () {
        var id = $("#funcionario_id").val(); // Pega o valor do input de busca
        console.log("Buscando funcionário com ID:", id); // Log do ID
        if (id) {
            $.ajax({
                url: "FuncionarioController?action=getById", // Ação de busca
                type: "POST",
                data: { id: id }, // Envia o ID como parâmetro
                success: function (response) {
                    if (response.success) {
                        var funcionario = response.funcionario;
                        console.log(response);
                        // Limpa a tabela e insere o funcionário encontrado
                        tableFuncionarios([funcionario]); // Passa um array com o funcionário encontrado
                    } else {
                        alert("Funcionário não encontrado.");
                        tableFuncionarios([]); // Limpa a tabela se não encontrar
                    }
                },
                error: function (err) {
                    console.error("Erro ao buscar funcionário:", err);
                    alert("Erro ao buscar funcionário.");
                }
            });
        } else {
            // Se o input estiver vazio, buscar todos os funcionários
            getAllFuncionarios(); // Função que busca todos os registros
        }
    });
}


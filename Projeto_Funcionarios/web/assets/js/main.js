$(document).ready(function () {
    callGetAllFuncionarios();
});

function getAllFuncionarios() {
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

function tableFuncionarios(data) {
    const divFuncionarios = $(".tabela-funcionarios");
    let div = '';
    div += '<div class="row">';
    div += '<div class="col-md-12">';
    div += '<h3>Registro de Funcionários</h3>';
    div += '<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal_create_funcionario">Adicionar Funcionário</button>';
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
    div += '<th>Data Nascimento</th>';
//    div += '<th>Created at</th>';
//    div += '<th>Updated at</th>';
    div += '<th>Ações</th>';
    div += '</tr>';
    div += '</thead>';
    div += '<tbody>';
    for(const value of data){
        div += '<tr>';
        div += '<td>'+value.id+'</td>';
        div += '<td>'+value.nome+'</td>';
        div += '<td>'+value.cargo+'</td>';
        div += '<td>'+value.dataAdmissao+'</td>';
        div += '<td>'+value.salario+'</td>';
        div += '<td>'+value.departamento+'</td>';
        div += '<td>'+value.email+'</td>';
        div += '<td>'+value.telefone+'</td>';
        div += '<td>'+value.endereco+'</td>';
        div += '<td>'+value.dataNascimento+'</td>';
//        div += '<td>'+value.createdAt+'</td>';
//        div += '<td>'+value.updatedAt+'</td>';
        div += '<td>';
        div += '<button class="btn btn-primary me-2">Editar</button>';
        div += '<button class="btn btn-danger">Deletar</button>';
        div += '</td>';
        div += '</tr>';
    }
    div += '</tbody>';
    div += '</table>';
    div += '</div>';
    div += '</div>';

    divFuncionarios.html(div);
}


    

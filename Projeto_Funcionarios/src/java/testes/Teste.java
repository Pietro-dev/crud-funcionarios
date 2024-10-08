/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testes;

import dao.DaoFuncionario;
import java.sql.Connection;
import java.sql.SQLException;
import util.Conexao;
import models.FuncionarioModel;


/**
 *
 * @author Pietro
 */
public class Teste {
    public static void main(String[] args) {
//        try {
//            Conexao conexao = new Conexao();
//            Connection conn = null;
//            conn = conexao.conexao();
//            System.out.println(conn.isClosed());
//            
//            conexao.desligarConexao(conn);
//            System.out.println(conn.isClosed());
//        } catch (SQLException e) {
//            
//        }
       
        
//        for(FuncionarioModel func : daoFunc.getAllFuncionarios()){
//            System.out.println(func.getId());
//            System.out.println(func.getNome());
//            System.out.println(func.getCreatedAt());
//        }

        DaoFuncionario daoFunc = new DaoFuncionario();
        FuncionarioModel funcModel = new FuncionarioModel();
        
        funcModel.setNome("Pedro");
        funcModel.setCargo("Gerente");
        funcModel.setDataAdmissao("01/01/2023");
        funcModel.setSalario(5000.00);
        funcModel.setDepartamento("RH");
        funcModel.setEmail("pedro@email.com");
        funcModel.setTelefone("123456789");
        funcModel.setEndereco("Rua 123, Cidade XYZ");
        funcModel.setDataNascimento("01/01/1990");
        
        boolean result = daoFunc.createFuncionario(funcModel);
        if(result){
            System.out.println("Registro inserido com sucesso!!");
        }else{
            System.out.println("Erro na inserção do registro!");
        }
    }
}

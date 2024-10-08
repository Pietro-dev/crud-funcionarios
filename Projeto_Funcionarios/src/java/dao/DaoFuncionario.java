
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.FuncionarioModel;
import util.Conexao;

/**
 *
 * @author Pietro
 */
public class DaoFuncionario {
    public List<FuncionarioModel> getAllFuncionarios(){
        Connection conn = null;
        Conexao conexao = new Conexao();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "select * from funcionarios";
        List listaFuncionarios = new ArrayList();
        try {
            conn = conexao.conexao();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                FuncionarioModel funcModel = new FuncionarioModel();
                funcModel.setId(rs.getInt("id"));
                funcModel.setNome(rs.getString("nome"));
                funcModel.setCargo(rs.getString("cargo"));
                funcModel.setDataAdmissao(rs.getString("data_admissao"));
                funcModel.setSalario(rs.getDouble("salario"));
                funcModel.setDepartamento(rs.getString("departamento"));
                funcModel.setEmail(rs.getString("email"));
                funcModel.setTelefone(rs.getString("telefone"));
                funcModel.setEndereco(rs.getString("endereco"));
                funcModel.setDataNascimento(rs.getString("data_nascimento"));
                funcModel.setCreatedAt(rs.getTimestamp("created_at"));
                funcModel.setUpdatedAt(rs.getTimestamp("updated_at"));
                funcModel.setDeletedAt(rs.getTimestamp("deleted_at"));
                listaFuncionarios.add(funcModel);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            conexao.desligarConexao(conn);
        }
        return listaFuncionarios;
    }
    
    public boolean createFuncionario(FuncionarioModel funcModel){
        Conexao conexao = new Conexao();
        Connection conn = null;
        PreparedStatement ps = null;
        boolean isSuccess = false;
        
        try {
            conn = conexao.conexao();
            
            String querySql = "INSERT INTO funcionarios (nome, cargo, data_admissao, salario, departamento, email, telefone, endereco, data_nascimento) values(?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(querySql);
            ps.setString(1, funcModel.getNome());
            ps.setString(2, funcModel.getCargo());
            ps.setString(3, funcModel.getDataAdmissao());
            ps.setDouble(4, funcModel.getSalario());
            ps.setString(5, funcModel.getDepartamento());
            ps.setString(6, funcModel.getEmail());
            ps.setString(7, funcModel.getTelefone());
            ps.setString(8, funcModel.getEndereco());
            ps.setString(9, funcModel.getDataNascimento());
            
            int rowsAffected = ps.executeUpdate();
            isSuccess = (rowsAffected > 0);
        } catch (SQLException e) {
            System.err.println("Erro ao inserir funcionário: " + e.getMessage());
            return false;
        } finally {
            // Fecha os recursos
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar os recursos: " + e.getMessage());
            }
        }
        
        return isSuccess;
    }
    
    public boolean deleteFuncionario(int id) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conexao();
        PreparedStatement ps = null;
        boolean isSuccess = false;

        try {
            String querySql = "DELETE FROM funcionarios WHERE id = ?";
            ps = conn.prepareStatement(querySql);
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                isSuccess = true;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar funcionário: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return isSuccess;
    }
    
    public boolean updateFuncionario(FuncionarioModel funcModel) {
    Conexao conexao = new Conexao();
    Connection conn = conexao.conexao();
    PreparedStatement ps = null;
    boolean isSuccess = false;

    try {
        String querySql = "UPDATE funcionarios SET nome=?, cargo=?, data_admissao=?, salario=?, departamento=?, email=?, telefone=?, endereco=?, data_nascimento=? WHERE id=?";
        ps = conn.prepareStatement(querySql);
        ps.setString(1, funcModel.getNome());
        ps.setString(2, funcModel.getCargo());
        ps.setString(3, funcModel.getDataAdmissao());
        ps.setDouble(4, funcModel.getSalario());
        ps.setString(5, funcModel.getDepartamento());
        ps.setString(6, funcModel.getEmail());
        ps.setString(7, funcModel.getTelefone());
        ps.setString(8, funcModel.getEndereco());
        ps.setString(9, funcModel.getDataNascimento());
        ps.setInt(10, funcModel.getId()); // Certifique-se de que o ID está definido no modelo

        int rowsAffected = ps.executeUpdate();
        isSuccess = (rowsAffected > 0);
    } catch (SQLException e) {
        System.err.println("Erro ao atualizar: " + e.getMessage());
    } finally {
        try {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }

    return isSuccess;
}
    
    public FuncionarioModel getFuncionarioById(int id) {
        FuncionarioModel funcModel = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conexao();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querySql = "SELECT * FROM funcionarios WHERE id = ?";
            ps = conn.prepareStatement(querySql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                funcModel = new FuncionarioModel();
                funcModel.setId(rs.getInt("id"));
                funcModel.setNome(rs.getString("nome"));
                funcModel.setCargo(rs.getString("cargo"));
                funcModel.setDataAdmissao(rs.getString("data_admissao"));
                funcModel.setSalario(rs.getDouble("salario"));
                funcModel.setDepartamento(rs.getString("departamento"));
                funcModel.setEmail(rs.getString("email"));
                funcModel.setTelefone(rs.getString("telefone"));
                funcModel.setEndereco(rs.getString("endereco"));
                funcModel.setDataNascimento(rs.getString("data_nascimento"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar funcionário: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return funcModel;
    }

}

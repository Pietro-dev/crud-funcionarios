/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author Pietro
 */
public class Conexao {
    private final String classDriver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost/db_funcionarios";
    private final String user = "root";
    private final String password = "1234";
    
    public Connection conexao (){
        Connection conn = null; 
        
        try {
            // carregando o driver
            Class.forName(classDriver);
            
            // obtendo a conexão
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn; 
    }
    
    public void desligarConexao(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

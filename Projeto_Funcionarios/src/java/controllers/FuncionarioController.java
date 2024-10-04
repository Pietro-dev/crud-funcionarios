/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DaoFuncionario;
import models.FuncionarioModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pietro
 */
@WebServlet(name = "FuncionarioController", urlPatterns = {"/FuncionarioController","/GetAllFuncionarios"})
public class FuncionarioController extends HttpServlet {
    
    private final ObjectMapper mapper = new ObjectMapper();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        DaoFuncionario daoFunc = new DaoFuncionario();
        ObjectMapper mapper = new ObjectMapper();
        switch(action){
            case "getAllFuncionarios":
                out.print(mapper.writeValueAsString(daoFunc.getAllFuncionarios()));
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            processRequest(request, response);
            String nome = request.getParameter("nome");
            String cargo = request.getParameter("cargo");
            String data_admissao = request.getParameter("data_admissao");
            String salario = request.getParameter("salario");
            String departamento = request.getParameter("departamento");
            String email = request.getParameter("email");
            String telefone = request.getParameter("telefone");
            String endereco = request.getParameter("endereco");
            String data_nascimento = request.getParameter("data_nascimento");

            FuncionarioModel funcModel = new FuncionarioModel();
            funcModel.setNome(nome);
            funcModel.setCargo(cargo);
            funcModel.setDataAdmissao(data_admissao);
            funcModel.setSalario(Double.parseDouble(salario));
            funcModel.setDepartamento(departamento);
            funcModel.setEmail(email);
            funcModel.setTelefone(Integer.parseInt(telefone));
            funcModel.setEndereco(endereco);
            funcModel.setDataNascimento(data_nascimento);

            PrintWriter out = response.getWriter();
            DaoFuncionario daoFunc = new DaoFuncionario();
            boolean isCreated = daoFunc.createFuncionario(funcModel);
            
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("success", isCreated);
            if (isCreated) {
              responseMap.put("message", "Funcionário cadastrado com sucesso!");
            } else {
              responseMap.put("message", "Erro ao cadastrar funcionário");
            }
            try {
            daoFunc.createFuncionario(funcModel); // Chame o método para salvar
            out.print(mapper.writeValueAsString("Funcionário cadastrado com sucesso!!!"));
            } catch (JsonProcessingException e) {
                out.print(mapper.writeValueAsString("Erro ao cadastrar funcionário: " + e.getMessage()));
            }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

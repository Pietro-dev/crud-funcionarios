/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

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

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();

        String action = request.getParameter("action");  // Captura o parâmetro de ação

        Map<String, Object> responseMap = new HashMap<>();

        try {
            // Verifica qual ação foi solicitada (criar, atualizar ou deletar)
            if ("create".equalsIgnoreCase(action)) {
                // Operação de criação
                FuncionarioModel funcModel = mapper.readValue(request.getReader(), FuncionarioModel.class);
                DaoFuncionario daoFunc = new DaoFuncionario();
                boolean isCreated = daoFunc.createFuncionario(funcModel);

                responseMap.put("success", isCreated);
                if (isCreated) {
                    responseMap.put("message", "Funcionário cadastrado com sucesso!");
                } else {
                    responseMap.put("message", "Erro ao cadastrar funcionário.");
                }

            } else if ("update".equalsIgnoreCase(action)) {
                // Operação de atualização
                FuncionarioModel funcModel = mapper.readValue(request.getReader(), FuncionarioModel.class);
                DaoFuncionario daoFunc = new DaoFuncionario();
                boolean isUpdated = daoFunc.updateFuncionario(funcModel);

                responseMap.put("success", isUpdated);
                if (isUpdated) {
                    responseMap.put("message", "Funcionário atualizado com sucesso!");
                } else {
                    responseMap.put("message", "Erro ao atualizar funcionário.");
                }
            } else if ("getById".equalsIgnoreCase(action)){
                // Pegando o ID da requisição
                int id = Integer.parseInt(request.getParameter("id"));

                DaoFuncionario daoFunc = new DaoFuncionario();
                FuncionarioModel funcModel = daoFunc.getFuncionarioById(id);

                if (funcModel != null) {
                    responseMap.put("success", true);
                    responseMap.put("funcionario", funcModel); // Retorna o funcionário encontrado
                } else {
                    responseMap.put("success", false);
                    responseMap.put("message", "Funcionário não encontrado.");
                }
        }

        } catch (IOException e) {
            // Erro durante o processamento da requisição
            responseMap.put("success", false);
            responseMap.put("message", "Erro ao processar a requisição: " + e.getMessage());
        }

        // Retorna a resposta como JSON
        out.print(mapper.writeValueAsString(responseMap));
    }

    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();

        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            String idStr = request.getParameter("id"); // Captura o ID do funcionário a ser deletado

            try {
                int id = Integer.parseInt(idStr); // Tentativa de conversão para int
                DaoFuncionario daoFunc = new DaoFuncionario();
                boolean isDeleted = false;
                isDeleted = daoFunc.deleteFuncionario(id); // Chame o método para deletar

                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("success", isDeleted);

                if (isDeleted) {
                    responseMap.put("message", "Funcionário deletado com sucesso!");
                } else {
                    // responseMap.put("message", "Erro ao deletar funcionário | FuncionarioController dao não deletado");
                }

                out.print(mapper.writeValueAsString(responseMap));
            } catch (NumberFormatException e) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "ID inválido: " + idStr);
                out.print(mapper.writeValueAsString(errorResponse));
            }
        } else {
            // Resposta para ação inválida
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Ação inválida");
            out.print(mapper.writeValueAsString(errorResponse));
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.project.servlet;

import com.project.project.common.JobDetails;
import com.project.project.common.UserDetails;
import com.project.project.ejb.JobBean;
import com.project.project.ejb.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ServletSecurity(value = @HttpConstraint(rolesAllowed ={"HrRole","DirectorRole"}))

@WebServlet(name = "EditJob", urlPatterns = {"/Jobs/Update"})
public class EditJob extends HttpServlet {

    @Inject
    UserBean userBean;
    
    @Inject
    JobBean jobBean;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditJob</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditJob at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        //processRequest(request, response);
        List<UserDetails> users = userBean.getAllUsers();
        request.setAttribute("users", users);
        
        int jobId = Integer.parseInt(request.getParameter("id"));
        JobDetails job = jobBean.findById(jobId);
        request.setAttribute("job", job);
        
        request.getRequestDispatcher("/WEB-INF/pages/editJob.jsp").forward(request, response);
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
        //processRequest(request, response);
        String jobType = request.getParameter("job_type");
        String firmName = request.getParameter("firm_name");
        int userId = Integer.parseInt(request.getParameter("owner_id"));
        int jobId = Integer.parseInt(request.getParameter("job_id"));
        
        jobBean.updateJob(jobId, jobType, firmName, userId);
        
        response.sendRedirect(request.getContextPath()+"/Jobs");
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

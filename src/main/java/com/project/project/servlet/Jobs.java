/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.project.servlet;

import com.project.project.common.JobDetails;
import com.project.project.ejb.JobBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.DeclareRoles;

import javax.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@DeclareRoles({"AdminRole","ClientRole","DirectorRole","HrRole","RecruiterRole"})

@ServletSecurity(
        httpMethodConstraints = @HttpMethodConstraint(value = "POST", rolesAllowed = {"ClientRole","HrRole","DirectorRole"})
            
)

@WebServlet(name = "Jobs", urlPatterns = {"/Jobs"})
public class Jobs extends HttpServlet {

    @Inject
    private JobBean jobBean;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Jobs</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Jobs at " + request.getContextPath() + "</h1>");
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
        request.setAttribute("activePage", "Jobs");
        
        List<JobDetails> jobs = jobBean.getAllJobs();
        request.setAttribute("jobs", jobs);
        request.getRequestDispatcher("/WEB-INF/pages/jobs.jsp").forward(request, response);
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
        String[] jobIdsAsString = request.getParameterValues("job_ids");
        if(jobIdsAsString != null){
            List<Integer> jobIds = new ArrayList<>();
            for(String jobIdAsString : jobIdsAsString){
                jobIds.add(Integer.parseInt(jobIdAsString));
            }
            jobBean.deleteJobsByIds(jobIds);
        }
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

package com.example.lab7iweb.servlets;

import com.example.lab7iweb.beans.Job;
import com.example.lab7iweb.daos.JobDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "JobServlet", value = "/JobServlet")
public class JobServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        JobDao jobDao = new JobDao();

        switch (action) {
            case "lista":
                ArrayList<Job> list = jobDao.listar();
                request.setAttribute("lista", list);
                RequestDispatcher rd = request.getRequestDispatcher("job/lista.jsp");
                rd.forward(request, response);
                break;
            case "new":
                request.getRequestDispatcher("job/form_new.jsp").forward(request, response);
                break;
            case "edit":
                String id = request.getParameter("id");
                Job job = jobDao.buscarPorId(id);

                if (job != null) {
                    request.setAttribute("job", job);
                    request.getRequestDispatcher("job/form_edit.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/JobServlet");
                }
                break;
            case "del":
                String idd = request.getParameter("id");
                Job jobb = jobDao.buscarPorId(idd);

                if (jobb != null) {
                    jobDao.borrar(idd);
                }
                response.sendRedirect(request.getContextPath() + "/JobServlet");
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/JobServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        JobDao jobDao = new JobDao();

        String action = request.getParameter("action") == null ? "crear" : request.getParameter("action");

        switch (action) {
            case "crear":
                String jobId = request.getParameter("jobId");
                String jobTitle = request.getParameter("jobTitle");
                String minSalaryStr = request.getParameter("minSalary");
                String maxSalaryStr = request.getParameter("maxSalary");

                boolean isAllValid = true;

                if (jobTitle.length() > 35) {
                    isAllValid = false;
                }

                if (jobId.length() > 10) {
                    isAllValid = false;
                }

                if (isAllValid) {
                    int minSalary = Integer.parseInt(minSalaryStr);
                    int maxSalary = Integer.parseInt(maxSalaryStr);

                    Job job = new Job();
                    job.setJobId(jobId);
                    job.setJobTitle(jobTitle);
                    job.setMinSalary(minSalary);
                    job.setMaxSalary(maxSalary);

                    jobDao.crear(job);
                    response.sendRedirect(request.getContextPath() + "/JobServlet?action=lista");
                } else {
                    request.getRequestDispatcher("job/form_new.jsp").forward(request, response);
                }
                break;
            case "e":
                String jobId2 = request.getParameter("jobId");
                String jobTitle2 = request.getParameter("jobTitle");
                String minSalary2 = request.getParameter("minSalary");
                String maxSalary2 = request.getParameter("maxSalary");

                boolean isAllValid2 = true;

                if (jobTitle2.length() > 35) {
                    isAllValid2 = false;
                }

                if (jobId2.length() > 10) {
                    isAllValid2 = false;
                }

                if (isAllValid2) {
                    Job job = new Job();
                    job.setJobId(jobId2);
                    job.setJobTitle(jobTitle2);
                    job.setMinSalary(Integer.parseInt(minSalary2));
                    job.setMaxSalary(Integer.parseInt(maxSalary2));

                    jobDao.actualizar(job);
                    response.sendRedirect(request.getContextPath() + "/JobServlet");
                } else {
                    request.setAttribute("job", jobDao.buscarPorId(jobId2));
                    request.getRequestDispatcher("job/form_edit.jsp").forward(request, response);
                }
                break;
            case "s":
                String textBuscar = request.getParameter("textoBuscar");
                ArrayList<Job> lista = jobDao.buscarIdOrTitle(textBuscar);

                request.setAttribute("lista", lista);
                request.setAttribute("busqueda", textBuscar);
                request.getRequestDispatcher("job/lista.jsp").forward(request, response);

                break;
            default:
                response.sendRedirect(request.getContextPath() + "/JobServlet");
                break;
        }
    }
}

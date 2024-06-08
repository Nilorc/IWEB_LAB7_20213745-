package com.example.lab7iweb.servlets;

import com.example.lab7iweb.beans.Employee;
import com.example.lab7iweb.daos.DaoEmployee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "EmployeeServlet", value = "/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {

    private final DaoEmployee daoEmployee = new DaoEmployee();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        switch (action) {
            case "lista":
                List<Employee> lista = daoEmployee.listar();
                request.setAttribute("lista", lista);
                RequestDispatcher rd = request.getRequestDispatcher("employee/lista.jsp");
                rd.forward(request, response);
                break;
            case "new":
                request.getRequestDispatcher("employee/form_new.jsp").forward(request, response);
                break;
            case "edit":
                int id = Integer.parseInt(request.getParameter("id"));
                Employee employee = daoEmployee.buscarPorId(id);

                if (employee != null) {
                    request.setAttribute("employee", employee);
                    request.getRequestDispatcher("employee/form_edit.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                }
                break;
            case "del":
                int idDel = Integer.parseInt(request.getParameter("id"));
                Employee employeeDel = daoEmployee.buscarPorId(idDel);

                if (employeeDel != null) {
                    daoEmployee.borrar(idDel);
                }
                response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "crear" : request.getParameter("action");

        switch (action) {
            case "crear":
                Employee employee = new Employee();
                employee.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
                employee.setFullNameEmployee(request.getParameter("firstName"), request.getParameter("lastName"));
                employee.setEmail(request.getParameter("email"));
                employee.setPassword(request.getParameter("password"));
                employee.setPhoneNumber(request.getParameter("phoneNumber"));
                employee.setHireDate(Date.valueOf(request.getParameter("hireDate")));
                employee.setJobId(request.getParameter("jobId"));
                employee.setSalary(Integer.parseInt(request.getParameter("salary")));
                employee.setCommissionPct(Double.parseDouble(request.getParameter("commissionPct")));
                employee.setManagerId(Integer.parseInt(request.getParameter("managerId")));
                employee.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
                employee.setEnabled(Integer.parseInt(request.getParameter("enabled")));

                daoEmployee.crear(employee);
                response.sendRedirect(request.getContextPath() + "/EmployeeServlet?action=lista");
                break;
            case "edit":
                Employee employeeToUpdate = new Employee();
                employeeToUpdate.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
                employeeToUpdate.setFullNameEmployee(request.getParameter("firstName"), request.getParameter("lastName"));
                employeeToUpdate.setEmail(request.getParameter("email"));
                employeeToUpdate.setPassword(request.getParameter("password"));
                employeeToUpdate.setPhoneNumber(request.getParameter("phoneNumber"));
                employeeToUpdate.setHireDate(Date.valueOf(request.getParameter("hireDate")));
                employeeToUpdate.setJobId(request.getParameter("jobId"));
                employeeToUpdate.setSalary(Integer.parseInt(request.getParameter("salary")));
                employeeToUpdate.setCommissionPct(Double.parseDouble(request.getParameter("commissionPct")));
                employeeToUpdate.setManagerId(Integer.parseInt(request.getParameter("managerId")));
                employeeToUpdate.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
                employeeToUpdate.setEnabled(Integer.parseInt(request.getParameter("enabled")));

                daoEmployee.actualizar(employeeToUpdate);
                response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                break;
            case "search":
                String textBuscar = request.getParameter("textoBuscar");
                List<Employee> lista = daoEmployee.buscarPorNombreOId(textBuscar);

                request.setAttribute("lista", lista);
                request.setAttribute("busqueda", textBuscar);
                request.getRequestDispatcher("employee/lista.jsp").forward(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                break;
        }
    }
}

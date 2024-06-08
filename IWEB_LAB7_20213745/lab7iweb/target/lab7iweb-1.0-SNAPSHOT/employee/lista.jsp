<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab7iweb.beans.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="lista" scope="request" type="ArrayList<Employee>" />
<html>
    <head>
        <title>Empleados</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
              crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <div class="clearfix mt-3 mt-2">
                <a href="<%=request.getContextPath()%>/EmployeeServlet">
                    <h1 class="float-start link-dark">Lista de Empleados</h1>
                </a>
                <a class="btn btn-primary float-end mt-1" href="<%=request.getContextPath() %>/EmployeeServlet?action=new">Crear empleado</a>
            </div>
            <hr/>
            <form method="post" action="<%=request.getContextPath()%>/EmployeeServlet?action=s">
                <div class="form-floating mt-3">
                    <input type="text" class="form-control" id="floatingInput"
                           placeholder="Por ID o por nombre" name="textoBuscar" value="<%= request.getAttribute("busqueda") != null ? request.getAttribute("busqueda") : "" %>">
                    <label for="floatingInput">Buscar empleado</label>
                </div>
            </form>
            <table class="table table-striped mt-3">
                <tr class="table-primary">
                    <th>ID</th>
                    <th>Nombre Completo</th>
                    <th>Email</th>
                    <th>Teléfono</th>
                    <th>Fecha de Contratación</th>
                    <th>Salario</th>
                    <th>Acciones</th>
                </tr>
                <% for (Employee emp : lista) { %>
                <tr>
                    <td><%=emp.getEmployeeId()  %></td>
                    <td><%=emp.getFullNameEmployee() %></td>
                    <td><%=emp.getEmail() %></td>
                    <td><%=emp.getPhoneNumber() %></td>
                    <td><%=emp.getHireDate() %></td>
                    <td><%=emp.getSalary() %></td>
                    <td>
                        <a class="btn btn-success" href="<%=request.getContextPath()%>/EmployeeServlet?action=edit&id=<%= emp.getEmployeeId() %>">Editar</a>
                        <a onclick="return confirm('¿Está seguro de borrar?')" class="btn btn-danger" href="<%=request.getContextPath()%>/EmployeeServlet?action=del&id=<%= emp.getEmployeeId() %>">Borrar</a>
                    </td>
                </tr>
                <% } %>
            </table>
        </div>
    </body>
</html>

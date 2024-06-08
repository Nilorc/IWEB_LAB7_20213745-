<%@ page import="com.example.lab7iweb.beans.Job" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
              crossorigin="anonymous">
        <title>Editar trabajo</title>
    </head>
    <body>
        <div class='container'>
            <h1 class='mb-3'>Editar trabajo</h1>
            <form method="post" action="<%=request.getContextPath()%>/JobServlet">
                <input type="hidden" name="jobId" value="<%= ((Job) request.getAttribute("job")).getJobId() %>">
                <div class="mb-3">
                    <label>ID de Trabajo</label>
                    <input type="text" class="form-control" name="jobId" value="<%= ((Job) request.getAttribute("job")).getJobId() %>">
                </div>
                <div class="mb-3">
                    <label>Título del Trabajo</label>
                    <input type="text" class="form-control" name="jobTitle" value="<%= ((Job) request.getAttribute("job")).getJobTitle() %>">
                </div>
                <div class="mb-3">
                    <label>Salario Mínimo</label>
                    <input type="number" class="form-control" name="minSalary" value="<%= ((Job) request.getAttribute("job")).getMinSalary() %>">
                </div>
                <div class="mb-3">
                    <label>Salario Máximo</label>
                    <input type="number" class="form-control" name="maxSalary" value="<%= ((Job) request.getAttribute("job")).getMaxSalary() %>">
                </div>
                <a href="<%=request.getContextPath()%>/JobServlet" class="btn btn-danger">Regresar</a>
                <button type="submit" class="btn btn-primary">Guardar cambios</button>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
                crossorigin="anonymous"></script>
    </body>
</html>

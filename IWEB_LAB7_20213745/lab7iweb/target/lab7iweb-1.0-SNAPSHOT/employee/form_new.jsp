<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
              crossorigin="anonymous">
        <title>Crear un nuevo empleado</title>
    </head>
    <body>
        <div class='container'>
            <h1 class='mb-3'>Crear un nuevo empleado</h1>
            <form method="post" action="<%=request.getContextPath()%>/EmployeeServlet">
                <input type="hidden" name="action" value="crear">
                <div class="mb-3">
                    <label>Nombre Completo</label>
                    <input type="text" class="form-control" name="fullName">
                </div>
                <div class="mb-3">
                    <label>Email</label>
                    <input type="email" class="form-control" name="email">
                </div>
                <div class="mb-3">
                    <label>Contraseña</label>
                    <input type="password" class="form-control" name="password">
                </div>
                <div class="mb-3">
                    <label>Teléfono</label>
                    <input type="text" class="form-control" name="phoneNumber">
                </div>
                <div class="mb-3">
                    <label>Fecha de Contratación</label>
                    <input type="date" class="form-control" name="hireDate">
                </div>
                <div class="mb-3">
                    <label>ID de Trabajo</label>
                    <input type="text" class="form-control" name="jobId">
                </div>
                <div class="mb-3">
                    <label>Salario</label>
                    <input type="number" class="form-control" name="salary">
                </div>
                <div class="mb-3">
                    <label>Porcentaje de Comisión</label>
                    <input type="number" step="0.01" class="form-control" name="commissionPct">
                </div>
                <div class="mb-3">
                    <label>ID del Gerente</label>
                    <input type="number" class="form-control" name="managerId">
                </div>
                <div class="mb-3">
                    <label>ID del Departamento</label>
                    <input type="number" class="form-control" name="departmentId">
                </div>
                <div class="mb-3">
                    <label>Habilitado</label>
                    <input type="number" class="form-control" name="enabled">
                </div>
                <a href="<%=request.getContextPath()%>/EmployeeServlet" class="btn btn-danger">Regresar</a>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
                crossorigin="anonymous"></script>
    </body>
</html>

package com.example.lab7iweb.daos;

import com.example.lab7iweb.beans.Employee;

import java.sql.*;
import java.util.ArrayList;

public class DaoEmployee {

    String DB_URL = "jdbc:mysql://localhost:3306/hr";
    String DB_USER = "root";
    String DB_PASSWORD = "123456";

    public ArrayList<Employee> listar() {
        ArrayList<Employee> lista = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFullNameEmployee(rs.getString("first_name"), rs.getString("last_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPassword(rs.getString("password"));
                employee.setPhoneNumber(rs.getString("phone_number"));
                employee.setHireDate(rs.getDate("hire_date"));
                employee.setJobId(rs.getString("job_id"));
                employee.setSalary(rs.getInt("salary"));
                employee.setCommissionPct(rs.getDouble("commission_pct"));
                employee.setManagerId(rs.getInt("manager_id"));
                employee.setDepartmentId(rs.getInt("department_id"));
                employee.setEnabled(rs.getInt("enabled"));
                lista.add(employee);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public Employee buscarPorId(int id) {
        Employee employee = null;
        String sql = "SELECT * FROM employees WHERE employee_id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    employee = new Employee();
                    employee.setEmployeeId(rs.getInt("employee_id"));
                    employee.setFullNameEmployee(rs.getString("first_name"), rs.getString("last_name"));
                    employee.setEmail(rs.getString("email"));
                    employee.setPassword(rs.getString("password"));
                    employee.setPhoneNumber(rs.getString("phone_number"));
                    employee.setHireDate(rs.getDate("hire_date"));
                    employee.setJobId(rs.getString("job_id"));
                    employee.setSalary(rs.getInt("salary"));
                    employee.setCommissionPct(rs.getDouble("commission_pct"));
                    employee.setManagerId(rs.getInt("manager_id"));
                    employee.setDepartmentId(rs.getInt("department_id"));
                    employee.setEnabled(rs.getInt("enabled"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employee;
    }

    public void crear(Employee employee) {
        String sql = "INSERT INTO employees (employee_id, first_name, last_name, email, password, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id, enabled) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, employee.getEmployeeId());
            pstmt.setString(2, employee.getFullNameEmployee().split(" ")[0]);
            pstmt.setString(3, employee.getFullNameEmployee().split(" ")[1]);
            pstmt.setString(4, employee.getEmail());
            pstmt.setString(5, employee.getPassword());
            pstmt.setString(6, employee.getPhoneNumber());
            pstmt.setDate(7, employee.getHireDate());
            pstmt.setString(8, employee.getJobId());
            pstmt.setInt(9, employee.getSalary());
            pstmt.setDouble(10, employee.getCommissionPct());
            pstmt.setInt(11, employee.getManagerId());
            pstmt.setInt(12, employee.getDepartmentId());
            pstmt.setInt(13, employee.getEnabled());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizar(Employee employee) {
        String sql = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, password = ?, phone_number = ?, hire_date = ?, job_id = ?, salary = ?, commission_pct = ?, manager_id = ?, department_id = ?, enabled = ? WHERE employee_id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, employee.getFullNameEmployee().split(" ")[0]);
            pstmt.setString(2, employee.getFullNameEmployee().split(" ")[1]);
            pstmt.setString(3, employee.getEmail());
            pstmt.setString(4, employee.getPassword());
            pstmt.setString(5, employee.getPhoneNumber());
            pstmt.setDate(6, employee.getHireDate());
            pstmt.setString(7, employee.getJobId());
            pstmt.setInt(8, employee.getSalary());
            pstmt.setDouble(9, employee.getCommissionPct());
            pstmt.setInt(10, employee.getManagerId());
            pstmt.setInt(11, employee.getDepartmentId());
            pstmt.setInt(12, employee.getEnabled());
            pstmt.setInt(13, employee.getEmployeeId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrar(int employeeId) {
        String sql = "DELETE FROM employees WHERE employee_id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, employeeId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Employee> buscarPorNombreOId(String text) {
        ArrayList<Employee> lista = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE employee_id = ? OR lower(first_name) LIKE lower(?) OR lower(last_name) LIKE lower(?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, text);
            pstmt.setString(2, "%" + text + "%");
            pstmt.setString(3, "%" + text + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setEmployeeId(rs.getInt("employee_id"));
                    employee.setFullNameEmployee(rs.getString("first_name"), rs.getString("last_name"));
                    employee.setEmail(rs.getString("email"));
                    employee.setPassword(rs.getString("password"));
                    employee.setPhoneNumber(rs.getString("phone_number"));
                    employee.setHireDate(rs.getDate("hire_date"));
                    employee.setJobId(rs.getString("job_id"));
                    employee.setSalary(rs.getInt("salary"));
                    employee.setCommissionPct(rs.getDouble("commission_pct"));
                    employee.setManagerId(rs.getInt("manager_id"));
                    employee.setDepartmentId(rs.getInt("department_id"));
                    employee.setEnabled(rs.getInt("enabled"));
                    lista.add(employee);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
}

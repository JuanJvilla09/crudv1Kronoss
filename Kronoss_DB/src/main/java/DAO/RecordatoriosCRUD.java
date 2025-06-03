package DAO;

import model.Recordatorio;

import javax.swing.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

public class RecordatoriosCRUD {
    //Realizar conexion base de datos en MySQL
    //credenciales
    private final String url = "jdbc:mysql://localhost:3306/kronoss_";
    private final String user = "root";
    private final String password = "";
    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    //metodo para agregar recordatorio
    public void agregarRecordatorios(Recordatorio recordatorio){
        String sql = "INSERT INTO recordatorios (id, titulo, fecha_hora, tipo) VALUES (?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)) {
            stmtquery.setInt(1,recordatorio.getId());
            stmtquery.setString(2,recordatorio.getTitulo());
            stmtquery.setTimestamp(3, Timestamp.valueOf(recordatorio.getFecha_Hora()));
            stmtquery.setString(4,recordatorio.getTipo());
            stmtquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //metodo para listar recordatorio
    public List<Recordatorio> listarRecordatorios() {
        List<Recordatorio> lista = new ArrayList<>();
        String sql = "SELECT * FROM recordatorios";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Recordatorio recordatorio = new Recordatorio(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getTimestamp("fecha_hora").toLocalDateTime(),
                        rs.getString("tipo")
                );
                lista.add(recordatorio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    //metodo para buscar un recordatorio por titulo y id
    public List<Recordatorio> BuscarRecordatorioPortituloId(String titulo, Integer id) {
        List<Recordatorio> lista = new ArrayList<>();
        String sql = "SELECT * FROM recordatorios WHERE titulo = ? AND id = ?";

        try (PreparedStatement stmtquery = conectar().prepareStatement(sql)) {
            stmtquery.setString(1, titulo);
            stmtquery.setInt(2, id);

            try (ResultSet rs = stmtquery.executeQuery()) {
                while (rs.next()) {
                    Recordatorio recordatorio = new Recordatorio(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getTimestamp("fecha_hora").toLocalDateTime(),
                            rs.getString("tipo")
                    );
                    lista.add(recordatorio);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Puedes manejar la excepción aquí, o lanzar una excepción personalizada
        }
        return lista;
    }

    //metodo para actualizar
    public void actualizarRecordatorios(Recordatorio recordatorio){
        String sql = "UPDATE recordatorios SET titulo=?, fecha_hora=?, tipo=? WHERE id= ?";

        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)) {
            stmtquery.setString(1, recordatorio.getTitulo());
            stmtquery.setTimestamp(2, Timestamp.valueOf(recordatorio.getFecha_Hora()));
            stmtquery.setString(3, recordatorio.getTipo());
            stmtquery.setInt(4, recordatorio.getId());
            stmtquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //metodo para eliminar un recordatorio por id
    public void eliminarRecordatorio(Integer id){
        String sql = "DELETE FROM recordatorios WHERE id = ?";

        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)){
            stmtquery.setInt(1,id);
            stmtquery.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}


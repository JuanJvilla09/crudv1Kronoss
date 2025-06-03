package package_example;

import DAO.RecordatoriosCRUD;
import model.Recordatorio;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        RecordatoriosCRUD orecordatoriocrud = new RecordatoriosCRUD();

        Connection conn = null;

        try {
            conn=orecordatoriocrud.conectar();

            if (conn!=null && !conn.isClosed()){

                System.out.println("Conexión realizada correctamente...");

                System.out.print("Ingrese ID para el recordatorio: ");
                Integer id = sc.nextInt();
                sc.nextLine(); // Limpiar el salto de línea pendiente

                System.out.print("Ingrese el título del recordatorio: ");
                String titulo = sc.nextLine();

                System.out.print("Ingrese la fecha y hora del recordatorio (formato: yyyy-MM-dd HH:mm): ");
                String fechayhora = sc.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime fecha_hora = LocalDateTime.parse(fechayhora, formatter);

                System.out.print("Ingrese el tipo de recordatorio: ");
                String tipo = sc.nextLine();


                orecordatoriocrud.agregarRecordatorios(new Recordatorio(id, titulo, fecha_hora,tipo));
                System.out.println("Recordatorio agregado con exito...");

                orecordatoriocrud.listarRecordatorios().forEach(recordatorio ->
                        System.out.println(recordatorio.getId() + " | " + recordatorio.getTitulo() + " | " + recordatorio.getFecha_Hora() + " | " + recordatorio.getTipo()));

                


            }else {
                System.out.println("Conexion fallida....");
            }
        } catch (SQLException e){
            System.out.println("Error al conectar la base de datos");
            e.printStackTrace();
        }


    }
}
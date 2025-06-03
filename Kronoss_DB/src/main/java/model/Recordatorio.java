package model;

import java.time.LocalDateTime;

public class Recordatorio {
    private Integer id;
    private String titulo;
    private LocalDateTime fecha_Hora;
    private String tipo;

    public Recordatorio() {
    }

    public Recordatorio(Integer id, String titulo, LocalDateTime fecha_Hora, String tipo) {
        this.id = id;
        this.titulo = titulo;
        this.fecha_Hora = fecha_Hora;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getFecha_Hora() {
        return fecha_Hora;
    }

    public void setFecha_Hora(LocalDateTime fecha_Hora) {
        this.fecha_Hora = fecha_Hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

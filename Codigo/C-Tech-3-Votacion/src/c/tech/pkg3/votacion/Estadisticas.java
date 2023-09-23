/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.tech.pkg3.votacion;

/**
 *
 * @author losmelli
 */
public class Estadisticas {
    private String nombrePartido,descripcionVotacion;
    private int cantVotos,año;

    public Estadisticas(String nombre, String descripcion, int cantVotos, int año) {
        this.nombrePartido = nombre;
        this.descripcionVotacion = descripcion;
        this.cantVotos = cantVotos;
        this.año = año;
    }

    public String getNombrePartido() {
        return nombrePartido;
    }

    public String getDescripcionVotacion() {
        return descripcionVotacion;
    }

    public int getCantVotos() {
        return cantVotos;
    }

    public int getAño() {
        return año;
    }

    @Override
    public String toString() {
        return "Estadisticas{" + "nombrePartido=" + nombrePartido + ", descripcionVotacion=" + descripcionVotacion + ", cantVotos=" + cantVotos + ", a\u00f1o=" + año + '}';
    }
    
}

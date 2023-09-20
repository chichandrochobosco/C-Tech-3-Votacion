/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.tech.pkg3.votacion;

import java.util.List;



/**
 *
 * @author losmelli
 */
public class Votacion {
    private int id;
    private int año;
    private String descripcion;
    private List<Lista> listas;

    private int activa ;

    public Votacion(int id, int año, String descripcion, List<Lista> listas,int activa) {
        this.id = id;
        this.año = año;
        this.descripcion = descripcion;
        this.listas = listas;
        this.activa = activa;
    }

    public int getId() {
        return id;
    }

    public int getActiva() {
        return activa;
    }

    public int getAño() {
        return año;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<Lista> getListas() {
        return listas;
    }

    @Override
    public String toString() {
        return "Votacion{" +
                "id=" + id +
                ", año=" + año +
                ", descripcion='" + descripcion + '\'' +
                ", listas=" + listas.toString() +
                ", activa=" + activa +
                '}';
    }
}

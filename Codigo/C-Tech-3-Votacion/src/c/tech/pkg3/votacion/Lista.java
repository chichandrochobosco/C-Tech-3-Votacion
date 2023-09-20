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
public class Lista {

    private String nombre;

    private int idLista;
    private List<Representante> representantes;
    private int cantVotos;

    public Lista(String nombre, int idLista) {
        this.nombre = nombre;
        this.idLista = idLista;
    }

    public void setRepresentantes(List<Representante> representantes) {
        this.representantes = representantes;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdLista() {
        return idLista;
    }

    public int getCantVotos() {
        return cantVotos;
    }

    public void setCantVotos(int cantVotos) {
        this.cantVotos = cantVotos;
    }

    public List<Representante> getRepresentantes() {
        return representantes;
    }

    @Override
    public String toString() {
        return "Lista{" +
                "nombre='" + nombre + '\'' +
                ", idLista=" + idLista +
                ", representantes=" + representantes.toString() +
                ", cantVotos=" + cantVotos +
                '}';
    }
}

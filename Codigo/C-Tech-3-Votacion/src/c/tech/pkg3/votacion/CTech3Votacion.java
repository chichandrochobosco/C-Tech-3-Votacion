/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package c.tech.pkg3.votacion;

import c.tech.pkg3.votacion.dao.DaoVotacion;

/**
 *
 * @author ET36
 */
public class CTech3Votacion {

    public static void main(String[] args) {
        DaoVotacion daoVotacion = new DaoVotacion();
        for (Votacion v : daoVotacion.obtenerVotaciones()){
            System.out.println(v.toString());
        }

    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package c.tech.pkg3.votacion;

import Encriptador.EncriptadorContraseña;
import c.tech.pkg3.votacion.dao.DaoVotacion;
import c.tech.pkg3.votacion.vistas.añadirUsuario;

/**
 *
 * @author ET36
 */
public class CTech3Votacion {

    public static void main(String[] args) {
        
        añadirUsuario a = new añadirUsuario();
        a.setVisible(true);
        DaoVotacion daoVotacion = new DaoVotacion();
        for (Votacion v : daoVotacion.obtenerVotaciones()){
            System.out.println(v.toString());
        }

    }
    
}

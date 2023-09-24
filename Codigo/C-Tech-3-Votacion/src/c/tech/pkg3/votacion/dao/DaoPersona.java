/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c.tech.pkg3.votacion.dao;

import c.tech.pkg3.votacion.Persona;
import c.tech.pkg3.votacion.Rol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ET36
 */
public class DaoPersona {

    public Persona obtenerPersona(int dni) {
        try {
            String nom = "", apellido = "", contraseña = "";
            int telefono = 0;
            Rol rol = null;
            Connection con = SqlConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("Select * from persona where dni =?");
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nom = rs.getString(2);
                apellido = rs.getString(3);
                telefono = rs.getInt(4);
                contraseña = rs.getString(5);
                if (rs.getInt(6) == 1) {
                    rol = Rol.ADMIN;
                }else rol = Rol.PERSONA;
                return new Persona(dni, nom, apellido, telefono, contraseña,rol);
            }
            rs.close();
            ps.close();

            return null;
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void ingresarPersona(Persona persona) {
        Connection con = SqlConnection.getConnection();
        PreparedStatement ps = null;
        int rol = 0;
        if (persona.getRol().equals(Rol.ADMIN)) {
            rol = 1;
        } else {
            rol = 2;
        }
        try {
            ps = con.prepareStatement("insert into persona (dni,nombre,apellido,telefono,contraseña,rol) VALUES(?,?,?,?,?,?)");

            ps.setInt(1, persona.getDni());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getApellido());
            ps.setInt(4, persona.getTelefono());
            ps.setString(5, persona.getContraseña());
            ps.setInt(6, rol);
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(DaoPersona.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }

    }
}

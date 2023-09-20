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
public class Persona {
    private int dni;
    private String nombre;
    private String apellido;
    private int telefono;
    private String contraseña;
    private Rol rol;

    public Persona(int dni, String nombre, String apellido, int telefono, String contraseña,Rol rol) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public Rol getRol() {
        return rol;
    }

    
    
    public int getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getContraseña() {
        return contraseña;
    }
    
    
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Encriptador;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ET36
 */
public class EncriptadorContraseña {

    public String encriptarContraseña(String contraseña)  {
        byte[] hashedPassword = null;
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            
            hashedPassword = md.digest(contraseña.getBytes(StandardCharsets.UTF_8));
            System.out.println(hashedPassword);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncriptadorContraseña.class.getName()).log(Level.SEVERE, null, ex);
        }
      return hashedPassword.toString();
    }
}

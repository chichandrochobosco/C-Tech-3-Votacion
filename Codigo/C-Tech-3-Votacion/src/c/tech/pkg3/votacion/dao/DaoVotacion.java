/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.tech.pkg3.votacion.dao;

import c.tech.pkg3.votacion.Lista;
import c.tech.pkg3.votacion.Representante;
import c.tech.pkg3.votacion.Votacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author losmelli
 */
public class DaoVotacion {

    public List<Votacion> obtenerVotaciones(){
        List<Votacion> votaciones = new ArrayList<>();
        Connection con = SqlConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {



            CallableStatement cs = con.prepareCall("{CALL obtenerVotaciones}");
            cs.execute();
            rs = cs.getResultSet();

            while (rs.next()){
                int idLista = rs.getInt("id");
                votaciones.add(new Votacion(idLista,rs.getInt("ano"),rs.getString("descripcion"),getLista(idLista),rs.getInt("activa")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return votaciones;
    }



    private List<Lista> getLista(int idVotacion){
        List<Lista> listas = new ArrayList<>();
        Connection con = SqlConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            CallableStatement cs = con.prepareCall("{CALL obtenerListaVotacion(?)}");
            cs.setInt(1, idVotacion);
            cs.execute();
            rs = cs.getResultSet();

            while (rs.next()) {
                listas.add(new Lista(rs.getString("nombre"), rs.getInt("id")));
            }

            for (Lista lista : listas){
                lista.setRepresentantes(getRepresentantes(lista.getIdLista()));
                System.out.println(lista.getIdLista());
            }

            return listas;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }






    private List<Representante> getRepresentantes(int idLista){
        List<Representante> representantes = new ArrayList<>();
        Connection con = SqlConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            CallableStatement cs = con.prepareCall("{CALL obtenerRepresentantes(?)}");
            cs.setInt(1, idLista);
            cs.execute();
            rs = cs.getResultSet();

            while (rs.next()){
                representantes.add(new Representante(rs.getInt("id"), rs.getString("nombre"),rs.getString("apellido")));
            }

            return representantes;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }






    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.tech.pkg3.votacion.dao;

import c.tech.pkg3.votacion.Estadisticas;
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

    public List<Votacion> obtenerVotaciones() {
        List<Votacion> votaciones = new ArrayList<>();
        Connection con = SqlConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            CallableStatement cs = con.prepareCall("{CALL obtenerVotaciones}");
            cs.execute();
            rs = cs.getResultSet();

            while (rs.next()) {
                int idLista = rs.getInt("id");
                votaciones.add(new Votacion(idLista, rs.getInt("ano"), rs.getString("descripcion"), getListaPorIdVotacion(idLista), rs.getInt("activa")));
            }

            rs.close();
            cs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return votaciones;
    }

    private List<Lista> getListaPorIdVotacion(int idVotacion) {
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

            for (Lista lista : listas) {
                lista.setRepresentantes(getRepresentantesPorIdLista(lista.getIdLista()));
            }
            rs.close();
            cs.close();
            return listas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Representante> getRepresentantesPorIdLista(int idLista) {
        List<Representante> representantes = new ArrayList<>();
        Connection con = SqlConnection.getConnection();
        ResultSet rs = null;

        try {
            CallableStatement cs = con.prepareCall("{CALL obtenerRepresentantes(?)}");
            cs.setInt(1, idLista);
            cs.execute();
            rs = cs.getResultSet();

            while (rs.next()) {
                representantes.add(new Representante(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido")));
            }
            rs.close();

            return representantes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Lista obtenerListaPorNombre(String nombre) {
        ResultSet rs = null;
        Connection con = SqlConnection.getConnection();
        String query = "SELECT * from lista WHERE nombre=?";
        Lista lista = null;
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String nombreLista = rs.getString(2);
                lista = new Lista(nombreLista, id);
            }
            rs.close();

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void añadirLista(String nombre) {
        ResultSet rs = null;
        Connection con = SqlConnection.getConnection();
        String query = "insert into lista (nombre) values (?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, nombre);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void añadirRepresentante(String nombreLista, String nombre, String apellido) {
        Connection con = SqlConnection.getConnection();
        Lista lista = obtenerListaPorNombre(nombreLista);
        String query = "insert into representante (id_lista,nombre,apellido) values (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, lista.getIdLista());
            ps.setString(2, nombre);
            ps.setString(3, apellido);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void añadirVotacion(int año, String descripcion) {
        Connection con = SqlConnection.getConnection();
        String query = "insert into votacion (ano,descripcion,activa) values (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, año);
            ps.setString(2, descripcion);
            ps.setInt(3, 0);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void añadirListasAVotacion(String nombreVotacion, int idLista) {
        Connection con = SqlConnection.getConnection();
        String query = "insert into listas_votacion (id_votacion,id_lista) values (?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, obtenerIdVotacionPorNombre(nombreVotacion));
            ps.setInt(2, idLista);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Estadisticas> obtenerEstadisticas() {
        List<Estadisticas> estadisticas = new ArrayList<>();
        Connection con = SqlConnection.getConnection();
        ResultSet rs = null;

        try {
            CallableStatement cs = con.prepareCall("{CALL obtenerEstadisticas()}");
            cs.execute();
            rs = cs.getResultSet();

            while (rs.next()) {
                estadisticas.add(new Estadisticas(rs.getString("nombre"), rs.getString("descripcion"), rs.getInt("votos"), rs.getInt("ano")));
            }
            rs.close();

            return estadisticas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int obtenerIdVotacionPorNombre(String nombre) {
        ResultSet rs = null;
        Connection con = SqlConnection.getConnection();
        String query = "SELECT id from votacion WHERE descripcion=?";
        int id = -1;
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
            return id;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Estadisticas> obtenerEstadisticasIdVotacion(int idVotacion) {
        List<Estadisticas> estadisticas = new ArrayList<>();
        Connection con = SqlConnection.getConnection();
        ResultSet rs = null;

        try {
            CallableStatement cs = con.prepareCall("{CALL obtenerEstadisticaIdVotacion(?)}");
            cs.setInt(1, idVotacion);
            cs.execute();
            rs = cs.getResultSet();

            while (rs.next()) {
                estadisticas.add(new Estadisticas(rs.getString("nombre"), rs.getString("descripcion"), rs.getInt("votos"), rs.getInt("ano")));
            }
            rs.close();

            return estadisticas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void desactivarVotacion(int idVotacion) {
        Connection con = SqlConnection.getConnection();
        String query = "UPDATE votacion set activa = 1 where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idVotacion);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Lista> obtenerListas() {
        List<Lista> listas = new ArrayList<>();
        Connection con = SqlConnection.getConnection();
        ResultSet rs = null;

        try {
            CallableStatement cs = con.prepareCall("{CALL obtenerListas()}");
            cs.execute();
            rs = cs.getResultSet();

            while (rs.next()) {
                listas.add(new Lista(rs.getString(2), rs.getInt("id")));
            }
            rs.close();
            return listas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean personaVoto(int dni, int idVotacion) {
        ResultSet rs = null;
        Connection con = SqlConnection.getConnection();

        String query2 = "SELECT id from voto_persona WHERE id_persona=? and id_votacion = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query2);
            ps.setInt(1, dni);
            ps.setInt(2, idVotacion);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            rs.close();
            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void votar(int dni, int idVotacion, int idLista) {
        Connection con = SqlConnection.getConnection();
        String query1 = "insert into voto_persona(id_persona,id_votacion) VALUES (?,?)";
        String query2 = "insert into voto(id_votacion,id_lista) VALUES (?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query1);
            ps.setInt(1, dni);
            ps.setInt(2, idVotacion);
            ps.executeUpdate();

            ps = con.prepareStatement(query2);
            ps.setInt(1, idVotacion);
            ps.setInt(2, idLista);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

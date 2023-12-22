/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Antonio
 */
public class EspecieDAO extends DAO {

    private static EspecieDAO instance;

    private EspecieDAO() {
        getConnection();
        createTable();
    }

    public static EspecieDAO getInstance() {
        return (instance == null ? (instance = new EspecieDAO()) : instance);
    }

    public Especie create(String nome) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO Especie (nome) VALUES (?)");
            stmt.setString(1, nome);
            executeUpdate(stmt);

        } catch (SQLException ex) {
            Logger.getLogger(EspecieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("Especie", "id"));
    }

    public boolean isLastEmpty() {
        Especie lastAnimal = this.retrieveById(lastId("Especie", "id"));
        if (lastAnimal != null) {
            return lastAnimal.getNome().isBlank();
        }
        return false;
    }

    private Especie buildObject(ResultSet rs) {
        Especie especie = null;
        try {
            especie = new Especie(rs.getInt("id"), rs.getString("nome"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return especie;
    }

    public List retrieve(String query) {
        List<Especie> animais = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                animais.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return animais;
    }
    
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM Especie");
    }

    public Especie retrieveById(int id) {
        List<Especie> animais = this.retrieve("SELECT * FROM Especie WHERE id = " + id);
        return (animais.isEmpty() ? null : animais.get(0));
    }

    public Especie retrieveByName(String name) {
        List<Especie> animais = this.retrieve("SELECT * FROM Especie WHERE nome LIKE '%" + name + "%'");
        return (animais.isEmpty() ? null : animais.get(0));
    }
    
    public List retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM Especie WHERE nome LIKE '%" + nome + "%'");
    }
    
    public void update(Especie especie) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE Especie SET nome=? WHERE id=?");
            stmt.setObject(1, especie.getNome());
            stmt.setInt(2, especie.getId());

            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    public void delete(int especie) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM Especie WHERE id = ?");
            stmt.setInt(1, especie);
            executeUpdate(stmt);  
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
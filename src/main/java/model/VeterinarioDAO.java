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
public class VeterinarioDAO extends DAO {

    private static VeterinarioDAO instance;

    private VeterinarioDAO() {
        getConnection();
        createTable();
    }

    public static VeterinarioDAO getInstance() {
        return (instance == null ? (instance = new VeterinarioDAO()) : instance);
    }

    public Veterinario create(String nome, String endereco, String email, String telefone) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO Veterinario (nome, endereco, email, telefone) VALUES (?, ?, ?, ?)");
            stmt.setString(1, nome);
            stmt.setString(2, endereco);
            stmt.setString(3, email);
            stmt.setString(4, telefone);
            executeUpdate(stmt);

        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("Veterinario", "id"));
    }

    public boolean isLastEmptys() {
        Veterinario lastVet = this.retrieveById(lastId("Veterinario", "id"));
        if (lastVet != null) {
            return lastVet.getNome().isBlank();
        }
        return false;
    }

    private Veterinario buildObject(ResultSet rs) {
        Veterinario veterinario = null;
        try {
            veterinario = new Veterinario(rs.getInt("id"), rs.getString("nome"), rs.getString("endereco"),
            rs.getString("email"), rs.getString("telefone"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return veterinario;
    }

    public List retrieve(String query) {
        List<Veterinario> veterinarios = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                veterinarios.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return veterinarios;
    }

    public Veterinario retrieveById(int id) {
        List<Veterinario> veterinarios = this.retrieve("SELECT * FROM Veterinario WHERE id = " + id);
        return (veterinarios.isEmpty() ? null : veterinarios.get(0));
    }
    
    public List retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM Veterinario WHERE nome LIKE '%" + nome + "%'");
    }

    public List retrieveAll() {
        return this.retrieve("SELECT * FROM Veterinario");
    }
    
    public void delete(int veterinario) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM Veterinario WHERE id = ?");
            stmt.setInt(1, veterinario);
            executeUpdate(stmt);  
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void update(Veterinario veterinario) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE Veterinario SET nome=?, endereco=?, email=?, telefone=? WHERE id=?");
            stmt.setString(1, veterinario.getNome());
            stmt.setString(2, veterinario.getEndereco());
            stmt.setString(3, veterinario.getEmail());
            stmt.setString(4, veterinario.getTelefone());
            stmt.setInt(5, veterinario.getId());

            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
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
public class ExameDAO extends DAO {

    private static ExameDAO instance;

    private ExameDAO() {
        getConnection();
        createTable();
    }

    public static ExameDAO getInstance() {
        return (instance == null ? (instance = new ExameDAO()) : instance);
    }

    public Exame create(int id_consulta, String descricao, String resultado) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO Exame (id_consulta, descricao, resultado) VALUES (?, ?, ?)");
            stmt.setInt(1, id_consulta);
            stmt.setString(2, descricao);
            stmt.setString(3, resultado);
            executeUpdate(stmt);

        } catch (SQLException ex) {
            Logger.getLogger(ExameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("Exame", "id"));
    }

    public boolean isLastEmpty() {
        Exame lastExam = this.retrieveById(lastId("Exame", "id_exam"));
        if (lastExam != null) {
            return lastExam.getDescricao().isBlank();
        }
        return false;
    }

    private Exame buildObject(ResultSet rs) {
        Exame exame = null;
        try {
            exame = new Exame(rs.getInt("id"), rs.getInt("id_consulta"), rs.getString("descricao"), rs.getString("resultado"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return exame;
    }

    public List retrieve(String query) {
        List<Exame> exames = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                exames.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return exames;
    }

    public List retrieveAll() {
        return this.retrieve("SELECT * FROM Exame");
    }

    public List retrieveByConsultId(int id_consulta) {
        return this.retrieve("SELECT * FROM Exame WHERE id_consulta = " + id_consulta);
    }

    public Exame retrieveById(int id) {
        List<Exame> exames = this.retrieve("SELECT * FROM Exame WHERE id = " + id);
        return (exames.isEmpty() ? null : exames.get(0));
    }

    public void update(Exame exame) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE Exame SET descricao=?, resultado=? WHERE id=?");
            stmt.setObject(1, exame.getDescricao());
            stmt.setString(2, exame.getResultado());
            stmt.setInt(3, exame.getId());

            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    public void delete(int exame) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM Exame WHERE id = ?");
            stmt.setInt(1, exame);
            executeUpdate(stmt);  
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
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
public class TratamentoDAO extends DAO {

    private static TratamentoDAO instance;

    private TratamentoDAO() {
        getConnection();
        createTable();
    }

    public static TratamentoDAO getInstance() {
        return (instance == null ? (instance = new TratamentoDAO()) : instance);
    }

    public Tratamento create(int id_animal, String data_inicio, String data_final, int finalizado) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO Tratamento (id_animal, data_inicio, data_final, finalizado) VALUES(?, ?, ?, ?)");
            stmt.setInt(1, id_animal);
            stmt.setString(2, data_inicio);
            stmt.setString(3, data_final);
            stmt.setInt(4, finalizado);
            executeUpdate(stmt);

        } catch (SQLException ex) {
            Logger.getLogger(TratamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("Tratamento", "id"));
    }

    private Tratamento buildObject(ResultSet rs) {
        Tratamento tratamento = null;
        try {
            tratamento = new Tratamento(rs.getInt("id"), rs.getInt("id_animal"), rs.getString("data_inicio"), rs.getString("data_final"), rs.getString("finalizado"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tratamento;
    }

    public List retrieve(String query) {
        List<Tratamento> tratamentos = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                tratamentos.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tratamentos;
    }

    public List retrieveAll() {
        return this.retrieve("SELECT * FROM Tratamento");
    }

    public Tratamento retrieveByAnimalId(int id_animal) {
        List<Tratamento> tratamentos = this.retrieve("SELECT * FROM Tratamento WHERE id_animal = " + id_animal);
        return (tratamentos.isEmpty() ? null : tratamentos.get(0));
    }
    
    public List retrieveByAnimalId2(int id_animal) {
        return this.retrieve("SELECT * FROM Tratamento WHERE id_animal = " + id_animal);
    }

    public Tratamento retrieveById(int id) {
        List<Tratamento> tratamentos = this.retrieve("SELECT * FROM Tratamento WHERE id = " + id);
        return (tratamentos.isEmpty() ? null : tratamentos.get(0));
    }

    public void update(Tratamento tratamento) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE Tratamento SET finalizado=?, data_inicio=?, data_final=? WHERE id=?");
            stmt.setString(1, tratamento.getFinalizado());
            stmt.setString(3, tratamento.getData_final());
            stmt.setString(2, tratamento.getData_inicio());
            stmt.setInt(4, tratamento.getId());

            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    public void delete(int tratamento) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM Tratamento WHERE id = ?");
            stmt.setInt(1, tratamento);
            executeUpdate(stmt);  
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
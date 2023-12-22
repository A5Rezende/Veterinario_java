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
public class ConsultaDAO extends DAO {

    private static ConsultaDAO instance;

    private ConsultaDAO() {
        getConnection();
        createTable();
    }

    public static ConsultaDAO getInstance() {
        return (instance == null ? (instance = new ConsultaDAO()) : instance);
    }

    public Consulta create(int id_tratamento, int id_veterinario, int id_animal, String sintomas, String data_conclusao) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO Consulta (id_tratamento, id_veterinario, id_animal, sintomas, data_conclusao) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, id_tratamento);
            stmt.setInt(2, id_veterinario);
            stmt.setInt(3, id_animal);
            stmt.setString(4, sintomas);
            stmt.setString(5, data_conclusao);
            executeUpdate(stmt);

        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("Consulta", "id"));
    }

    private Consulta buildObject(ResultSet rs) {
        Consulta consulta = null;
        try {
            consulta = new Consulta(rs.getInt("id"), rs.getInt("id_tratamento"), rs.getInt("id_veterinario"), rs.getInt("id_animal"), rs.getString("sintomas"), rs.getString("data_conclusao"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consulta;
    }

    public List retrieve(String query) {
        List<Consulta> consultas = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                consultas.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consultas;
    }

    public List retrieveAll() {
        return this.retrieve("SELECT * FROM Consulta");
    }

    public Consulta retrieveById(int id) {
        List<Consulta> consultas = this.retrieve("SELECT * FROM Consulta WHERE id = " + id);
        return (consultas.isEmpty() ? null : consultas.get(0));
    }

    public List retrieveByTratId(int id_trat) {
        return this.retrieve("SELECT * FROM Consulta WHERE id_tratamento = " + id_trat);
    }

    public List retrieveByAnimalId(int id_animal) {
        return this.retrieve("SELECT * FROM Consulta WHERE id_animal = " + id_animal);
    }

    public List retrieveByVetId(int id_vet) {
        return this.retrieve("SELECT * FROM Consulta WHERE id_veterinario = " + id_vet);

    }

    public void update(Consulta consulta) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE Consulta SET data_conclusao=?, sintomas=? WHERE id=?");
            stmt.setObject(1, consulta.getData_conclusao());
            stmt.setString(2, consulta.getSintomas());
            stmt.setInt(3, consulta.getId());

            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    public void delete(int consulta) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM Consulta WHERE id = ?");
            stmt.setInt(1, consulta);
            executeUpdate(stmt);  
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}

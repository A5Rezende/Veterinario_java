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
public class AnimalDAO extends DAO {

    private static AnimalDAO instance;

    private AnimalDAO() {
        getConnection();
        createTable();
    }

    public static AnimalDAO getInstance() {
        return (instance == null ? (instance = new AnimalDAO()) : instance);
    }

    public Animal create(int id_cliente, int id_especie, String nome, String idade, String sexo) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO Animal (id_cliente, id_especie, nome, idade, sexo) VALUES (?,?,?,?,?)");
            stmt.setInt(1, id_cliente);
            stmt.setInt(2, id_especie);
            stmt.setString(3, nome);
            stmt.setString(4, idade);
            stmt.setString(5, sexo);
            executeUpdate(stmt);

        } catch (SQLException ex) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("Animal", "id"));
    }

    public boolean isLastEmpty() {
        Animal lastAnimal = this.retrieveById(lastId("Animal", "id"));
        if (lastAnimal != null) {
            return lastAnimal.getNome().isBlank();
        }
        return false;
    }

    private Animal buildObject(ResultSet rs) {
        Animal animal = null;
        try {
            animal = new Animal(rs.getInt("id"), rs.getInt("id_cliente"), rs.getInt("id_especie"), rs.getString("nome"), rs.getString("idade"), rs.getString("sexo"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return animal;
    }

    public List retrieve(String query) {
        List<Animal> animais = new ArrayList();
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

    public Animal retrieveById(int id) {
        List<Animal> animais = this.retrieve("SELECT * FROM Animal WHERE id = " + id);
        return (animais.isEmpty() ? null : animais.get(0));
    }
    
    public List retrieveAll(){
        return this.retrieve("SELECT * FROM Animal");
    }
    
    public List retrieveByClienteId(int id){
        return this.retrieve("SELECT * FROM Animal WHERE id_cliente = " + id);
    }
    
    public void delete(int animal) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM Animal WHERE id = ?");
            stmt.setInt(1, animal);
            executeUpdate(stmt);  
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void update(Animal animal) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE Animal SET nome=?, idade=?, sexo=?, id_especie=?, id_cliente=?"
                    + " WHERE id=?");
            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getIdade());
            stmt.setString(3, animal.getSexo());
            stmt.setInt(4, animal.getId_especie());
            stmt.setInt(5, animal.getId_cliente());
            stmt.setInt(6, animal.getId());

            executeUpdate(stmt);

        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());

        }
    }
}

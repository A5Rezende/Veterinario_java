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
public class ClienteDAO extends DAO {

    private static ClienteDAO instance;

    private ClienteDAO() {
        getConnection();
        createTable();
    }

    public static ClienteDAO getInstance() {
        return (instance == null ? (instance = new ClienteDAO()) : instance);
    }

    public Cliente create(String nome, String endereco, String cep, String telefone, String email) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO Cliente (nome, endereco, cep, telefone, email) VALUES (?,?,?,?,?)");
            stmt.setString(1, nome);
            stmt.setString(2, endereco);
            stmt.setString(3, cep);
            stmt.setString(4, telefone);
            stmt.setString(5, email);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("Cliente", "id"));
    }

    public boolean isLastEmpty() {
        Cliente lastClient = this.retrieveById(lastId("Cliente", "id"));
        if (lastClient != null) {
            return lastClient.getNome().isBlank();
        }
        return false;
    }

    private Cliente buildObject(ResultSet rs) {
        Cliente cliente = null;
        try {
            cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("endereco"), rs.getString("cep"), rs.getString("email"),
                    rs.getString("telefone"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return cliente;
    }

    public List retrieve(String query) {
        List<Cliente> clientes = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                clientes.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return clientes;
    }

    public List retrieveAll() {
        return this.retrieve("SELECT * FROM Cliente");
    }

    public List retrieveLast() {
        return this.retrieve("SELECT * FROM Cliente WHERE id = " + lastId("Cliente", "id"));
    }

    public Cliente retrieveById(int id) {
        List<Cliente> clientes = this.retrieve("SELECT * FROM Cliente WHERE id = " + id);
        return (clientes.isEmpty() ? null : clientes.get(0));
    }

    public List retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM Cliente WHERE nome LIKE '%" + nome + "%'");
    }

    public void update(Cliente cliente) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE Cliente SET nome=?, endereco=?, cep=?, email=?,"
                    + "telefone=? WHERE id=?");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getCep());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getTelefone());
            stmt.setInt(6, cliente.getId());
            
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public void delete(int cliente) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM Cliente WHERE id = ?");
            stmt.setInt(1, cliente);
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}

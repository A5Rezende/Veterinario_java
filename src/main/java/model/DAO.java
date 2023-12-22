/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antonio
 */
public abstract class DAO {

    public static final String DBURL = "jdbc:sqlite:veterinaria-v9.db";
    private static Connection con;
    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Connect to SQLite
    public static Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(DBURL);
                if (con != null) {
                    DatabaseMetaData meta = con.getMetaData();
                }
            } catch (SQLException e) {
                System.err.println("Exception: " + e.getMessage());
            }
        }
        return con;
    }

    protected ResultSet getResultSet(String query) {
        Statement s;
        ResultSet rs = null;
        try {
            s = (Statement) con.createStatement();
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return rs;
    }

    protected int executeUpdate(PreparedStatement queryStatement) throws SQLException {
        int update;
        update = queryStatement.executeUpdate();
        return update;
    }

    protected int lastId(String tableName, String primaryKey) {
        Statement s;
        int lastId = -1;
        try {
            s = (Statement) con.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(" + primaryKey + ") AS id FROM " + tableName);
            if (rs.next()) {
                lastId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return lastId;
    }

    public static void terminar() {
        try {
            (DAO.getConnection()).close();
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    protected final boolean createTable() {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Cliente( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR, \n"
                    + "endereco VARCHAR, \n"
                    + "cep VARCHAR, \n"
                    + "telefone VARCHAR, \n"
                    + "email VARCHAR); \n");
            executeUpdate(stmt);

            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Animal( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "id_cliente INTEGER, \n"
                    + "id_especie INTEGER, \n"
                    + "nome VARCHAR, \n"
                    + "idade VARCHAR, \n"
                    + "sexo VARCHAR); \n");
            executeUpdate(stmt);

            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Especie( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR); \n");
            executeUpdate(stmt);

            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Veterinario( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR, \n "
                    + "endereco VARCHAR, \n "
                    + "email VARCHAR, \n "
                    + "telefone VARCHAR); \n");
            executeUpdate(stmt);

            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Tratamento( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "id_animal INTEGER, \n"
                    + "data_inicio VARCHAR, \n"
                    + "data_final VARCHAR, \n"
                    + "finalizado VARCHAR); \n");
            executeUpdate(stmt);

            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Consulta( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "id_tratamento INTEGER, \n"
                    + "id_veterinario INTEGER, \n"
                    + "id_animal INTEGER, \n"
                    + "sintomas VARCHAR, \n"
                    + "data_conclusao VARCHAR); \n");
            executeUpdate(stmt);

            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Exame( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "id_consulta INTEGER, \n"
                    + "descricao VARCHAR, \n"
                    + "resultado VARCHAR); \n");
            executeUpdate(stmt);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}

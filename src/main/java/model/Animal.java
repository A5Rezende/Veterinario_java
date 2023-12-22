/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Antonio
 */
public class Animal {
    
    private int id;
    private String nome;
    private String idade;
    private String sexo;
    private int id_cliente;
    private int id_especie;

    public Animal(int id,int id_cliente, int id_especie, String nome, String idade, String sexo) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.id_cliente = id_cliente;
        this.id_especie = id_especie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_especie() {
        return id_especie;
    }

    public void setId_especie(int id_especie) {
        this.id_especie = id_especie;
    }
    
    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
               "Idade: " + idade + "\n" +
               "Sexo: " + sexo + "\n" +
               "Cliente: " + id_cliente + "\n" +
               "ESpecie: " + id_especie;
    }
    
}

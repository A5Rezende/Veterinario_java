/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Antonio
 */
public class Consulta {
    private int id;
    private int id_tratamento;
    private int id_veterinario;
    private int id_animal;
    private String sintomas;
    private String data_conclusao;

    public Consulta(int id, int id_tratamento, int id_veterinario, int id_animal, String sintomas, String data_conclusao) {
        this.id = id;
        this.id_tratamento = id_tratamento;
        this.id_veterinario = id_veterinario;
        this.id_animal = id_animal;
        this.sintomas = sintomas;
        this.data_conclusao = data_conclusao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_tratamento() {
        return id_tratamento;
    }

    public void setId_tratamento(int id_tratamento) {
        this.id_tratamento = id_tratamento;
    }

    public int getId_veterinario() {
        return id_veterinario;
    }

    public void setId_veterinario(int id_veterinario) {
        this.id_veterinario = id_veterinario;
    }

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getData_conclusao() {
        return data_conclusao;
    }

    public void setData_conclusao(String data_conclusao) {
        this.data_conclusao = data_conclusao;
    }
    
    @Override
    public String toString() {
        return "id: " + id + "\n" +
               "tratamento: " + id_tratamento + "\n" +
               "veterinario: " + id_veterinario + "\n" +
               "animal: " + id_animal + "\n" +
               "Sintomas: " + sintomas + "\n" +
               "Data de conclusao: " + data_conclusao;
    }
    
}

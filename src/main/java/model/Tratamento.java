/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Antonio
 */
public class Tratamento {
    private int id;
    private int id_animal;
    private String data_inicio;
    private String data_final;
    private String finalizado;

    public Tratamento(int id, int id_animal, String data_inicio, String data_final, String finalizado) {
        this.id = id;
        this.id_animal = id_animal;
        this.data_inicio = data_inicio;
        this.data_final = data_final;
        this.finalizado = finalizado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public String getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getData_final() {
        return data_final;
    }

    public void setData_final(String data_final) {
        this.data_final = data_final;
    }

    public String getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(String finalizado) {
        this.finalizado = finalizado;
    }
    
    @Override
    public String toString() {
        return "id: " + id + "\n" +
               "Animal: " + id_animal + "\n" +
               "Inicio: " + data_inicio + "\n" +
               "Final: " + data_final + "\n" +
               "Finalizado: " + finalizado;
    }
}

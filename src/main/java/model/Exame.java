/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Antonio
 */
public class Exame {
    private int id;
    private int id_consulta;
    private String descricao;
    private String resultado;

    public Exame(int id, int id_consulta, String descricao, String resultado) {
        this.id = id;
        this.id_consulta = id_consulta;
        this.descricao = descricao;
        this.resultado = resultado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    @Override
    public String toString() {
        return "id: " + id + "\n" +
               "Consulta: " + id_consulta + "\n" +
               "descricao: " + descricao + "\n" +
               "resultado: " + resultado;
    }
}

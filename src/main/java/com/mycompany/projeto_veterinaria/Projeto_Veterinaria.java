/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projeto_veterinaria;

import View.Tela;
import controller.Controller;
import model.Animal;
import model.Cliente;
import model.ClienteDAO;
import model.AnimalDAO;
import model.Consulta;
import model.ConsultaDAO;
import model.Especie;
import model.EspecieDAO;
import model.Exame;
import model.ExameDAO;
import model.Tratamento;
import model.TratamentoDAO;
import model.Veterinario;
import model.VeterinarioDAO;
import view.Tela02;

/**
 *
 * @author Antonio
 */
public class Projeto_Veterinaria {

    public static void main(String[] args) {
        
        /*
        ClienteDAO.getInstance().create("teste", "teste", "teste", "teste", "teste");
        AnimalDAO.getInstance().create(1, 2, "Teste", 3, "M");
        ConsultaDAO.getInstance().create(1, 1, 1, "Gripe", "Indefinido");
        EspecieDAO.getInstance().create("Gato");
        ExameDAO.getInstance().create(1, "Esta com Gripe", "Melhora");
        TratamentoDAO.getInstance().create(1, "Teste", "Teste", 0);
        VeterinarioDAO.getInstance().create("Teste", "teste", "teste", "teste");
        
        
        Cliente c1 = ClienteDAO.getInstance().retrieveById(1);
        System.out.println(c1);
        Animal a1 = AnimalDAO.getInstance().retrieveById(1);
        System.out.println(a1);
        Consulta co1 = ConsultaDAO.getInstance().retrieveById(1);
        System.out.println(co1);
        Especie e1 = EspecieDAO.getInstance().retrieveById(1);
        System.out.println(e1);
        Exame ex1 = ExameDAO.getInstance().retrieveById(1);
        System.out.println(ex1);
        Tratamento t1 = TratamentoDAO.getInstance().retrieveById(1);
        System.out.println(t1);
        Veterinario v1 = VeterinarioDAO.getInstance().retrieveById(1);
        System.out.println(v1);
        */  
        
        Tela02 main = new Tela02();
        main.show();
    }
}

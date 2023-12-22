/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import View.AnimalTableModel;
import View.ClienteTableModel;
import View.ConsultaTableModel;
import View.EspecieTableModel;
import View.ExameTableModel;
import View.GenericTableModel;
import View.TratamentoTableModel;
import View.VeterinarioTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.Animal;
import model.AnimalDAO;
import model.Cliente;
import model.ClienteDAO;
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

/**
 *
 * @author Antonio
 */
public class Controller {
    private static int veterinarioSelecionado = -1;
    private static int clienteSelecionado = -1;
    private static int animalSelecionado = -1;
    private static int especieSelecionado = -1;
    private static int tratamentoSelecionado = -1;
    private static int consultaSelecionado = -1;
    private static int exameSelecionado = -1;
    
    public static void setTableModel(JTable table, GenericTableModel tableModel) {
        table.setModel(tableModel);
    }
    
    public static int getVeterinarioSelecionado () {
        return veterinarioSelecionado;
    }
    
    public static int getClienteSelecionado () {
        return clienteSelecionado;
    }
    
    public static int getAnimalSelecionado () {
        return animalSelecionado;
    }
    
    public static int getEspecieSelecionado () {
        return especieSelecionado;
    }
    
    public static int getTratamentoSelecionado () {
        return tratamentoSelecionado;
    }
    
    public static int getConsultaSelecionado () {
        return consultaSelecionado;
    }
    
    public static int getExameSelecionado () {
        return exameSelecionado;
    }
    
    public static void setVeterinarioSelecionado (int veterinario) {
        veterinarioSelecionado = veterinario;
    }
    
    public static void setClienteSelecionado (int cliente) {
        clienteSelecionado = cliente;
    }
    
    public static void setAnimalSelecionado (int animal) {
        animalSelecionado = animal;
    }
    
    public static void setEspecieSelecionado (int especie) {
        especieSelecionado = especie;
    }
    
    public static void setTratamentoSelecionado (int tratamento) {
        tratamentoSelecionado = tratamento;
    }
    
    public static void setConsultaSelecionado (int consulta) {
        consultaSelecionado = consulta;
    }
    
    public static void setExameSelecionado (int exame) {
        exameSelecionado = exame;
    }
    
    public static String getNomeAnimalSelecionado () {
        Animal animal = AnimalDAO.getInstance().retrieveById(animalSelecionado);
        return animal.getNome();
    }
    
    public static String getNomeClienteSelecionado () {
        Cliente cliente = ClienteDAO.getInstance().retrieveById(clienteSelecionado);
        return cliente.getNome();
    }
    
    public static void setModelVeterinario(JTable table) {
        setTableModel(table, new VeterinarioTableModel(VeterinarioDAO.getInstance().retrieveAll()));
    }
    
    public static void setModelCliente(JTable table) {
        setTableModel(table, new ClienteTableModel(ClienteDAO.getInstance().retrieveAll()));
    }
    
    public static void setModelAnimal(JTable table) {
        if (clienteSelecionado != -1) {
            setTableModel(table, new AnimalTableModel(AnimalDAO.getInstance().retrieveByClienteId(clienteSelecionado)));
        }
        else {
            setTableModel(table, new AnimalTableModel(AnimalDAO.getInstance().retrieveAll()));
        }
    }
    
    public static void setModelEspecie(JTable table) {
        setTableModel(table, new EspecieTableModel(EspecieDAO.getInstance().retrieveAll()));
    }
    
    public static void setModelTratamento(JTable table) {
        if (animalSelecionado != -1) {
            setTableModel(table, new TratamentoTableModel((List)TratamentoDAO.getInstance().retrieveByAnimalId2(animalSelecionado)));
        }
        else {
            setTableModel(table, new TratamentoTableModel(new ArrayList()));
        }
    }
    
    public static void setModelConsulta(JTable table) {
        if (tratamentoSelecionado != -1) {
            setTableModel(table, new ConsultaTableModel(ConsultaDAO.getInstance().retrieveByTratId(tratamentoSelecionado)));
        }
        else {
            setTableModel(table, new ConsultaTableModel(new ArrayList()));
        }
    }
    
    public static void setModelExame(JTable table) {
        if (tratamentoSelecionado != -1) {
            setTableModel(table, new ExameTableModel(ExameDAO.getInstance().retrieveByConsultId(consultaSelecionado)));
        }
        else {
            setTableModel(table, new ExameTableModel(new ArrayList()));
        }
    }
    
    public static List<Object> getVeterinarioNomeParecido(String nomeParcial) {
        return VeterinarioDAO.getInstance().retrieveBySimilarName(nomeParcial);
    }
    
    public static List<Object> getClienteNomeParecido(String nomeParcial) {
        return ClienteDAO.getInstance().retrieveBySimilarName(nomeParcial);
    }
    
    public static List<Object> getEspecieNomeParecido(String nomeParcial) {
        return EspecieDAO.getInstance().retrieveBySimilarName(nomeParcial);
    }
    
    public static List<Object> getTratamentopeloIdAnimal(int Id) {
        return TratamentoDAO.getInstance().retrieveByAnimalId2(Id);
    }
    
    public static void apagaVeterinario(int veterinario) {
        VeterinarioDAO.getInstance().delete(veterinario);
    }
    
    public static void apagaCliente(int cliente) {
        List<Animal> animais = AnimalDAO.getInstance().retrieveByClienteId(cliente);
        for (Animal animal : animais) {
            AnimalDAO.getInstance().delete(animal.getId());
        }
        ClienteDAO.getInstance().delete(cliente);
    }
    
    public static void apagaAnimal(int animal) {
        AnimalDAO.getInstance().delete(animal);
    }
    
    public static void apagaEspecie(int especie) {
        EspecieDAO.getInstance().delete(especie);
    }
    
    public static void apagaTratamento(int tratamento) {
        List<Consulta> consultas = ConsultaDAO.getInstance().retrieveByTratId(tratamento);
        for (Consulta consulta : consultas) {
            apagaConsulta(consulta.getId());
        }
        TratamentoDAO.getInstance().delete(tratamento);
    }
    
    public static void apagaConsulta(int consulta) {
        List<Exame> exames = ExameDAO.getInstance().retrieveByConsultId(consulta);
        for (Exame exame : exames) {
            ExameDAO.getInstance().delete(exame.getId());
        }
        ConsultaDAO.getInstance().delete(consulta);
    }
    
    public static void apagaExame(int exame) {
        ExameDAO.getInstance().delete(exame);
    }
    
    public static Veterinario adicionaVeterinario(String nome, String endereco, String email, String telefone) {
        return VeterinarioDAO.getInstance().create(nome, endereco, email, telefone);
    }
    
    public static Cliente adicionaCliente(String nome, String endereco, String cep, String telefone, String email) {
        return ClienteDAO.getInstance().create(nome, endereco, cep, telefone, email);
    }
    
    public static Animal adicionaAnimal(int id_cliente, int id_especie, String nome, String idade, String sexo) {
        return AnimalDAO.getInstance().create(id_cliente, id_especie, nome, idade, sexo);
    }
    
    public static Especie adicionaEspecie(String nome) {
        return EspecieDAO.getInstance().create(nome);
    }
    
    public static Tratamento adicionaTratamento(int id_animal, String data_inicio, String data_final, int finalizado) {
        return TratamentoDAO.getInstance().create(id_animal, data_inicio, data_final, finalizado);
    }
    
    public static Consulta adicionaConsulta(int id_tratamento, int id_veterinario, String sintomas, String data_conclusao) {
        int id_animal = TratamentoDAO.getInstance().retrieveById(id_tratamento).getId_animal();
        return ConsultaDAO.getInstance().create(id_tratamento, id_veterinario, id_animal, sintomas, data_conclusao);
    }
    
    public static Exame adicionaExame(int id_consulta, String descricao, String resultado) {
        return ExameDAO.getInstance().create(id_consulta, descricao, resultado);
    }
    
    public static void alteraVeterinario(int id, String nome, String endereco, String email, String telefone) {
        Veterinario veterinario = new Veterinario(id, nome, endereco, email, telefone);
        VeterinarioDAO.getInstance().update(veterinario);
    }
    
    public static void alteraCliente(int id, String nome, String endereco, String cep, String email, String telefone) {
        Cliente cliente = new Cliente(id, nome, endereco, cep, email, telefone);
        ClienteDAO.getInstance().update(cliente);
    }
    
    public static void alteraAnimal(int id, String nome, String idade, String sexo) {
        int id_cliente = AnimalDAO.getInstance().retrieveById(id).getId_cliente();
        int id_especie = AnimalDAO.getInstance().retrieveById(id).getId_especie();
        Animal animal = new Animal(id, id_cliente, id_especie, nome, idade, sexo);
        AnimalDAO.getInstance().update(animal);
    }
    
    public static void alteraEspecie(int id, String nome) {
        Especie especie = new Especie(id, nome);
        EspecieDAO.getInstance().update(especie);
    }
    
    public static void alteraTratamento(int id, String data_inicio, String data_final, String finalizado) {
        int id_animal = TratamentoDAO.getInstance().retrieveById(id).getId_animal();
        Tratamento tratamento = new Tratamento(id, id_animal, data_inicio, data_final, finalizado);
        TratamentoDAO.getInstance().update(tratamento);
    }
    
    public static void alteraConsulta(int id, String sintomas, String data_conclusao) {
        int id_animal = ConsultaDAO.getInstance().retrieveById(id).getId_animal();
        int id_tratamento = ConsultaDAO.getInstance().retrieveById(id).getId_tratamento();
        int id_veterinario = ConsultaDAO.getInstance().retrieveById(id).getId_veterinario();
        Consulta consulta = new Consulta(id, id_tratamento, id_veterinario, id_animal, sintomas, data_conclusao);
        ConsultaDAO.getInstance().update(consulta);
    }
    
    public static void alteraExame(int id, String descricao, String resultado) {
        int id_consulta = ExameDAO.getInstance().retrieveById(id).getId_consulta();
        Exame exame = new Exame(id, id_consulta, descricao, resultado);
        ExameDAO.getInstance().update(exame);
    }
    
}
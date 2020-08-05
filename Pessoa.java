/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PC
 */
public class Pessoa {
    private int id ;
    private String  nome , endereco , bairro , cidade , regiao , numero;
    private ArrayList<Animais> listaAnimais = new ArrayList();
    private ArrayList<Perguntas> listaPerguntas = new ArrayList();

    public Pessoa() {
    }

    public Pessoa(String numero, String nome, String endereco, String bairro, String cidade, String regiao) {
        this.numero = numero;
        this.nome = nome;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.regiao = regiao;
    }
    public Pessoa(int id ,String numero, String nome, String endereco, String bairro, String cidade, String regiao,ArrayList<Animais> listaAnimais,ArrayList<Perguntas> listaPerguntas) {
        this.numero = numero;
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.regiao = regiao;
        this.listaAnimais=listaAnimais;
        this.listaPerguntas=listaPerguntas;
    }


    public int somaAnimais(Date data){
        int soma = 0;
        for(Animais a:listaAnimais){ //for(int i=0;i<listaAnimais.size();i++)
            if (a.getData().getTime()==data.getTime())  //a -> listaAnimais.get(i)
                soma += a.getQuantidade(); //soma = soma + listaAnimais.get(i).getQuantidade();
        }
        return soma;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getData() {
        return regiao;
    }

    public void setData(String data) {
        this.regiao = regiao;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public ArrayList<Animais> getListaAnimais() {
        return listaAnimais;
    }

    public void setListaAnimais(ArrayList<Animais> listaAnimais) {
        this.listaAnimais = listaAnimais;
    }

    public ArrayList<Perguntas> getListaPerguntas() {
        return listaPerguntas;
    }

   
    public void setListaPerguntas(ArrayList<Perguntas> listaPerguntas) {
        this.listaPerguntas = listaPerguntas;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", numero=" + numero + ", nome=" + nome + ", endereco=" + endereco + ", bairro=" + bairro + ", cidade=" + cidade + ", regiao=" + regiao + ", listaPerguntas=" + listaPerguntas + ", listaAnimais=" + listaAnimais +  '}'+"\r\n";
    }
    
    }

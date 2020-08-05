/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.Date;

/**
 *
 * @author aluno
 */
public class Perguntas {
    
    private boolean cisterna , cisternaconsumo , cxdagua , tampada , pcartesiano , pococonsumo , fseptica , animais;
    private int capacidade;
    private Date data;

    public Perguntas() {
    }

    public Perguntas(boolean cisterna, boolean cisternaconsumo, boolean cxdagua, boolean tampada, boolean pcartesiano, boolean pococonsumo, boolean fseptica, boolean animais, int capacidade ,Date data) {
        this.cisterna = cisterna;
        this.cisternaconsumo = cisternaconsumo;
        this.cxdagua = cxdagua;
        this.tampada = tampada;
        this.pcartesiano = pcartesiano;
        this.pococonsumo = pococonsumo;
        this.fseptica = fseptica;
        this.animais = animais;
        this.capacidade = capacidade;
        this.data=data;
    }

    public boolean isCisterna() {
        return cisterna;
    }

    public void setCisterna(boolean cisterna) {
        this.cisterna = cisterna;
    }

    public boolean isCisternaconsumo() {
        return cisternaconsumo;
    }

    public void setCisternaconsumo(boolean cisternaconsumo) {
        this.cisternaconsumo = cisternaconsumo;
    }

    public boolean isCxdagua() {
        return cxdagua;
    }

    public void setCxdagua(boolean cxdagua) {
        this.cxdagua = cxdagua;
    }

    public boolean isTampada() {
        return tampada;
    }

    public void setTampada(boolean tampada) {
        this.tampada = tampada;
    }

    public boolean isPcartesiano() {
        return pcartesiano;
    }

    public void setPcartesiano(boolean pcartesiano) {
        this.pcartesiano = pcartesiano;
    }

    public boolean isPococonsumo() {
        return pococonsumo;
    }

    public void setPococonsumo(boolean pococonsumo) {
        this.pococonsumo = pococonsumo;
    }

    public boolean isFseptica() {
        return fseptica;
    }

    public void setFseptica(boolean fseptica) {
        this.fseptica = fseptica;
    }

    public boolean isAnimais() {
        return animais;
    }

    public void setAnimais(boolean animais) {
        this.animais = animais;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Perguntas{" + "cisterna=" + cisterna + ", cisternaconsumo=" + cisternaconsumo + ", cxdagua=" + cxdagua + ", tampada=" + tampada + ", pcartesiano=" + pcartesiano + ", pococonsumo=" + pococonsumo + ", fseptica=" + fseptica + ", animais=" + animais + ", capacidade=" + capacidade + ", data=" + data + '}';
    }


    
    
    
}

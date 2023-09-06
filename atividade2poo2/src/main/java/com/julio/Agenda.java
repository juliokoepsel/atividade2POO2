package com.julio;

import java.util.ArrayList;

public class Agenda {
    
    private ArrayList<Contato> contatos;
    private AgendaPersistencia persistencia;

    public Agenda(AgendaPersistencia persistencia) {
        contatos = new ArrayList<Contato>();
        this.persistencia = persistencia;
    }

    public ArrayList<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(ArrayList<Contato> contatos) {
        this.contatos = contatos;
    }

    public AgendaPersistencia getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(AgendaPersistencia persistencia) {
        this.persistencia = persistencia;
    }

    @Override
    public String toString() {
        String aux = "Agenda [contatos=";
        for (Contato c : contatos) {
            aux = aux + c.toString();
        }
        return aux + "]";
    }

    public void salvar() {
        try {
            persistencia.salvar(contatos);
            System.out.println("Salvo!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void carregar() {
        try {
            contatos = persistencia.carregar();
            System.out.println("Carregado!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

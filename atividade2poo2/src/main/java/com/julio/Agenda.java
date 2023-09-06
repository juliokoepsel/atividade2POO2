package com.julio;

import java.util.ArrayList;

public class Agenda {
    
    private ArrayList<Contato> contatos;

    public Agenda() {
        contatos = new ArrayList<Contato>();
    }

    public ArrayList<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(ArrayList<Contato> contatos) {
        this.contatos = contatos;
    }

    @Override
    public String toString() {
        String aux = "Agenda [contatos=";
        for (Contato c : contatos) {
            aux = aux + c.toString();
        }
        return aux + "]";
    }

    public void salvar(AgendaPersistencia persistencia) {
        try {
            persistencia.salvar(contatos);
            System.out.println("Salvo!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void carregar(AgendaPersistencia persistencia) {
        try {
            contatos = persistencia.carregar();
            System.out.println("Carregado!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

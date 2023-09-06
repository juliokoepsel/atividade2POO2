package com.julio;

import java.time.LocalDate;

public class Sistema {
    public static void main(String[] args) {
        
        Agenda a = new Agenda(new AgendaPersistenciaCSV());
        Contato c;

        c = new Contato("Fulano", LocalDate.of(2001, 12, 15), "(00)111-222-333", "fulano@email.com");
        a.getContatos().add(c);

        c = new Contato("Beltrano", LocalDate.of(1993, 8, 6), "(00)222-333-111", "beltrano@email.com");
        a.getContatos().add(c);

        c = new Contato("Sicrano", LocalDate.of(1964, 7, 24), "(00)333-111-222", "sicrano@email.com");
        a.getContatos().add(c);

        System.out.println(a.toString());

        a.salvar();
        a.getContatos().clear();
        a.carregar();

        a.setPersistencia(new AgendaPersistenciaXML());
        a.salvar();
        a.getContatos().clear();
        a.carregar();

        a.setPersistencia(new AgendaPersistenciaJSON());
        a.salvar();
        a.getContatos().clear();
        a.carregar();

        a.setPersistencia(new AgendaPersistenciaMySQL());
        a.salvar();
        a.getContatos().clear();
        a.carregar();

        a.setPersistencia(new AgendaPersistenciaPostgreSQL());
        a.salvar();
        a.getContatos().clear();
        a.carregar();

        //a.setPersistencia(new AgendaPersistenciaMongoDB());
        //a.salvar();
        //a.getContatos().clear();
        //a.carregar();

        System.out.println(a.toString());

    }
}
package com.julio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AgendaPersistenciaJSON implements AgendaPersistencia {

    @Override
    public void salvar(ArrayList<Contato> contatos) throws IOException {

        File jsonFile = new File("agenda.json");

        try (FileWriter fileWriter = new FileWriter(jsonFile)) {

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("[").append(System.lineSeparator());
        
            for (int i = 0; i < contatos.size(); i++) {

                Contato contato = contatos.get(i);

                stringBuilder.append("  {").append(System.lineSeparator());
                stringBuilder.append("    \"nome\":\"").append(contato.getNome()).append("\",").append(System.lineSeparator());
                stringBuilder.append("    \"dataNascimento\":\"").append(contato.getDataNascimento()).append("\",").append(System.lineSeparator());
                stringBuilder.append("    \"telefone\":\"").append(contato.getTelefone()).append("\",").append(System.lineSeparator());
                stringBuilder.append("    \"email\":\"").append(contato.getEmail()).append("\"").append(System.lineSeparator());
                stringBuilder.append("  }");
                
                if (i < contatos.size() - 1) {
                    stringBuilder.append(",");
                }

                stringBuilder.append(System.lineSeparator());
            }
            
            stringBuilder.append("]").append(System.lineSeparator());

            fileWriter.write(stringBuilder.toString());
        }
    }

    @Override
    public ArrayList<Contato> carregar() throws IOException {

        File jsonFile = new File("agenda.json");

        ArrayList<Contato> contatos = new ArrayList<Contato>();

        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {

            StringBuilder stringBuilder = new StringBuilder();
            String linha;

            while ((linha = reader.readLine()) != null) {
                stringBuilder.append(linha);
            }

            String jsonString = stringBuilder.toString();
            jsonString = jsonString.substring(1, jsonString.length() - 1).replace(System.lineSeparator(), "");

            String[] contatoStrings = jsonString.split("},");
            
            for (String contatoString : contatoStrings) {
                String[] atributos = contatoString.split(",");
                
                String nome = atributos[0].split(":")[1].replace("\"", "");

                String dataNascimento = atributos[1].split(":")[1].replace("\"", "");
                String[] numeros = dataNascimento.split("-");

                String telefone = atributos[2].split(":")[1].replace("\"", "");

                String email = atributos[3].split(":")[1].replace("\"", "").replace("  ", "").replace("}", "");

                contatos.add(new Contato(nome, LocalDate.of(Integer.parseInt(numeros[0]), Integer.parseInt(numeros[1]), Integer.parseInt(numeros[2])), telefone, email));
            }

        }

        return contatos;
    }
    
}

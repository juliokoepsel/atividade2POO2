package agenda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class AgendaPersistenciaCSV implements AgendaPersistencia {

    @Override
    public void salvar(ArrayList<Contato> contatos ) throws IOException {

        File csvFile = new File("agenda.csv");

        try(FileWriter fileWriter = new FileWriter(csvFile)) {

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("nome")
                    .append(',')
                    .append("dataNascimento")
                    .append(',')
                    .append("telefone")
                    .append(',')
                    .append("email")
                    .append(System.lineSeparator());

            for (final var contato : contatos)
                stringBuilder.append(contato.getNome())
                        .append(',')
                        .append(contato.getDataNascimento())
                        .append(',')
                        .append(contato.getTelefone())
                        .append(',')
                        .append(contato.getEmail())
                        .append(System.lineSeparator());

            fileWriter.write(stringBuilder.toString());
        }
    }

    @Override
    public ArrayList<Contato> carregar() throws IOException {

        Path csvFile = Paths.get("agenda.csv");

        ArrayList<Contato> contatos = new ArrayList<Contato>();
        Contato contato;

        try (BufferedReader bufferedReader = Files.newBufferedReader(csvFile)) {

            String linha = bufferedReader.readLine();
            linha = bufferedReader.readLine();

            while (linha != null) {
                String[] atributos = linha.split(",");
                String[] numeros = atributos[1].split("-");

                contato = new Contato(atributos[0], LocalDate.of(Integer.parseInt(numeros[0]), Integer.parseInt(numeros[1]), Integer.parseInt(numeros[2])), atributos[2], atributos[3]);
                contatos.add(contato);

                linha = bufferedReader.readLine();
            }

        }

        return contatos;
    }
    
}

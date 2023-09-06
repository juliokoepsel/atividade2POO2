package com.julio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AgendaPersistenciaPostgreSQL implements AgendaPersistencia {

    @Override
    public void salvar(ArrayList<Contato> contatos) throws SQLException, ClassNotFoundException {
        
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/agenda";
        String usuario = "leviathan";
        String senha = "";
        
        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String insertQuery = "INSERT INTO contato (nome, nascimento, telefone, email) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            for (Contato contato : contatos) {
                preparedStatement.setString(1, contato.getNome());
                preparedStatement.setDate(2, Date.valueOf(contato.getDataNascimento()));
                preparedStatement.setString(3, contato.getTelefone());
                preparedStatement.setString(4, contato.getEmail());

                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public ArrayList<Contato> carregar() throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/agenda";
        String usuario = "leviathan";
        String senha = "";

        ArrayList<Contato> contatos = new ArrayList<Contato>();

        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String selectQuery = "SELECT * FROM contato";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String[] numeros = resultSet.getString("nascimento").split("-");
                contatos.add(new Contato(resultSet.getString("nome"), LocalDate.of(Integer.parseInt(numeros[0]), Integer.parseInt(numeros[1]), Integer.parseInt(numeros[2])), resultSet.getString("telefone"), resultSet.getString("email")));
            }
        }

        return contatos;
    }
    
}

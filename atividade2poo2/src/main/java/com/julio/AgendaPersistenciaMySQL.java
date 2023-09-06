package com.julio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AgendaPersistenciaMySQL implements AgendaPersistencia {

    @Override
    public void salvar(ArrayList<Contato> contatos) throws SQLException, ClassNotFoundException {

        Class.forName("org.mariadb.jdbc.Driver");
        String url = "jdbc:mariadb://localhost:3306/agenda";
        String usuario = "julio";
        String senha = "julio";

        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String insertQuery = "INSERT INTO contato (id, nome, nascimento, telefone, email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            for (Contato contato : contatos) {
                preparedStatement.setString(1, "0");
                preparedStatement.setString(2, contato.getNome());
                preparedStatement.setString(3, String.valueOf(contato.getDataNascimento()));
                preparedStatement.setString(4, contato.getTelefone());
                preparedStatement.setString(5, contato.getEmail());

                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public ArrayList<Contato> carregar() throws SQLException, ClassNotFoundException {

        Class.forName("org.mariadb.jdbc.Driver");
        String url = "jdbc:mariadb://localhost:3306/agenda";
        String usuario = "julio";
        String senha = "julio";

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

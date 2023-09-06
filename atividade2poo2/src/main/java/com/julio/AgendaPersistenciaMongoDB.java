package com.julio;

import java.time.LocalDate;
import java.util.ArrayList;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class AgendaPersistenciaMongoDB implements AgendaPersistencia {

    @Override
    public void salvar(ArrayList<Contato> contatos) {
        
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("agenda");
        MongoCollection<Document> collection = database.getCollection("contato");

        for (Contato contato : contatos) {
            Document document = new Document("nome", contato.getNome())
                    .append("nascimento", String.valueOf(contato.getDataNascimento()))
                    .append("telefone", contato.getTelefone())
                    .append("email", contato.getEmail());

            collection.insertOne(document);
        }

        mongoClient.close();
    }

    @Override
    public ArrayList<Contato> carregar() {
        
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("agenda");
        MongoCollection<Document> collection = database.getCollection("contato");

        MongoCursor<Document> cursor = collection.find().iterator();

        ArrayList<Contato> contatos = new ArrayList<>();

        while (cursor.hasNext()) {
            Document document = cursor.next();
            String[] numeros = document.getString("nascimento").split("-");

            Contato contato = new Contato(document.getString("nome"), LocalDate.of(Integer.parseInt(numeros[0]), Integer.parseInt(numeros[1]), Integer.parseInt(numeros[2])), document.getString("telefone"), document.getString("email"));

            contatos.add(contato);
        }

        cursor.close();
        mongoClient.close();

        return contatos;
    }
    
}

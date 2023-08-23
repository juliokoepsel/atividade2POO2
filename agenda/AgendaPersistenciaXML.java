package agenda;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AgendaPersistenciaXML implements AgendaPersistencia {

    @Override
    public void salvar(ArrayList<Contato> contatos) throws Exception {

        File xmlFile = new File("agenda.xml");

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element rootElement = document.createElement("agenda");
        document.appendChild(rootElement);

        for (Contato contato : contatos) {
            Element contatoElement = document.createElement("contato");
            rootElement.appendChild(contatoElement);

            Element nomeElement = document.createElement("nome");
            nomeElement.appendChild(document.createTextNode(contato.getNome()));
            contatoElement.appendChild(nomeElement);

            Element dataNascimentoElement = document.createElement("dataNascimento");
            dataNascimentoElement.appendChild(document.createTextNode(String.valueOf(contato.getDataNascimento())));
            contatoElement.appendChild(dataNascimentoElement);

            Element telefoneElement = document.createElement("telefone");
            telefoneElement.appendChild(document.createTextNode(contato.getTelefone()));
            contatoElement.appendChild(telefoneElement);

            Element emailElement = document.createElement("email");
            emailElement.appendChild(document.createTextNode(contato.getEmail()));
            contatoElement.appendChild(emailElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(xmlFile);

        transformer.transform(source, result);
    }

    @Override
    public ArrayList<Contato> carregar() throws Exception {

        File xmlFile = new File("agenda.xml");

        ArrayList<Contato> contatos = new ArrayList<Contato>();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();

        NodeList contatosNodes = document.getElementsByTagName("contato");

        for (int i = 0; i < contatosNodes.getLength(); i++) {

            Node contatoNode = contatosNodes.item(i);

            if (contatoNode.getNodeType() == Node.ELEMENT_NODE) {

                Element contatoElement = (Element) contatoNode;

                String nome = contatoElement.getElementsByTagName("nome").item(0).getTextContent();
                
                String dataNascimento = contatoElement.getElementsByTagName("dataNascimento").item(0).getTextContent();
                String[] numeros = dataNascimento.split("-");

                String telefone = contatoElement.getElementsByTagName("telefone").item(0).getTextContent();

                String email = contatoElement.getElementsByTagName("email").item(0).getTextContent();

                contatos.add(new Contato(nome, LocalDate.of(Integer.parseInt(numeros[0]), Integer.parseInt(numeros[1]), Integer.parseInt(numeros[2])), telefone, email));
            }
        }

        return contatos;
    }
    
}

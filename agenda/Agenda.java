package agenda;

import java.util.ArrayList;

public class Agenda {
    
    private ArrayList<Contato> contatos;

    public Agenda() {
        this.contatos = new ArrayList<Contato>();
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
        persistencia.salvar();
    }

    public void carregar(AgendaPersistencia persistencia) {
        persistencia.carregar();
    }

}

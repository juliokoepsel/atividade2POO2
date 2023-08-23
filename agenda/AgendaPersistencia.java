package agenda;

import java.io.IOException;
import java.util.ArrayList;

public interface AgendaPersistencia {
    
    public void salvar(ArrayList<Contato> contatos) throws IOException;

    public ArrayList<Contato> carregar() throws IOException;

}

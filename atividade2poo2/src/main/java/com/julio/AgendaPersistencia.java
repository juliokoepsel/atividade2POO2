package com.julio;

import java.util.ArrayList;

public interface AgendaPersistencia {
    
    public void salvar(ArrayList<Contato> contatos) throws Exception;

    public ArrayList<Contato> carregar() throws Exception;

}

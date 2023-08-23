package agenda;

import java.time.LocalDate;

public class Contato {
    
    private String nome;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;

    public Contato(String nome, LocalDate dataNascimento, String telefone, String email) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contato [nome=" + nome + ", dataNascimento=" + dataNascimento + ", telefone=" + telefone + ", email="
                + email + "]";
    }

}

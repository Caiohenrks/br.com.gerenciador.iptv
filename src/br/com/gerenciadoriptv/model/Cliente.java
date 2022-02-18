
package br.com.gerenciadoriptv.model;


public class Cliente {
    public int id;
    public String nome;
    public String usuario;
    public String senha;
    public String telefone;
    public String email;
    public String inicio;
    public String vencimento;

    public Cliente() {
    }

    public Cliente(String nome, String usuario, String senha, String telefone, String email, String inicio, String vencimento) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.telefone = telefone;
        this.email = email;
        this.inicio = inicio;
        this.vencimento = vencimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nome=" + nome + ", usuario=" + usuario + ", senha=" + senha + ", telefone=" + telefone + ", email=" + email + ", inicio=" + inicio + ", vencimento=" + vencimento + '}';
    }
    
    

}
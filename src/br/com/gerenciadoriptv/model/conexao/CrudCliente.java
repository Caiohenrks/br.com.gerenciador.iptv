package br.com.gerenciadoriptv.model.conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import br.com.gerenciadoriptv.model.Cliente;

public class CrudCliente {

    public void createCliente(Cliente c) {

        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO cliente (nome, usuario, senha, telefone, email, inicio, vencimento) VALUES(?,?,?,?,?,?,?)");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getUsuario());
            stmt.setString(3, c.getSenha());
            stmt.setString(4, c.getTelefone());
            stmt.setString(5, c.getEmail());
            stmt.setString(6, c.getInicio());
            stmt.setString(7, c.getVencimento());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(CrudCliente.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar" + ex);
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }
    }

    public List<Cliente> readCliente() {

        Connection con = ConnectionDB.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente c = new Cliente();

                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setUsuario(rs.getString("usuario"));
                c.setSenha(rs.getString("senha"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setInicio(rs.getString("inicio"));
                c.setVencimento(rs.getString("vencimento"));
                clientes.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CrudCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }

        return clientes;
    }

    public List<Cliente> buscarCliente(String nome) {

        Connection con = ConnectionDB.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE nome LIKE ?");
            stmt.setString(1, "%" + nome + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente c = new Cliente();

                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setUsuario(rs.getString("usuario"));
                c.setSenha(rs.getString("senha"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setInicio(rs.getString("inicio"));
                c.setVencimento(rs.getString("vencimento"));
                clientes.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CrudCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }

        return clientes;
    }

    public Cliente buscarId(int id) {

        Connection con = ConnectionDB.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        Cliente c = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE id LIKE ?");
            stmt.setString(1, "%" + id + "%");
            c = new Cliente();
            rs = stmt.executeQuery();

            while (rs.next()) {

                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setUsuario(rs.getString("usuario"));
                c.setSenha(rs.getString("senha"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setInicio(rs.getString("inicio"));
                c.setVencimento(rs.getString("vencimento"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CrudCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }

        return c;
    }

    public void atualizarCliente(Cliente c) {

        Connection con = ConnectionDB.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE cliente SET nome = ? ,usuario = ? ,senha = ? ,telefone = ? ,email = ? ,inicio = ? ,vencimento = ? WHERE id = ?");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getUsuario());
            stmt.setString(3, c.getSenha());
            stmt.setString(4, c.getTelefone());
            stmt.setString(5, c.getEmail());
            stmt.setString(6, c.getInicio());
            stmt.setString(7, c.getVencimento());
            stmt.setInt(8, c.getId());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }

    }

    public void RenovarCliente(Cliente c) {

        Connection con = ConnectionDB.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE cliente SET vencimento = ? WHERE id = ?");
            stmt.setString(1, c.getVencimento());
            stmt.setInt(2, c.getId());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }

    }

    public void deleteCliente(Cliente p) {

        Connection con = ConnectionDB.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM cliente WHERE id = ?");
            stmt.setInt(1, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }
    }

}

package model;

import model.conectaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.produtosDTO;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
   
    public void cadastrarProduto(ProdutosDTO produto) {

            Connection conn = null;
        PreparedStatement prep = null;
        try {
            conn = new conectaDAO().connectDB();
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            int linhas = prep.executeUpdate();
            if (linhas > 0) {
                System.out.println("Produto cadastrado com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar o produto.");
            }
        } catch (SQLException ex) {
            System.err.println("Erro em cadastrarProduto: " + ex.getMessage());
        } finally {
            try { if (prep != null) prep.close(); } catch (SQLException e) { }
            try { if (conn != null) conn.close(); } catch (SQLException e) { }
        }
        
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;
        try {
            conn = new conectaDAO().connectDB();
            String sql = "SELECT id, nome, valor, status FROM produtos";
            prep = conn.prepareStatement(sql);
            rs = prep.executeQuery();
            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException ex) {
            System.err.println("Erro em listarProdutos: " + ex.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { }
            try { if (prep != null) prep.close(); } catch (SQLException e) { }
            try { if (conn != null) conn.close(); } catch (SQLException e) { }
        }
        return listagem;
    }
    // Método para vender um produto (atualiza o status para 'vendido')
    public void venderProduto(int id) {
        Connection conn = null;
        PreparedStatement prep = null;
        try {
            conn = new conectaDAO().connectDB();
            String sql = "UPDATE produtos SET status = 'vendido' WHERE id = ?";
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            int linhas = prep.executeUpdate();
            if (linhas > 0) {
                System.out.println("Produto vendido com sucesso!");
            } else {
                System.out.println("Produto não encontrado para venda.");
            }
        } catch (SQLException ex) {
            System.err.println("Erro em venderProduto: " + ex.getMessage());
        } finally {
            try { if (prep != null) prep.close(); } catch (SQLException e) { }
            try { if (conn != null) conn.close(); } catch (SQLException e) { }
        }
    }
     // Método para listar somente os produtos com status 'vendido'
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;
        try {
            conn = new conectaDAO().connectDB();
            String sql = "SELECT id, nome, valor, status FROM produtos WHERE status = 'vendido'";
            prep = conn.prepareStatement(sql);
            rs = prep.executeQuery();
            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException ex) {
            System.err.println("Erro em listarProdutosVendidos: " + ex.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { }
            try { if (prep != null) prep.close(); } catch (SQLException e) { }
            try { if (conn != null) conn.close(); } catch (SQLException e) { }
        }
        return listagem;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Livro;

/**
 *
 * @author PIU2
 */
public class LivroDAO {
    
    public void create(Livro livro){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO livro (precounitario, nome, autor, editora) VALUES (?,?,?,?)");
            stmt.setDouble(1, livro.getPrecounitario());
            stmt.setString(2, livro.getNome());
            stmt.setString(3, livro.getAutor());
            stmt.setString(4, livro.getEditora());
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
    }
    
    public ArrayList<Livro> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Livro> listaLivros = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM livro ORDER by idLivro");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Livro livro = new Livro();
                livro.setIdLivro(rs.getInt("idLivro"));
                livro.setNome(rs.getString("nome"));
                livro.setPrecounitario(rs.getDouble("precounitario"));
                livro.setAutor(rs.getString("autor"));
                livro.setEditora(rs.getString("editora"));
                livro.setInicio(rs.getString("inicio"));
                livro.setFim(rs.getString("fim"));
                listaLivros.add(livro);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler os livros", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return listaLivros;
    }
    
    public void update(Livro livro){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE livro set nome = ?, precounitario = ?, autor = ?, editora = ? "
                    +" WHERE idlivro = ?");
            stmt.setString(1, livro.getNome());
            stmt.setDouble(2, livro.getPrecounitario());
            stmt.setString(3, livro.getAutor());
            stmt.setString(4, livro.getEditora());
            stmt.setInt(5, livro.getIdLivro());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+ex);
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }
    }
    
    public void delete(Livro livro){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE FROM livro WHERE idlivro = ?");
            stmt.setInt(1, livro.getIdLivro());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: "+ex);
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }
    }
    
}

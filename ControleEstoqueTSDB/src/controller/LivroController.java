/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.bean.Livro;
import model.dao.LivroDAO;

/**
 *
 * @author PIU2
 */
public class LivroController {
    
    public void create(String nome, double precounitario, String autor, String editora){
        
        Livro livro = new Livro();
        livro.setNome(nome);
        livro.setPrecounitario(precounitario);
        livro.setAutor(autor);
        livro.setEditora(editora);
        
        LivroDAO livroDAO = new LivroDAO();
        livroDAO.create(livro);
        
    }
    
    public ArrayList<Livro> read(){
        LivroDAO livroDAO = new LivroDAO();
        return livroDAO.read();
    }
    
    public void update(int idlivro, String nome, double precounitario, String autor, String editora){
        Livro livro = new Livro();
        livro.setIdLivro(idlivro);
        livro.setNome(nome);
        livro.setPrecounitario(precounitario);
        livro.setAutor(autor);
        livro.setEditora(editora);
        //livro.setInicio(inicio);
        //livro.setFim(fim);
        
        LivroDAO livroDAO = new LivroDAO();
        livroDAO.update(livro);
    }
    
    public void delete (int idlivro){
        Livro livro = new Livro();
        livro.setIdLivro(idlivro);
        
        LivroDAO livroDAO = new LivroDAO();
        livroDAO.delete(livro);
    }
    
}

package br.mackenzie.fci.ec.lp2.action;

import br.mackenzie.fci.ec.lp2.dao.AutorDAO;
import br.mackenzie.fci.ec.lp2.dao.LivroDAO;
import br.mackenzie.fci.ec.lp2.exception.PersistenciaException;
import br.mackenzie.fci.ec.lp2.model.Autor;
import br.mackenzie.fci.ec.lp2.model.Livro;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LivroAction extends ActionSupport
{
    public String listar()
    {
        try
        {
            this.getRequest().setAttribute("livros", new LivroDAO().listar());
        } catch (PersistenciaException ex)
        {
            Logger.getLogger(LivroAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "WEB-INF/jsp/livro/listar.jsp";
    }

    public String novo()
    {
        return "WEB-INF/jsp/livro/inserir.jsp";
    }
    
    public String inserir() throws PersistenciaException
    {
        Livro livro = new Livro();
        
        String nome = this.getRequest().getParameter("nome");
        
        int IDAutor = Integer.parseInt(this.getRequest().getParameter("ID"));
        
        AutorDAO autorDAO = new AutorDAO();
        
        Autor autor = autorDAO.listarPorID(new Autor(IDAutor, ""));

        livro.setNome(nome);
        livro.setIDAutor(autor);
        
        try
        {
            new LivroDAO().inserir(livro);
        } catch (PersistenciaException ex)
        {
            Logger.getLogger(LivroAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.listar();
    }
   
    public String alterar()
    {
        return "WEB-INF/jsp/livro/alterar.jsp";
    }

    public String confirmarAlteracao() throws PersistenciaException
    {
        try
        {
            Livro livro = new Livro();
            
            livro.setID(Integer.parseInt(this.getRequest().getParameter("ID")));
            livro.setNome(this.getRequest().getParameter("nome"));
            
            LivroDAO livroDAO = new LivroDAO();
            
            livroDAO.alterar(livro);
        } catch (PersistenciaException ex)
        {
            Logger.getLogger(LivroAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.listar();
    }
    
    public String remover()
    {
        return "WEB-INF/jsp/livro/remover.jsp";
    }

    public String confirmarRemocao() throws PersistenciaException
    {
        try
        {
            Livro livro = new Livro();
            
            livro.setID(Integer.parseInt(this.getRequest().getParameter("ID")));
            
            LivroDAO livroDAO = new LivroDAO();
            
            livroDAO.remover(livro);
        } catch (PersistenciaException ex)
        {
            Logger.getLogger(LivroAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.listar();
    }
}
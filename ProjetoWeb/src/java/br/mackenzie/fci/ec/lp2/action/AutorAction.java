package br.mackenzie.fci.ec.lp2.action;

import br.mackenzie.fci.ec.lp2.dao.AutorDAO;
import br.mackenzie.fci.ec.lp2.dao.LivroDAO;
import br.mackenzie.fci.ec.lp2.exception.PersistenciaException;
import br.mackenzie.fci.ec.lp2.model.Autor;
import br.mackenzie.fci.ec.lp2.model.Livro;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutorAction extends ActionSupport
{
    public String listar()
    {
        try
        {
            this.getRequest().setAttribute("autores", new AutorDAO().listar());
        } catch (PersistenciaException ex)
        {
            Logger.getLogger(AutorAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "WEB-INF/jsp/autor/listar.jsp";
    }

    public String novo()
    {
        return "WEB-INF/jsp/autor/inserir.jsp";
    }
    
    public String inserir()
    {
        Autor autor = new Autor();
        
        String nome = this.getRequest().getParameter("nome");
        
        autor.setNome(nome);
        
        try
        {
            new AutorDAO().inserir(autor);
        } catch (PersistenciaException ex)
        {
            Logger.getLogger(AutorAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.listar();
    }
    
    public String alterar()
    {
        return "WEB-INF/jsp/autor/alterar.jsp";
    }

    public String confirmarAlteracao()
    {
        try
        {
            Autor autor = new Autor();
            
            autor.setID(Integer.parseInt(this.getRequest().getParameter("ID")));
            autor.setNome(this.getRequest().getParameter("nome"));
            
            AutorDAO autorDAO = new AutorDAO();
            
            autorDAO.alterar(autor);
        } catch (PersistenciaException ex)
        {
            Logger.getLogger(AutorAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.listar();
    }
    
    public String remover()
    {
        return "WEB-INF/jsp/autor/remover.jsp";
    }

    public String confirmarRemocao() throws PersistenciaException
    {
        try
        {
            Autor autor = new Autor();
            
            autor.setID(Integer.parseInt(this.getRequest().getParameter("ID")));

            AutorDAO autorDAO = new AutorDAO();
            
            autorDAO.remover(autor);
        } catch (PersistenciaException ex)
        {
            Logger.getLogger(AutorAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.listar();
    }
}
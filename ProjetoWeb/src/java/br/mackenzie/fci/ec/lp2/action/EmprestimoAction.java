package br.mackenzie.fci.ec.lp2.action;

import br.mackenzie.fci.ec.lp2.dao.AutorDAO;
import br.mackenzie.fci.ec.lp2.dao.EmprestimoDAO;
import br.mackenzie.fci.ec.lp2.dao.LivroDAO;
import br.mackenzie.fci.ec.lp2.dao.TipoUsuarioDAO;
import br.mackenzie.fci.ec.lp2.dao.UsuarioDAO;
import br.mackenzie.fci.ec.lp2.exception.PersistenciaException;
import br.mackenzie.fci.ec.lp2.model.Autor;
import br.mackenzie.fci.ec.lp2.model.Emprestimo;
import br.mackenzie.fci.ec.lp2.model.Livro;
import br.mackenzie.fci.ec.lp2.model.TipoUsuario;
import br.mackenzie.fci.ec.lp2.model.Usuario;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmprestimoAction extends ActionSupport
{

    public String listar()
    {
        try
        {
            this.getRequest().setAttribute("emprestimos", new EmprestimoDAO().listar());
        } catch (PersistenciaException ex)
        {
            Logger.getLogger(EmprestimoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "WEB-INF/jsp/emprestimo/listar.jsp";
    }

    public String novo()
    {     
        return "WEB-INF/jsp/emprestimo/inserir.jsp";
    }

    public String inserir() throws ClassNotFoundException, SQLException
    {
        try
        {
//            //ID do Autor
//            
//            int IDAutor = Integer.parseInt(this.getRequest().getParameter("IDAutor"));
//
//            AutorDAO autorDAO = new AutorDAO();
//
//            Autor autor = autorDAO.listarPorID(new Autor(IDAutor, ""));        
//            
//            //ID do Livro
//            
//            int IDLivro = Integer.parseInt(this.getRequest().getParameter("IDLivro"));
//
//            LivroDAO livroDAO = new LivroDAO();
//
//            Livro livro = livroDAO.listarPorID(new Livro(IDLivro, autor, ""));       
//            
//            //ID do Tipo de Usuário
//            
//            int IDTipoUsuario = Integer.parseInt(this.getRequest().getParameter("IDTipoUsuario"));
//            
//            TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
//            
//            TipoUsuario tipoUsuario = tipoUsuarioDAO.listarPorID(new TipoUsuario(IDTipoUsuario, ""));
//      
//            //ID do Usuario
//            
//            int IDUsuario = Integer.parseInt(this.getRequest().getParameter("IDUsuario"));
//            
//            UsuarioDAO usuarioDAO = new UsuarioDAO();
//            
//            Usuario usuario = usuarioDAO.listarPorID(new Usuario(IDUsuario, "", "", "", tipoUsuario));
//            
//            //Data
//            
//            LocalDate data = LocalDate.parse(this.getRequest().getParameter("data"));
            
            Emprestimo emprestimo = new Emprestimo();
            
//            emprestimo.setIDUsuario(usuario);
//            emprestimo.setIDLivro(livro);
//            emprestimo.setData(data);

            LocalDate data = LocalDate.parse(this.getRequest().getParameter("data"));

            Usuario usuario = new Usuario();
            
            usuario.setID(2);
            
            Livro livro = new Livro();
            
            livro.setID(3);
            
            emprestimo.setIDUsuario(usuario);
            emprestimo.setIDLivro(livro);
            emprestimo.setData(data);

            new EmprestimoDAO().inserir(emprestimo);
        } catch (PersistenciaException ex)
        {
            Logger.getLogger(EmprestimoAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.listar();
    }

    public String alterar()
    {
        return "WEB-INF/jsp/emprestimo/alterar.jsp";
    }

    public String confirmarAlteracao() throws PersistenciaException
    {
        try
        {
            Emprestimo emprestimo = new Emprestimo();

            emprestimo.setID(Integer.parseInt(this.getRequest().getParameter("ID")));
            emprestimo.setData(LocalDate.parse(this.getRequest().getParameter("data")));

            EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

            emprestimoDAO.alterar(emprestimo);
        } catch (PersistenciaException ex)
        {
            Logger.getLogger(EmprestimoAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.listar();
    }

    public String remover()
    {
        return "WEB-INF/jsp/emprestimo/remover.jsp";
    }

    public String confirmarRemocao() throws PersistenciaException
    {

        Emprestimo emprestimo = new Emprestimo();

        emprestimo.setID(Integer.parseInt(this.getRequest().getParameter("ID")));

        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

        emprestimoDAO.remover(emprestimo);

        return this.listar();
    }
}

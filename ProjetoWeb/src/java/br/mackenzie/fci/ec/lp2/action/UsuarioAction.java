package br.mackenzie.fci.ec.lp2.action;

import br.mackenzie.fci.ec.lp2.dao.UsuarioDAO;
import br.mackenzie.fci.ec.lp2.exception.PersistenciaException;
import br.mackenzie.fci.ec.lp2.model.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioAction extends ActionSupport
{
    public String login()
    {
        String login = this.getRequest().getParameter("login");
        String senha = this.getRequest().getParameter("senha");

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario usuario = usuarioDAO.readByName(login);

        if (usuario == null)
        {
            return "erro.jsp";
        }
        else if (senha.equals(usuario.getSenha()))
        {
            //Página do Administrador

            if ("admin".equals(usuario.getLogin()))
            {
                return this.listar();
            }

            return "menu.jsp";
        }
        else
        {
            return "erro.jsp";
        }
    }

    public String listar()
    {
        try
        {
            this.getRequest().setAttribute("usuarios", new UsuarioDAO().listar());

        } catch (PersistenciaException ex)
        {
            Logger.getLogger(UsuarioAction.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return "WEB-INF/jsp/usuario/listar.jsp";
    }

    public String novo()
    {
        return "WEB-INF/jsp/usuario/inserir.jsp";
    }

    public String inserir()
    {
        String nome = this.getRequest().getParameter("nome");
        String login = this.getRequest().getParameter("login");
        String senha = this.getRequest().getParameter("senha");

        Usuario usuario = new Usuario();

        usuario.setNome(nome);
        usuario.setLogin(login);
        usuario.setSenha(senha);

        try
        {
            new UsuarioDAO().inserir(usuario);
        } catch (PersistenciaException ex)
        {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "index.jsp";
    }

    public String alterar()
    {
        return "WEB-INF/jsp/usuario/alterar.jsp";
    }

    public String confirmarAlteracao() throws PersistenciaException
    {
        try
        {
            Usuario usuario = new Usuario();

            usuario.setID(Integer.parseInt(this.getRequest().getParameter("ID")));
            usuario.setNome(this.getRequest().getParameter("nome"));

            UsuarioDAO usuarioDAO = new UsuarioDAO();

            usuarioDAO.alterar(usuario);
        } catch (PersistenciaException ex)
        {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.listar();
    }

    public String remover()
    {
        return "WEB-INF/jsp/usuario/remover.jsp";
    }

    public String confirmarRemocao() throws PersistenciaException
    {
        try
        {
            Usuario usuario = new Usuario();

            usuario.setID(Integer.parseInt(this.getRequest().getParameter("ID")));

            UsuarioDAO usuarioDAO = new UsuarioDAO();

            usuarioDAO.remover(usuario);

        } catch (PersistenciaException ex)
        {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.listar();
    }
}

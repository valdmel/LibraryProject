package br.mackenzie.fci.ec.lp2.dao;

import br.mackenzie.fci.ec.lp2.exception.PersistenciaException;
import br.mackenzie.fci.ec.lp2.model.TipoUsuario;
import br.mackenzie.fci.ec.lp2.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO implements GenericoDAO<Usuario>
{
    @Override
    public List<Usuario> listar() throws PersistenciaException
    {
        List<Usuario> usuarios = new ArrayList();

        String sql = "SELECT U.ID_USUARIO, U.NOME_USUARIO, U.LOGIN_USUARIO, U.SENHA_USUARIO, U_TIPO.NOME_TIPO_USUARIO, U_TIPO.ID_TIPO_USUARIO "
                + "FROM PROJETO.USUARIO U "
                + "INNER JOIN PROJETO.TIPO_USUARIO U_TIPO "
                + "ON U.ID_TIPO_USUARIO = U_TIPO.ID_TIPO_USUARIO";

        try
        {
            try (Connection connection = Conexao.getInstance().getConnection())
            {
                Statement pst = connection.createStatement();

                ResultSet rs = pst.executeQuery(sql);

                while (rs.next())
                {
                    TipoUsuario tipoUsuario = new TipoUsuario(rs.getInt("ID_TIPO_USUARIO"), rs.getString("NOME_TIPO_USUARIO"));

                    Usuario usuario = new Usuario(rs.getInt("ID_USUARIO"), rs.getString("NOME_USUARIO"), rs.getString("LOGIN_USUARIO"), rs.getString("SENHA_USUARIO"), tipoUsuario);

                    usuarios.add(usuario);
                }

                connection.close();
            }

        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuarios;
    }

    public Usuario listarPorID(Usuario usuario) throws ClassNotFoundException, SQLException
    {
        String sql = "SELECT U.ID_USUARIO, U.NOME_USUARIO, U.LOGIN_USUARIO, U.SENHA_USUARIO, U_TIPO.ID_TIPO_USUARIO "
                + "FROM PROJETO.USUARIO U "
                + "INNER JOIN PROJETO.TIPO_USUARIO U_TIPO "
                + "ON U_TIPO.ID_TIPO_USUARIO=U.ID_TIPO_USUARIO "
                + "WHERE U.ID_USUARIO = ?";

        try (Connection connection = Conexao.getInstance().getConnection())
        {
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, usuario.getID());

            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                TipoUsuario tipoUsuario = new TipoUsuario(rs.getInt("ID_TIPO_USUARIO"), rs.getString("NOME_TIPO_USUARIO"));

                usuario = new Usuario(rs.getInt("ID_USUARIO"), rs.getString("NOME_USUARIO"), rs.getString("LOGIN_USUARIO"), rs.getString("SENHA_USUARIO"), tipoUsuario);
            }
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuario;
    }

    @Override
    public void inserir(Usuario usuario) throws PersistenciaException
    {
        Connection connection = null;

        String sql = "INSERT "
                + "INTO PROJETO.USUARIO (NOME_USUARIO, LOGIN_USUARIO, SENHA_USUARIO, ID_TIPO_USUARIO) "
                + "VALUES (?, ?, ?, 1)";

        try
        {
            connection = Conexao.getInstance().getConnection();

            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getLogin());
            pst.setString(3, usuario.getSenha());

            pst.execute();
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível conectar ao servidor de dados!");
        } catch (SQLException ex)
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Ops, ocorreu um erro ao enviar o comando para o servidor de dados!");
        } finally
        {
            try
            {
                connection.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Ops, não foi possível fechar a conexão com a base de dados!");
            }
        }
    }

    @Override
    public void alterar(Usuario usuario) throws PersistenciaException
    {
        try
        {
            String sql = "UPDATE PROJETO.USUARIO "
                    + "SET NOME_USUARIO = ? "
                    + "WHERE ID_USUARIO = ?";

            try (Connection connection = Conexao.getInstance().getConnection())
            {
                PreparedStatement pst = connection.prepareStatement(sql);

                pst.setString(1, usuario.getNome());
                pst.setInt(2, usuario.getID());

                pst.execute();
            }
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possivel enviar o comando, ou se conectar com a base de dados!");
        }
    }

    @Override
    public void remover(Usuario usuario) throws PersistenciaException
    {
        String sql = "DELETE "
                + "FROM PROJETO.USUARIO "
                + "WHERE ID_USUARIO = ?";

        try (Connection connection = Conexao.getInstance().getConnection())
        {
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, usuario.getID());

            pst.execute();
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possivel enviar o comando, ou se conectar com a base de dados!");
        }
    }

    public Usuario readByName(String nomeUsuario)
    {
        Usuario usuario = null;

        String sql = "SELECT * "
                + "FROM PROJETO.USUARIO "
                + "WHERE LOGIN_USUARIO = ?";

        Connection connection;

        try
        {
            connection = Conexao.getInstance().getConnection();

            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, nomeUsuario);

            ResultSet rs = pst.executeQuery();

            while (rs != null && rs.next())
            {
                usuario = new Usuario(nomeUsuario, rs.getString("SENHA_USUARIO"));

                usuario.setID(rs.getInt("ID_USUARIO"));
            }

            pst.close();
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuario;
    }
}

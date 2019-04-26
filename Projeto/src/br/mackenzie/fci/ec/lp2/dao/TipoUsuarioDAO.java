package br.mackenzie.fci.ec.lp2.dao;

import br.mackenzie.fci.ec.lp2.exception.PersistenciaException;
import br.mackenzie.fci.ec.lp2.model.TipoUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TipoUsuarioDAO implements GenericoDAO<TipoUsuario>
{
    @Override
    public List<TipoUsuario> listar() throws PersistenciaException
    {
        List<TipoUsuario> usuarios = new ArrayList();

        String sql = "SELECT U.ID_USUARIO, U.NOME_USUARIO, U.LOGIN_USUARIO, U.SENHA_USUARIO, U_TIPO.ID_TIPO_USUARIO "
                + "FROM PROJETO.USUARIO U "
                + "INNER JOIN PROJETO.TIPO_USUARIO U_TIPO "
                + "ON U_TIPO.ID_TIPO_USUARIO=U.ID_TIPO_USUARIO";

        try
        {
            try (Connection connection = Conexao.getInstance().getConnection())
            {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                
                while (rs.next())
                {
                    //TipoUsuario tipoUsuario = new TipoUsuario(result.getInt("ID_USUARIO"), result.getString("NOME_USUARIO"), result.getString("LOGIN_USUARIO"), result.getString("SENHA_USUARIO"), tipoUsuario);
                    
                    //usuarios.add(tipoUsuario);
                }
            }

        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(TipoUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuarios;
    }

    public TipoUsuario listarPorID(TipoUsuario tipoUsuario) throws ClassNotFoundException, SQLException
    {
        try
        {
            String sql = "SELECT * "
                    + "FROM PROJETO.USUARIO "
                    + "WHERE ID_USUARIO = ?";

            Connection connection = Conexao.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, tipoUsuario.getID());
            ResultSet rs = pst.executeQuery();

            if (rs.next())
            {
                tipoUsuario.setID(rs.getInt("ID_USUARIO"));
                tipoUsuario.setNome(rs.getString("NOME_USUARIO"));
            }

            tipoUsuario.setID(rs.getInt("ID_USUARIO"));
            tipoUsuario.setNome(rs.getString("NOME_USUARIO"));
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(TipoUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tipoUsuario;
    }

    //Inserir TipoUsuario
    @Override
    public void inserir(TipoUsuario tipoUsuario) throws PersistenciaException
    {
        Connection connection = null;
        String sql = "INSERT "
                + "INTO PROJETO.USUARIO(NOME_USUARIO, LOGIN_USUARIO, SENHA_USUARIO, ID_TIPO_USUARIO) "
                + "VALUES (?, ?, ?, 1)";

        try
        {
            connection = Conexao.getInstance().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, tipoUsuario.getNome());
            //preparedStatement.setString(2, tipoUsuario.getLogin());
            //preparedStatement.setString(3, tipoUsuario.getSenha());

            preparedStatement.execute();
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(TipoUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível conectar ao servidor de dados!");
        } catch (SQLException ex)
        {
            Logger.getLogger(TipoUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Ops, ocorreu um erro ao enviar o comando para o servidor de dados!");
        } finally
        {
            try
            {
                connection.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(TipoUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Ops, não foi possível fechar a conexão com a base de dados!");
            }
        }
    }

    @Override
    public void alterar(TipoUsuario tipoUsuario) throws PersistenciaException
    {
        try
        {
            String sql = "UPDATE PROJETO.USUARIO "
                    + "SET (NOME_USUARIO) = ? "
                    + "WHERE ID_USUARIO = ?";

            try (Connection connection = Conexao.getInstance().getConnection())
            {
                PreparedStatement pst = connection.prepareStatement(sql);

                pst.setString(1, tipoUsuario.getNome());
                pst.setInt(2, tipoUsuario.getID());

                pst.execute();
            }
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(TipoUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possivel enviar o comando, ou se conectar com a base de dados!");
        }
    }

    @Override
    public void remover(TipoUsuario tipoUsuario) throws PersistenciaException
    {
        String sql = "DELETE "
                + "FROM PROJETO.USUARIO "
                + "WHERE ID_USUARIO = ?";

        try (Connection connection = Conexao.getInstance().getConnection())
        {
            PreparedStatement PST = connection.prepareStatement(sql);
            PST.setInt(1, tipoUsuario.getID());
            PST.execute();
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(TipoUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possivel enviar o comando, ou se conectar com a base de dados!");
        }
    }
}

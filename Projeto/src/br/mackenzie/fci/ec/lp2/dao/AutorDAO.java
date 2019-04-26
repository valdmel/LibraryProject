package br.mackenzie.fci.ec.lp2.dao;

import br.mackenzie.fci.ec.lp2.exception.PersistenciaException;
import br.mackenzie.fci.ec.lp2.model.Autor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutorDAO implements GenericoDAO<Autor>
{
    @Override
    public List<Autor> listar() throws PersistenciaException
    {
        List<Autor> autores = new ArrayList();

        String sql = "SELECT * "
                + "FROM PROJETO.AUTOR";

        try
        {
            try (Connection connection = Conexao.getInstance().getConnection())
            {
                Statement pst = connection.createStatement();
                
                ResultSet rs = pst.executeQuery(sql);
                
                while (rs.next())
                {
                    autores.add(new Autor(rs.getInt("ID_AUTOR"), rs.getString("NOME_AUTOR")));
                }
            }

        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return autores;
    }

    public Autor listarPorID(Autor autor) throws PersistenciaException
    {
        String sql = "SELECT A.ID_AUTOR, A.NOME_AUTOR "
                + "FROM PROJETO.AUTOR A "
                + "WHERE A.ID_AUTOR = ?";

        try (Connection connection = Conexao.getInstance().getConnection())
        {
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, autor.getID());

            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                autor = new Autor(rs.getInt("ID_AUTOR"), rs.getString("NOME_AUTOR"));
            }
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return autor;
    }

    @Override
    public void inserir(Autor autor) throws PersistenciaException
    {
        Connection connection = null;

        String sql = "INSERT "
                + "INTO PROJETO.AUTOR(NOME_AUTOR) "
                + "VALUES (?)";

        try
        {
            connection = Conexao.getInstance().getConnection();

            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, autor.getNome());

            pst.execute();
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível conectar ao servidor de dados!");
        } catch (SQLException ex)
        {
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Ops, ocorreu um erro ao enviar o comando para o servidor de dados!");
        } finally
        {
            try
            {
                connection.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Ops, não foi possível fechar a conexão com a base de dados!");
            }
        }
    }

    @Override
    public void alterar(Autor autor) throws PersistenciaException
    {
        try
        {
            String sql = "UPDATE PROJETO.AUTOR "
                    + "SET NOME_AUTOR = ? "
                    + "WHERE ID_AUTOR = ?";

            try (Connection connection = Conexao.getInstance().getConnection())
            {
                PreparedStatement pst = connection.prepareStatement(sql);

                pst.setString(1, autor.getNome());
                pst.setInt(2, autor.getID());

                pst.execute();
            }
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possivel enviar o comando, ou se conectar com a base de dados!");
        }
    }

    @Override
    public void remover(Autor autor) throws PersistenciaException
    {
        String sql = "DELETE "
                + "FROM PROJETO.AUTOR "
                + "WHERE ID_AUTOR = ?";

        try (Connection connection = Conexao.getInstance().getConnection())
        {
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, autor.getID());

            pst.execute();
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possivel enviar o comando, ou se conectar com a base de dados!");
        }
    }
}

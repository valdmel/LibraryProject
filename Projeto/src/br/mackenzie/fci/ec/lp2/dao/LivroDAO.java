package br.mackenzie.fci.ec.lp2.dao;

import br.mackenzie.fci.ec.lp2.exception.PersistenciaException;
import br.mackenzie.fci.ec.lp2.model.Autor;
import br.mackenzie.fci.ec.lp2.model.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LivroDAO implements GenericoDAO<Livro>
{
    @Override
    public List<Livro> listar() throws PersistenciaException
    {
        List<Livro> livros = new ArrayList();

        String sql = "SELECT L.ID_LIVRO, L.NOME_LIVRO, A.ID_AUTOR, A.NOME_AUTOR "
                + "FROM PROJETO.LIVRO L "
                + "INNER JOIN PROJETO.AUTOR A "
                + "ON L.ID_AUTOR = A.ID_AUTOR";

        try
        {
            try (Connection connection = Conexao.getInstance().getConnection())
            {
                Statement pst = connection.createStatement();

                ResultSet rs = pst.executeQuery(sql);

                while (rs.next())
                {
                    Autor autor = new Autor(rs.getInt("ID_AUTOR"), rs.getString("NOME_AUTOR"));

                    Livro livro = new Livro(rs.getInt("ID_LIVRO"), autor, rs.getString("NOME_LIVRO"));

                    livros.add(livro);
                }

                connection.close();
            }
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return livros;
    }

    public Livro listarPorID(Livro livro) throws PersistenciaException
    {
        String sql = "SELECT L.ID_LIVRO, L.NOME_LIVRO, A.ID_AUTOR, A.NOME_AUTOR "
                + "FROM PROJETO.LIVRO L "
                + "INNER JOIN PROJETO.AUTOR A "
                + "ON L.ID_AUTOR = A.ID_AUTOR "
                + "WHERE L.ID_LIVRO = ?";
        
        try (Connection connection = Conexao.getInstance().getConnection())
        {
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, livro.getID());

            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                Autor autor = new Autor(rs.getInt("ID_AUTOR"), rs.getString("NOME_AUTOR"));
                
                livro = new Livro(rs.getInt("ID_LIVRO"), autor, rs.getString("NOME_LIVRO"));
            }
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return livro;
    }

    @Override
    public void inserir(Livro livro) throws PersistenciaException
    {
        Connection connection = null;
        
        String sql = "INSERT "
                + "INTO PROJETO.LIVRO(ID_AUTOR, NOME_LIVRO) "
                + "VALUES (?, ?)";

        try
        {
            connection = Conexao.getInstance().getConnection();

            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, livro.getIDAutor().getID());
            pst.setString(2, livro.getNome());

            pst.execute();
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível conectar ao servidor de dados!");
        } catch (SQLException ex)
        {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Ops, ocorreu um erro ao enviar o comando para o servidor de dados!");
        } finally
        {
            try
            {
                connection.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Ops, não foi possível fechar a conexão com a base de dados!");
            }
        }
    }

    @Override
    public void alterar(Livro livro) throws PersistenciaException
    {
        try
        {
            String sql = "UPDATE PROJETO.LIVRO "
                    + "SET NOME_LIVRO = ? "
                    + "WHERE ID_LIVRO = ?";

            try (Connection connection = Conexao.getInstance().getConnection())
            {
                PreparedStatement pst = connection.prepareStatement(sql);

                pst.setString(1, livro.getNome());
                pst.setInt(2, livro.getID());

                pst.execute();
            }
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possivel enviar o comando, ou se conectar com a base de dados!");
        }
    }

    @Override
    public void remover(Livro livro) throws PersistenciaException
    {          
        String sql = "DELETE "
                + "FROM PROJETO.LIVRO "
                + "WHERE ID_LIVRO = ?";

        try (Connection connection = Conexao.getInstance().getConnection())
        {
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, livro.getID());

            pst.execute();
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possivel enviar o comando, ou se conectar com a base de dados!");
        }
    }
}

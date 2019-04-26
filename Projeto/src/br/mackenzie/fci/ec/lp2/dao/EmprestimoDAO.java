package br.mackenzie.fci.ec.lp2.dao;

import br.mackenzie.fci.ec.lp2.exception.PersistenciaException;
import br.mackenzie.fci.ec.lp2.model.Autor;
import br.mackenzie.fci.ec.lp2.model.Emprestimo;
import br.mackenzie.fci.ec.lp2.model.Livro;
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

public class EmprestimoDAO implements GenericoDAO<Emprestimo>
{
    @Override
    public List<Emprestimo> listar() throws PersistenciaException
    {
        List<Emprestimo> emprestimos = new ArrayList();
        
        String sql = "SELECT E.ID_EMPRESTIMO, E.DATA_EMPRESTIMO, TU.ID_TIPO_USUARIO, TU.NOME_TIPO_USUARIO, "
                + "U.ID_USUARIO, U.NOME_USUARIO, U.LOGIN_USUARIO, U.SENHA_USUARIO, A.ID_AUTOR, "
                + "A.NOME_AUTOR, L.ID_LIVRO, L.NOME_LIVRO "
                + "FROM PROJETO.EMPRESTIMO E  "
                + "INNER JOIN PROJETO.USUARIO U "
                + "ON E.ID_USUARIO = U.ID_USUARIO "
                + "INNER JOIN PROJETO.TIPO_USUARIO TU "
                + "ON U.ID_TIPO_USUARIO = TU.ID_TIPO_USUARIO "
                + "INNER JOIN PROJETO.LIVRO L "
                + "ON E.ID_LIVRO = L.ID_LIVRO "
                + "INNER JOIN PROJETO.AUTOR A "
                + "ON L.ID_AUTOR = A.ID_AUTOR ";

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
                    
                    Autor autor = new Autor(rs.getInt("ID_AUTOR"), rs.getString("NOME_AUTOR"));
                    
                    Livro livro = new Livro(rs.getInt("ID_LIVRO"), autor, rs.getString("NOME_LIVRO"));
                    
                    Emprestimo emprestimo = new Emprestimo(rs.getInt("ID_EMPRESTIMO"), livro, usuario, rs.getDate("DATA_EMPRESTIMO").toLocalDate());
                    
                    emprestimos.add(emprestimo);
                }
            }

        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return emprestimos;
    }

    public Emprestimo listarPorID(Emprestimo emprestimo) throws PersistenciaException
    {
        String sql = "SELECT E.ID_EMPRESTIMO, E.DATA_EMPRESTIMO, TU.ID_TIPO_USUARIO, TU.NOME_TIPO_USUARIO, "
                + "U.ID_USUARIO, U.NOME_USUARIO, U.LOGIN_USUARIO, U.SENHA_USUARIO, A.ID_AUTOR, "
                + "A.NOME_AUTOR, L.ID_LIVRO, L.NOME_LIVRO "
                + "FROM PROJETO.EMPRESTIMO E  "
                + "INNER JOIN PROJETO.USUARIO U "
                + "ON E.ID_USUARIO = U.ID_USUARIO "
                + "INNER JOIN PROJETO.TIPO_USUARIO TU "
                + "ON U.ID_TIPO_USUARIO = TU.ID_TIPO_USUARIO "
                + "INNER JOIN PROJETO.LIVRO L "
                + "ON E.ID_LIVRO = L.ID_LIVRO "
                + "INNER JOIN PROJETO.AUTOR A "
                + "ON L.ID_AUTOR = A.ID_AUTOR "
                + "WHERE E.ID_EMPRESTIMO = ?";
        
        try (Connection connection = Conexao.getInstance().getConnection())
        {
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, emprestimo.getID());

            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                TipoUsuario tipoUsuario = new TipoUsuario(rs.getInt("ID_TIPO_USUARIO"), rs.getString("NOME_TIPO_USUARIO"));

                Usuario usuario = new Usuario(rs.getInt("ID_USUARIO"), rs.getString("NOME_USUARIO"), rs.getString("LOGIN_USUARIO"), rs.getString("SENHA_USUARIO"), tipoUsuario);

                Autor autor = new Autor(rs.getInt("ID_AUTOR"), rs.getString("NOME_AUTOR"));

                Livro livro = new Livro(rs.getInt("ID_LIVRO"), autor, rs.getString("NOME_LIVRO"));

                emprestimo = new Emprestimo(rs.getInt("ID_EMPRESTIMO"), livro, usuario, rs.getDate("DATA_EMPRESTIMO").toLocalDate());
            }
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return emprestimo;
    }
    
    @Override
    public void inserir(Emprestimo emprestimo) throws PersistenciaException
    {
        Connection connection = null;
        
        String sql = "INSERT "
                + "INTO PROJETO.EMPRESTIMO(ID_LIVRO, ID_USUARIO, DATA_EMPRESTIMO) "
                + "VALUES (?, ?, ?)";

        try
        {
            connection = Conexao.getInstance().getConnection();

            PreparedStatement pst = connection.prepareStatement(sql);

            Livro livro = new Livro();

            pst.setInt(1, livro.getID());
            
            pst.setInt(2, emprestimo.getIDUsuario().getID());
            
            pst.setDate(3, java.sql.Date.valueOf(emprestimo.getData()));

            pst.execute();
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível conectar ao servidor de dados!");
        } catch (SQLException ex)
        {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Ops, ocorreu um erro ao enviar o comando para o servidor de dados!");
        } finally
        {
            try
            {
                connection.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Ops, não foi possível fechar a conexão com a base de dados!");
            }
        }
    }

    @Override
    public void alterar(Emprestimo emprestimo) throws PersistenciaException
    {
        try
        {
            String sql = "UPDATE PROJETO.EMPRESTIMO "
                    + "SET DATA_EMPRESTIMO = ? "
                    + "WHERE ID_EMPRESTIMO = ?";

            try (Connection connection = Conexao.getInstance().getConnection())
            {
                PreparedStatement pst = connection.prepareStatement(sql);

                pst.setDate(1, java.sql.Date.valueOf(emprestimo.getData()));
                pst.setInt(2, emprestimo.getID());

                pst.execute();
            }
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possivel enviar o comando, ou se conectar com a base de dados!");
        }
    }

    @Override
    public void remover(Emprestimo emprestimo) throws PersistenciaException
    {
        String sql = "DELETE FROM PROJETO.EMPRESTIMO "
                + "WHERE ID_EMPRESTIMO = ?";

        try (Connection connection = Conexao.getInstance().getConnection())
        {
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, emprestimo.getID());

            pst.execute();
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possivel enviar o comando, ou se conectar com a base de dados!");
        }
    }
}

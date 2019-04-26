package br.mackenzie.fci.ec.lp2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Conexao
{
    private static Conexao conexao;
    private ResourceBundle bundle = ResourceBundle.getBundle("br.mackenzie.fci.ec.lp2.util.Configuracao");

    private Conexao()
    {
        
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException
    {
        Class.forName(bundle.getString("Driver"));
        return DriverManager.getConnection(bundle.getString("URL"), bundle.getString("Usuário"), bundle.getString("Senha"));
    }

    public static Conexao getInstance()
    {
        if (conexao == null)
        {
            conexao = new Conexao();
        }
        return conexao;
    }
}

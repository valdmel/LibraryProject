package br.mackenzie.fci.ec.lp2.model;

import java.io.Serializable;

public class Usuario implements Serializable
{
    private Integer ID;
    private String nome;
    private String login;
    private String senha;
    private TipoUsuario IDTipoUsuario;

    public Usuario()
    {
        
    }
    
    public Usuario(Integer ID, String nome, String login, String senha, TipoUsuario IDTipoUsuario)
    {
        this.ID=ID;
        this.nome=nome;
        this.login=login;
        this.senha=senha;
        this.IDTipoUsuario=IDTipoUsuario;
    }
    
    public Usuario(String login, String senha)
    {
        this.login=login;
        this.senha=senha;
    }

    public Integer getID()
    {
        return ID;
    }

    public void setID(Integer ID)
    {
        this.ID = ID;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public TipoUsuario getIDTipoUsuario()
    {
        return IDTipoUsuario;
    }

    public void setIDTipoUsuario(TipoUsuario IDTipoUsuario)
    {
        this.IDTipoUsuario = IDTipoUsuario;
    }
}

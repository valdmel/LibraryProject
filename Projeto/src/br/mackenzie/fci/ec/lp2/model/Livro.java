package br.mackenzie.fci.ec.lp2.model;

import java.io.Serializable;

public class Livro implements Serializable
{
    private Integer ID;
    private Autor IDAutor;
    private String nome;

    public Livro()
    {
        
    }
    
    public Livro(Integer ID, Autor IDAutor, String nome)
    {
        this.ID=ID;
        this.IDAutor = IDAutor;
        this.nome = nome;
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

    public Autor getIDAutor()
    {
        return IDAutor;
    }

    public void setIDAutor(Autor IDAutor)
    {
        this.IDAutor = IDAutor;
    }
}

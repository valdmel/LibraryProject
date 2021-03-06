package br.mackenzie.fci.ec.lp2.model;

import java.io.Serializable;

public class Autor implements Serializable
{
    private Integer ID;
    private String nome;

    public Autor()
    {
        
    }

    public Autor(Integer ID, String nome)
    {
        this.ID = ID;
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
}

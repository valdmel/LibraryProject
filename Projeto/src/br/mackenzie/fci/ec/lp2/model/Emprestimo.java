package br.mackenzie.fci.ec.lp2.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Emprestimo implements Serializable
{
    private Integer ID;
    private Livro IDLivro;
    private Usuario IDUsuario;
    private LocalDate data;

    public Emprestimo()
    {

    }

    public Emprestimo(Integer ID, Livro IDLivro, Usuario IDUsuario, LocalDate data)
    {
        this.ID = ID;
        this.IDLivro = IDLivro;
        this.IDUsuario = IDUsuario;
        this.data = data;
    }

    public Integer getID()
    {
        return ID;
    }

    public void setID(Integer ID)
    {
        this.ID = ID;
    }

    public LocalDate getData()
    {
        return data;
    }

    public void setData(LocalDate data)
    {
        this.data = data;
    }

    public Livro getIDLivro()
    {
        return IDLivro;
    }

    public void setIDLivro(Livro IDLivro)
    {
        this.IDLivro = IDLivro;
    }

    public Usuario getIDUsuario()
    {
        return IDUsuario;
    }

    public void setIDUsuario(Usuario IDUsuario)
    {
        this.IDUsuario = IDUsuario;
    }
}

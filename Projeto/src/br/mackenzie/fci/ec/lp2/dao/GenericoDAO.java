package br.mackenzie.fci.ec.lp2.dao;

import br.mackenzie.fci.ec.lp2.exception.PersistenciaException;
import java.io.Serializable;
import java.util.List;

public interface GenericoDAO<E> extends Serializable
{
    List<E> listar() throws PersistenciaException;

    void inserir(E e) throws PersistenciaException;

    void alterar(E e) throws PersistenciaException;

    void remover(E e) throws PersistenciaException;
}

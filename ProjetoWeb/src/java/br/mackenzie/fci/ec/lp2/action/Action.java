package br.mackenzie.fci.ec.lp2.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action
{
    public String execute(HttpServletRequest request, HttpServletResponse response, Method method) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}

package tpanual.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import tpanual.usuario.Usuario;
import tpanual.utilitarios.HibernateFactorySessions;

public class MainHibernate {

	public static void main(String[] args) {
		
		HibernateFactorySessions h = new HibernateFactorySessions();
		
		List<Usuario> us = h.obtenerTodosLosUsuarios();
		Iterator<Usuario> it = us.iterator();
		while (it.hasNext()){
			System.out.println(it.next().getNombre());
		}
		
		h.close();
		
		System.out.println("listo");
	}
}

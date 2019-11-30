package com.jwt.hibernate;

import java.util.List;

import javax.persistence.Query;

public class MierdaQueNoQuieroBorrar {
/*
 
 
 
 
//session.save(dpto);
			
			//session.saveOrUpdate(persona);
			//System.out.println("Se genero el registro con éxito.....!!");
			
			tx.commit();
			
			//CriteriaBuilder cb = session.getCriteriaBuilder();
			//CriteriaQuery<Persona> cq = cb.createQuery(Persona.class);
			//cq.from(Persona.class);
			//Root<Persona> rp = cq.from(Persona.class);
			//cq.select(rp).where(cb.like(rp.get("nombre"), "%a%"));
			//List<Persona> lista = session.createQuery(cq).getResultList();
			//for(Persona p : lista) {
			//	System.out.println(p);
			//}			
			
			System.out.println("\nSolo DNI de las Personas");
			
			Query q = session.getNamedQuery("DniPersonas");
			List<Integer> listaConDni = q.getResultList();
			for(Integer i : listaConDni)
				System.out.println(i);
			
			System.out.println("\nOtra forma de consultar personas");
			q = session.createQuery("Select p from Persona p");
			List<Jugador> listaDePersonas = q.getResultList();
			for(Jugador p : listaDePersonas)
				System.out.println(p);
			
			System.out.println("\nSolo las personas de sexo femenino");
			q = session.createQuery("Select p from Persona p where p.sexo = 'F'");
			listaDePersonas = q.getResultList();
			for(Jugador p : listaDePersonas)
				System.out.println(p);
			
			long totalPersonas = (long) session.createQuery("select count(*) from Persona p").uniqueResult();
			System.out.println("\nCantidad de personas: " + totalPersonas);
			
			System.out.println("\nPersonas y su Departamento");
			q = session.createQuery("Select p.dni, p.apellido, d.descripcion from Persona p, Departamento d where p.codigo_depto = d.codigo");
			List<Object[]> listaDeDatos = q.getResultList();
			for(Object[] registro : listaDeDatos)
				System.out.println(registro[0] + " " + registro[1] + " (" + registro[2] + ")");
 
 
 
 
 */
}

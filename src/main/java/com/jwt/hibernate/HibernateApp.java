package com.jwt.hibernate;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

//import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		Jugador player = new Jugador();

		player.setUsername("Jason");
		player.setPassword("incorrecta");

		Transaction tx = session.beginTransaction();
		try {
			
//			session.saveOrUpdate(player);
//			System.out.println("Se genero el registro con �xito.....!!");
//			
//			CriteriaBuilder cb = session.getCriteriaBuilder();
//			CriteriaQuery<Jugador> cq = cb.createQuery(Jugador.class);
//			cq.from(Jugador.class);
//			Root<Jugador> rp = cq.from(Jugador.class);
//			cq.select(rp).where(cb.like(rp.get("username"), "%a%"));
//			List<Jugador> lista = session.createQuery(cq).getResultList();
//			for(Jugador p : lista) {
//				System.out.println(p);
//			}			
			
			//CONSULTA TODOS LOS PASSWORDS Y LAS DEVUELVE COMO LISTA
//			Query q = session.getNamedQuery("SELECT p.password FROM Jugador p");
//			q.getFirstResult();
//			List<Integer> listaConDni = q.getResultList();
//			for(Integer i : listaConDni)
//				System.out.println(i);
			
			// CONSULTA SIMPLE
//			String consulta = "Jason";
//			Query q = session.createQuery("SELECT u FROM Jugador u WHERE u.username = '" + consulta + "'");
//			Jugador resultado = (Jugador) q.getSingleResult();
//			System.out.println(resultado.toString());
		
			
			//ESTO SIEMPRE VA
			tx.commit();
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}
}

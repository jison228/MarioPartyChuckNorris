package com.jwt.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class JugadorDAO {
	
	@SuppressWarnings("unused")
	public static Jugador loguear(String username, String password) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			String query = "SELECT j FROM Jugador j WHERE j.username = '" + username + "' AND j.password = '" + password
					+ "'";
			Query queryLogueo = session.createQuery(query);
			try {
				Jugador jugador = (Jugador) queryLogueo.getSingleResult();
				return new Jugador(jugador.getUsername(), jugador.getWins(), jugador.getMaxminigame());
			} catch (NoResultException e) {
				return null;
			}

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}

		return null;
	}
	
	
	public static Jugador registrar(String username, String password) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query chequearDuplicado = session.createQuery("SELECT j FROM Jugador j WHERE j.username = '" + username + "'");
			List<Jugador> listaJugadores = chequearDuplicado.getResultList();
			if(!listaJugadores.isEmpty()) {
				return null;
			}else {
				Jugador jugadorNuevo = new Jugador(username, password, 0, 0);
				session.save(jugadorNuevo);
				return jugadorNuevo;
			}

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			tx.commit();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Partida> partidasDe(String jugador) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query partidasJugador = session.createQuery("SELECT p FROM Partida p WHERE p.player1 = '" + jugador + "' OR p.player2 = '"+ jugador 
															+ "' OR p.player3 = '"+ jugador + "' OR p.player4 = '"+ jugador + "'");
			List<Partida> listaPartidas = (ArrayList<Partida>) partidasJugador.getResultList();
			return new ArrayList<Partida>(listaPartidas);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			tx.commit();
		}
		return null;
	}
	
	public static Jugador obtenerWinsYMaxDe(String jugador) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query qJugador = session.createQuery("SELECT j FROM Jugador j WHERE j.username = '" + jugador + "'");
			Jugador jugResult = (Jugador)qJugador.getSingleResult();
			return new Jugador(jugResult.getUsername(), jugResult.getWins(), jugResult.getMaxminigame());
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			tx.commit();
		}
		return null;
	}
	
	public static boolean updateEstadisticas(Jugador jugador) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.merge(jugador);
			return true;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			tx.commit();
		}
		return false;
	}
}

package com.hackbulgaria.ddsystem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hackbulgaria.ddsystem.managers.DroneManager;
import com.hackbulgaria.ddsystem.managers.WareHouseManager;
import com.hackbulgaria.ddsystem.models.Request;


public class Main {
	private static SessionFactory factory;

	public static void main(String[] args) {
		
		try {
			factory = new Configuration().configure("/com/hackbulgaria/database/resources/hibernate.cfg.xml")
					.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		Session session = factory.openSession();
		ClientInterface cli = new ClientInterfaceImplementation(session);
		Request r = cli.parseInput("delivery 4 2016-10-25 12:31 420,369 1 5 3 20");
		System.out.println(r.getProducts().toString());
		
		DroneManager manager = new DroneManager(session);
		manager.showDrones();
		WareHouseManager WHmanager = new WareHouseManager(session);
		WHmanager.showProducts();
		session.close();
	}
	

}

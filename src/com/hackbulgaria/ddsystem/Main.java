package com.hackbulgaria.ddsystem;

import com.hackbulgaria.ddsystem.managers.*;
import com.hackbulgaria.ddsystem.models.*;

import java.sql.Time;
import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
		Request r = cli.parseInputWithoutTime("delivery 4 2016-10-25 12:31 420,369 1 5 3 20");
		System.out.println(r.getProductsMap().toString());
		
		DroneManager manager = new DroneManager(session);
		manager.showDronesFromTable();
		WareHouseManager WHmanager = new WareHouseManager(session);
		WHmanager.showProductsFromTable();
		session.close();
		
		
//        List<Product> products = new ArrayList<>();
//        Warehouse wh = new Warehouse(12384, new Coordinates(42, 42), "WH1");
//        for (int i = 0; i < 50; i++) {
//            Product p = new Product(i, "Product" + i, i);
//            wh.add(new StockItem(i,5*i,p));
//        }
//
//        Date d = new Date();
//        List<Drone> drones = new ArrayList<>();
//        for (int i = 0; i < 2; i++)
//            drones.add(new Drone(2000, 20, 5, new Time(d.getTime())));
//
//        DroneManagerInterface dm = DroneManager.fromList(drones);
//        WareHouseManagerInterface wm = new WareHouseManager(wh);
//
////      wm.showProducts();
//
//        ClientInterface clientInterface = new ClientInterfaceImplementation();
//        String s;
//        Scanner sc = new Scanner(System.in);
//        RequestManagerInterface rm = new RequestManager();
//        while(!(s = sc.nextLine()).equals("exit")) {
//            Request request = clientInterface.parseInput(s);
//            System.out.println(rm.canMakeDelivery(wm,dm,request) ? "Delivery sent!" : "Unable to send delivery");
//            drones.forEach(System.out::println);
//        }
    }


}

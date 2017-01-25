package com.hackbulgaria.ddsystem;

import com.hackbulgaria.ddsystem.managers.DroneManager;
import com.hackbulgaria.ddsystem.managers.RequestManager;
import com.hackbulgaria.ddsystem.managers.WareHouseManager;
import com.hackbulgaria.ddsystem.models.Request;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {
        SessionFactory factory;
        try {
            factory = new Configuration().configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        ClientInterface cli = new ClientInterfaceImplementation(session);
        Request r = cli.parseInputWithoutTime("delivery 4 1485287871 420,369 1 5 3 20");
        System.out.println(r.getProducts().toString());

        DroneManager dmanager = new DroneManager(session);
        WareHouseManager wmanager = new WareHouseManager(session);
        RequestManager requestManager = new RequestManager();
        System.out.println(requestManager.canMakeDelivery(wmanager, dmanager, r));
        session.close();
    }


}

package com.hackbulgaria.ddsystem;

import com.hackbulgaria.ddsystem.managers.*;
import com.hackbulgaria.ddsystem.models.Request;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class RequestHandler {
    public static final String QUIT = "quit";
    public static final String DELIVERY = "delivery";
    public static final String SUPPLY = "supply";

    public static void main(String[] args) throws IOException {
/*        if (args.length != 3) {
            throw new IllegalStateException("Invalid arguments. Format is <dbname> <user> <password>");
        }*/

        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;

        fh = new FileHandler("droneDelivery.log", true);
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);

        Session session = initSession();
        logger.info("Initialized system");


        RequestParser cli = new PersistentParser(session);

        DroneManager dmanager = new PersistentDroneManager(session);
        WarehouseManager wmanager = new PersistentWarehouseManager(session);
        RequestManager requestManager = new RequestManager(logger, wmanager, dmanager);

        Request r;
        String s;
        Scanner scanner = new Scanner(System.in);
        while (!(s = scanner.nextLine()).equals(QUIT)) {
            r = cli.parse(s);

            logger.info(requestManager.canMakeDelivery(r) ?
                    "Request sent successfully." : "Failed executing request.");
        }
        session.close();
        logger.info("Shutting down system.");
    }

    private static Session initSession() {
        SessionFactory factory;
        try {
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
//                    .setProperty("hibernate.connection.url","jdbc:mysql://localhost/" + dbName)
//                    .setProperty("hibernate.connection.username",user)
//                    .setProperty("hibernate.connection.password",password);
            factory = cfg.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return factory.openSession();
    }
}

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
    
    private Session session;
    private static Logger logger;
    private DroneManager droneManager;
    private WarehouseManager warehouseManager;
    private RequestManager requestManager;
    
    public RequestHandler() throws IOException{
    	logger = Logger.getLogger("MyLog");
        FileHandler fh = new FileHandler("droneDelivery.log", true);
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        
        
        logger.info("Initialized system");
    	this.session = initSession();
   	  	this.droneManager = new PersistentDroneManager(session);
        this.warehouseManager = new PersistentWarehouseManager(session);
        this.requestManager = new RequestManager(logger, warehouseManager, droneManager);
        
    }
    
    public void handle() throws IOException {
    	

		RequestParser parser = new PersistentParser(session, logger);

		Request request;
		String command;
		Scanner scanner = new Scanner(System.in);
		try {
			while (!(command = scanner.nextLine()).equals(QUIT)) {
				request = parser.parse(command);
				if (request.getRequestType().equals(DELIVERY)) {
					requestManager.makeDelivery(request);
					
				} else if (request.getRequestType().equals(SUPPLY)) {
					warehouseManager.makeSupply(request);
				} else {
					logger.info("NON existing request type.");
				}
			}
		} finally {
			session.close();
			logger.info("Shutting down system.");
			scanner.close();
		}
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

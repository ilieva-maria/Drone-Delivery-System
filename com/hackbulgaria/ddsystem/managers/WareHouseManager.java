package com.hackbulgaria.ddsystem.managers;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import com.hackbulgaria.database.Products;
import com.hackbulgaria.ddsystem.models.Request;
import com.hackbulgaria.ddsystem.results.ProductResults;

public class WareHouseManager implements WareHouseManagerInterface{
	private Session session;
	
	public WareHouseManager(Session session) {
		this.session = session;
	}

	public void showProducts() {
		List<?> products = session.createQuery("FROM Products").getResultList();
		for (Iterator<?> iterator = products.iterator(); iterator.hasNext();) {
			Products product = (Products) iterator.next();
			System.out.print("ID: " + product.getId());
			System.out.print("  Name: " + product.getName());
			System.out.println();
			
		}
	}

	@Override
	public ProductResults checkProducts(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

}

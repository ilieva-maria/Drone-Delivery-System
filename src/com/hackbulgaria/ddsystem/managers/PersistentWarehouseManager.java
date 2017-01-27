package com.hackbulgaria.ddsystem.managers;

import com.hackbulgaria.ddsystem.models.Product;
import com.hackbulgaria.ddsystem.models.Request;
import com.hackbulgaria.ddsystem.models.StockItem;
import com.hackbulgaria.ddsystem.models.Warehouse;
import com.hackbulgaria.ddsystem.results.ProductResults;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Map;

public class PersistentWarehouseManager implements WarehouseManager {
	private Session session;
	private Warehouse warehouse;

	@SuppressWarnings("unchecked")
	public PersistentWarehouseManager(Session session) {
		this.session = session;
		List<Warehouse> list = session.createQuery("FROM Warehouse").getResultList();
		this.warehouse = list.get(0);
	}

	@SuppressWarnings("unchecked")
	public void showProductsFromTable() {
		session.createQuery("FROM Product").getResultList().forEach(System.out::println);
	}

	public void showProducts() {
		warehouse.getItems().forEach(System.out::println);
	}

	@Override
	public Warehouse getWarehouse() {
		return warehouse;
	}

	@Override
	public void makeDelivery(ProductResults productResults) {
		for (StockItem item : productResults.getPurchased()) {
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				session.update(item);
				tx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				if (tx != null) {
					tx.rollback();
				}
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public ProductResults checkProducts(Request request) {
		ProductResults results = new ProductResults();

		if (request.getProducts().size() == 0) {
			return null;
		}

		Map<Product, Integer> supplyProducts = request.getProducts();

		for (Map.Entry<Product, Integer> supplyProduct : supplyProducts.entrySet()) {
			Product p = supplyProduct.getKey();
			Integer q = supplyProduct.getValue();
			String query = String.format("FROM StockItem where quantity > %d and id = %d", q, p.getId());

			List<StockItem> items = session.createQuery(query).getResultList();
			if (items.isEmpty()) {
				return null;
			}

			StockItem item = items.get(0);
			results.addWeight(item.getProduct().getWeight() * q);

			// Adding the stockitem with new quantity to update later
			item.setQuantity(item.getQuantity() - q);
			results.addPurchasedItem(item);
		}

		results.setWareHouse(request.getCoordinates());
		return results;
	}

	@Override
	public void makeSupply(Request request) {
		// TODO Auto-generated method stub
		Map<Product, Integer> supplyProducts = request.getProducts();

		for (Map.Entry<Product, Integer> supplyProduct : supplyProducts.entrySet()) {
			Product p = supplyProduct.getKey();
			Integer q = supplyProduct.getValue();

			StockItem item = (StockItem) session.get(StockItem.class, p.getId());
			Transaction tx = null;
			if (item == null) {
				//Means that the product is not in the DB and needs to be added
				item = new StockItem(p, q);
			} 
			System.out.println(item);
			item.setQuantity(item.getQuantity() + q);
			try {
				tx = session.beginTransaction();
				System.out.println(p.toString());
				session.save(p);
				session.save(item);
				tx.commit();

			} catch (Exception e) {
				e.printStackTrace();
				if (tx != null) {
					tx.rollback();
				}
			}
		}
	}
}

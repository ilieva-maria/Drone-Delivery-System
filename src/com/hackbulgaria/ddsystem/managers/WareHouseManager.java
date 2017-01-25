package com.hackbulgaria.ddsystem.managers;

import com.hackbulgaria.ddsystem.models.Product;
import com.hackbulgaria.ddsystem.models.Request;
import com.hackbulgaria.ddsystem.models.StockItem;
import com.hackbulgaria.ddsystem.models.Warehouse;
import com.hackbulgaria.ddsystem.results.ProductResults;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class WareHouseManager implements WareHouseManagerInterface {
    private Session session;
    private Warehouse warehouse;


    @SuppressWarnings("unchecked")
    public WareHouseManager(Session session) {
        this.session = session;
        List<Warehouse> list = session.createQuery("FROM Warehouse").getResultList();
        this.warehouse = list.get(0);
    }

    public void showProductsFromTable() {
        List<?> products = session.createQuery("FROM Product").getResultList();
        for (Iterator<?> iterator = products.iterator(); iterator.hasNext(); ) {
            Product product = (Product) iterator.next();
            System.out.print("ID: " + product.getId());
            System.out.print("  Name: " + product.getName());
            System.out.println();

        }
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
        for (StockItem item : productResults.getPurchased()){
            session.update(item);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public ProductResults checkProducts(Request request) {
        ProductResults results = new ProductResults();

        if (request.getProducts().size() == 0) {
            results.setAvailable(false);
            return results;
        }

        Map<Product, Integer> requestedProducts = request.getProducts();

        for (Map.Entry<Product, Integer> requestedProduct : requestedProducts.entrySet()) {
            Product p = requestedProduct.getKey();
            Integer q = requestedProduct.getValue();
            String query = String.format("FROM StockItem where quantity > %d and id = %d", q, p.getId());


            List<StockItem> items = session.createQuery(query).getResultList();
            if (items.isEmpty()) {
                results.setAvailable(false);
                break;
            }

            StockItem item = items.get(0);
            results.addWeight(item.getProduct().getWeight() * q);

            //Adding the stockitem with new quantity to update later
            item.setQuantity(item.getQuantity()-q);
            results.addPurchasedItem(item);
        }

        results.setWareHouse(request.getCoordinates());
        return results;
    }

}

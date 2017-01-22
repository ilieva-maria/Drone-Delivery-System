package com.hackbulgaria.ddsystem.managers;

import com.hackbulgaria.ddsystem.models.Product;
import com.hackbulgaria.ddsystem.models.Request;
import com.hackbulgaria.ddsystem.models.StockItem;
import com.hackbulgaria.ddsystem.models.Warehouse;
import com.hackbulgaria.ddsystem.results.ProductResults;
import org.hibernate.Session;

import java.util.List;

public class WareHouseManager implements WareHouseManagerInterface {
    private Session session;
    private Warehouse warehouse;

    public WareHouseManager(Session session) {
        this.session = session;
    }

    public WareHouseManager(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void showProducts() {
        //List<?> products = session.createQuery("FROM Products").getResultList();
        warehouse.getItems().forEach(System.out::println);
    }

    @Override
    public Warehouse getWarehouse() {
        return warehouse;
    }

    @Override
    public void makeDelivery() {

    }

    @Override
    public ProductResults checkProducts(Request request) {
        ProductResults results = new ProductResults();

        if (request.getProducts().size() == 0) {
            results.setAvailable(false);
            return results;
        }

        List<StockItem> products = request.getProducts();
        List<StockItem> inWarehouse = warehouse.getItems();

        for (StockItem stockItem : products) {
            boolean inStock = inWarehouse.contains(stockItem);
            int index;
            if (inStock) {
                index = inWarehouse.indexOf(stockItem);
                Product product = inWarehouse.get(index).getProduct();
                if (inWarehouse.get(index).getQuantity() >= stockItem.getQuantity()) {
                    results.addWeight(stockItem.getQuantity() * product.getWeight());
                } else {
                    results.setAvailable(false);
                    break;
                }

            } else {
                results.setAvailable(false);
                break;
            }
        }

        results.setWareHouse(request.getCoordinates());
        return results;
    }

}

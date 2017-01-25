package com.hackbulgaria.ddsystem.models;

public class StockItem {
    private int id;
    private int quantity;
    private Product product;
    private Warehouse warehouse;

    public StockItem() {
    }

    public StockItem(int id, int quantity, Product product) {
        super();
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }

    public StockItem(Product key, Integer value) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return String.format("#%d | %s | %d pcs", id, product.toString(), quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockItem stockItem = (StockItem) o;

        return id == stockItem.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}

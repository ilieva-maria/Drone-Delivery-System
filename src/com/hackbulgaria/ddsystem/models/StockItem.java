package com.hackbulgaria.ddsystem.models;

public class StockItem {
    private int id;
    private int quantity;
    private Product product;

    public StockItem() {
    }

    public StockItem(int id,int quantity, Product product) {
        super();
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StockItem other = (StockItem) obj;
        if (id != other.id)
            return false;
        return true;
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
}

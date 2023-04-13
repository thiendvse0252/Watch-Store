/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

public class Watch {

    private String id;
    private String name;
    private double price;
    private int quantity;
    private String image;
    private String categoryID;

    public Watch() {
        this.id = "";
        this.name = "";
        this.price = 0;
        this.quantity = 0;
        this.image = "";
        this.categoryID = "";
    }

    public Watch(String id, String name, double price, int quantity, String image, String categoryID) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.categoryID = categoryID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String toString() {
        return "Watch{" + "id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", image=" + image + ", categoryID=" + categoryID + '}';
    }

    
    

}

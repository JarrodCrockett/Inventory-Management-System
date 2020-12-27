package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**Product class. Used to create products.*/
public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**Set Id. used to set the product Id.
     * @param id to set the product to.*/
    public void setId(int id) {
        this.id = id;
    }

    /**Set Name. used to set the name of the product.
     * @param name to set the product to.*/
    public void setName(String name) {
        this.name = name;
    }

    /**Set price. Used to set the price.
     * @param price to set the product to.*/
    public void setPrice(double price) {
        this.price = price;
    }

    /**Set stock. used to set the products stock.
     * @param stock to set the product to.*/
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**Set min. used to set the min.
     * @param min to set the product min.*/
    public void setMin(int min) {
        this.min = min;
    }

    /**Set max. Used to set max on the product.
     * @param max to set the product to.*/
    public void setMax(int max) {
        this.max = max;
    }

    /**Get Id. used to get product id.
     * @return int of the product.*/
    public int getId() {
        return this.id;
    }

    /**Get name. Used to get the product name.
     * @return String of the product name.*/
    public String getName() {
        return this.name;
    }

    /**Get Price. Used to get the product price.
     * @return double of the product price.*/
    public double getPrice() {
        return this.price;
    }

    /**Get stock. Used to get the product stock.
     *@return int of the products stock. */
    public int getStock() {
        return this.stock;
    }

    /**Get min. Used to get the min of the product.
     * @return int min of the product.*/
    public int getMin() {
        return this.min;
    }

    /**Get max. gives the max of the product.
     * @return int max of the product.*/
    public int getMax() {
        return this.max;
    }

    /**Adds the associated part. Adds the part to the associated part list.
     * @param part to be added.*/
    public void addAssociatedPart(Part part){
        this.associatedParts.add(part);
    }

    /**Delete associated part. Deletes the part from the list.
     * @param selectedAssociatedPart to be deleted from the list.
     * @return boolean of if the part was deleted.*/
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        return this.associatedParts.remove(selectedAssociatedPart);
    }

    /**Get all associated parts. Returns all the associated parts with the product.
     * @return ObservableList of parts.*/
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }
}

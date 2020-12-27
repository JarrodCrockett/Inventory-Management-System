package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Inventory class. Static class that are used to save ObservableList of parts and products.
 * */
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**@param newPart to be added to the list*/
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    /**@param newProduct to be added to the list*/
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    /**@param partId to lookup in the Part list.
     * @return Part returned from the list.*/
    public static Part lookupPart(int partId){
        if (!allParts.isEmpty()){
            for (Part allPart : allParts) {
                if (partId == allPart.getId()) {
                    return allPart;
                }
            }
        }
        return null;
    }

    /**Look up part. Looks up the part from the observableList
     * @param partName to look up.
     * @return ObservableList of parts.*/
    public static ObservableList<Part> lookupPart(String partName){
        if (!allParts.isEmpty()){
            ObservableList<Part> searchPartsList = FXCollections.observableArrayList();
            for (Part allPart : allParts) {
                if (allPart.getName().contains(partName)) {
                    searchPartsList.add(allPart);
                }
            }
            return searchPartsList;
        }
        return null;
    }

    /**Look up product. Looks up the product by Id.
     * @param  productId id to look up.
     * @return Product looked for.*/
    public static Product lookupProduct(int productId){
        if (!allProducts.isEmpty()){
            for (Product product : allProducts) {
                if (productId == product.getId()) {
                    return product;
                }
            }
        }
        return null;
    }

    /**Look up product. By the name
     * @param productName name to be searched
     * @return ObservableList of products*/
    public static ObservableList<Product> lookupProduct(String productName){
        if (!allProducts.isEmpty()){
            ObservableList<Product> searchProductList = FXCollections.observableArrayList();
            for (Product allProduct : allProducts) {
                if (allProduct.getName().contains(productName)) {
                    searchProductList.add(allProduct);
                }
            }
            return searchProductList;
        }
        return null;
    }

    /**Update part. Updates a part
     * @param index of the part array list
     * @param  selectedPart name of part.*/
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    /**Update product. Updates a part
     * @param index of the part array list
     * @param  selectedProduct name of product.*/
    public static void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }

    /**Delete part. deletes the selected part.
     * @param selectedPart to be deleted.
     * @return boolean of if the part was deleted.*/
    public static boolean deletePart(Part selectedPart){
        if (allParts.contains(selectedPart)){
            allParts.remove(selectedPart);
            return true;
        }
        return false;
    }

    /**Delete product. deletes a selected product.
     * @param selectedProduct to be removed.
     * @return boolean of if the product was deleted.*/
    public static boolean deleteProduct(Product selectedProduct){
        if (allProducts.contains(selectedProduct)){
            allProducts.remove(selectedProduct);
            return true;
        }
        return false;
    }

    /**Get all parts. Returns all the parts in the list.
     * @return ObservableList of all parts.*/
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**Get all products. Returns all the products in the list.
     *@return ObservableList of all the products. */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}

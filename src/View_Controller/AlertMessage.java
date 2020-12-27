package View_Controller;

import javafx.scene.control.Alert;

/**AlertMessage class. This static class is used for all alert messages throughout the application.*/
public class AlertMessage {

    /**Main screen error. This is all the error messages for the main screen.
     * @param errorCodeMain is the code for the text to display.*/
    public static void mainScreenError(int errorCodeMain){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Main Screen");
        alert.setHeaderText("Error");
        if (errorCodeMain == 1){
            alert.setContentText("There are no parts to modify.");
        }
        if (errorCodeMain == 2){
            alert.setContentText("There is no part selected.");
        }
        if (errorCodeMain == 3){
            alert.setContentText("There is no products to modify.");
        }
        if (errorCodeMain == 4){
            alert.setContentText("There is no product selected.");
        }
        if (errorCodeMain == 5) {
            alert.setContentText("This product contains parts. It could not be deleted.");
        }
        alert.showAndWait();
    }

    /**Part error. This message is displayed for a part error.
     * @param errorCodeParts is the code used for the message to display.*/
    public static void partError(int errorCodeParts) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Part");
        alert.setHeaderText("Error adding or modifying part.");
        if (errorCodeParts == 1) {
            alert.setContentText("Name, price, and machineID must be completed.");
        }
        if (errorCodeParts == 2) {
            alert.setContentText("Please select In House or Outsourced");
        }
        if (errorCodeParts == 3) {
            alert.setContentText("Inventory stock, Max, and Min must be a positive number.");
        }
        if (errorCodeParts == 4) {
            alert.setContentText("Machine ID must be a number.");
        }
        if (errorCodeParts == 5) {
            alert.setContentText("Max must be greater than Min.");
        }
        if (errorCodeParts == 6) {
            alert.setContentText("Inventory in stock must be between Min and Max.");
        }
        if (errorCodeParts == 7) {
            alert.setContentText("Price must be a positive number.");
        }
        alert.showAndWait();
    }

    /**Part added. This is the message displayed when a part is added or modified.*/
    public static void partAdded() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Part");
        alert.setHeaderText("Saving");
        alert.setContentText("You saved the part.");
        alert.showAndWait();
    }

    /**Product added. This is the message displayed when a product is added or modified.*/
    public static void productAdded(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Product");
        alert.setHeaderText("Saving");
        alert.setContentText("You saved the Product.");
        alert.showAndWait();
    }

    /**Product cancelled. This is the message displayed when the product is Cancelled.*/
    public static void productCancelled(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Product");
        alert.setHeaderText("Cancelling");
        alert.setContentText("You did not add a new product.");
        alert.showAndWait();
    }

    /**Product error. This is the messages used for product errors.
     * @param errorCodeProduct is the error code for the message to display.*/
    public static void productError(int errorCodeProduct) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Product");
        alert.setHeaderText("Error adding or modifying product.");
        if (errorCodeProduct == 1) {
            alert.setContentText("Name, and price fields must be completed.");
        }
        if (errorCodeProduct == 2) {
            alert.setContentText("Inventory stock, Max, and Min must be a positive number.");
        }
        if (errorCodeProduct == 3) {
            alert.setContentText("Max must be greater than Min.");
        }
        if (errorCodeProduct == 4) {
            alert.setContentText("Inventory in stock must be between Min and Max.");
        }
        if (errorCodeProduct == 5) {
            alert.setContentText("There are no associated parts with this product.");
        }
        if (errorCodeProduct == 6) {
            alert.setContentText("Price must be a positive number.");
        }
        if (errorCodeProduct == 7) {
            alert.setContentText("A part must be selected.");
        }
        if (errorCodeProduct == 8) {
            alert.setContentText("This part is already added.");
        }
        if (errorCodeProduct == 9){
            alert.setContentText("The listed parts can not be more than product price.");
        }
        alert.showAndWait();
    }
}

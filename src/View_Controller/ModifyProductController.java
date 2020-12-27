package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {

    private Stage stage;
    private Parent scene;
    private Product product;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();


    @FXML
    private TextField productID;

    @FXML
    private TextField productName;

    @FXML
    private TextField productInventory;

    @FXML
    private TextField productPrice;

    @FXML
    private TextField productMax;

    @FXML
    private TextField productMin;

    @FXML
    private TextField partSearch;

    @FXML
    private TableView<Part> partsTableView;

    @FXML
    private TableColumn<Part, Integer> partID;

    @FXML
    private TableColumn<Part, String> partName;

    @FXML
    private TableColumn<Part, Integer> partInv;

    @FXML
    private TableColumn<Part, Double> partPrice;

    @FXML
    private TableView<Part> selectedPartsTableView;

    @FXML
    private TableColumn<Part, Integer> selectedPartID;

    @FXML
    private TableColumn<Part, String> selectedPartName;

    @FXML
    private TableColumn<Part, Integer> selectedPartInv;

    @FXML
    private TableColumn<Part, Integer> selectedPartPrice;


    public ModifyProductController() {
    }

    /**Initialize method. Used to initialize the class.
     * @param url the location of the fxml.
     * @param resourceBundle the functions used in the class.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTableView.setItems(Inventory.getAllParts());
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**Send date. Sends the data from the main screen controller to the modify product controller.
     * @param product and creates the product within the controller that was selected from the main screen.*/
    public void sendData(Product product){

        Product selectedProduct = (Product) product;
        this.productID.setText(Integer.toString(selectedProduct.getId()));
        this.productName.setText(selectedProduct.getName());
        this.productPrice.setText(Double.toString(selectedProduct.getPrice()));
        this.productInventory.setText(Integer.toString(selectedProduct.getStock()));
        this.productMin.setText(Integer.toString(selectedProduct.getMin()));
        this.productMax.setText(Integer.toString(selectedProduct.getMax()));

        this.associatedParts = selectedProduct.getAllAssociatedParts();
        selectedPartsTableView.setItems(associatedParts);
        selectedPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        selectedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        selectedPartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        selectedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        selectedPartsTableView.refresh();

        setProduct(product);

    }

    /**Remove selected part. Removes the selected part in a table from the current product.
     * @param event when the remove button is pushed.*/
    @FXML
    void removeAssociatedPartFromTable(ActionEvent event) {
        if (associatedParts.isEmpty()){
            return;
        }
        Part selectedPart = selectedPartsTableView.getSelectionModel().getSelectedItem();
        associatedParts.remove(selectedPart);
        partsTableView.refresh();
    }

    public void setProduct(Product product){
        this.product = product;
    }

    /**Save product. Saves the product when the save button is pushed.
     * @param actionEvent when the save button is pushed.*/
    @FXML
    void saveProduct(ActionEvent actionEvent) {
        int indexOfProduct = Inventory.getAllProducts().indexOf(this.product);
        if (!productValidation()){
            return;
        }

        int id = Integer.parseInt(productID.getText().trim());
        String name = productName.getText().trim();
        double price = Double.parseDouble(productPrice.getText().trim());
        int inv = Integer.parseInt(productInventory.getText().trim());
        int min = Integer.parseInt(productMin.getText().trim());
        int max = Integer.parseInt(productMax.getText().trim());

        Product productToSave = new Product(id, name, price, inv, min, max);
        for (int i = 0; i < associatedParts.size(); i++) {
            productToSave.addAssociatedPart(associatedParts.get(i));
        }
        Inventory.updateProduct(indexOfProduct, productToSave);
        AlertMessage.productAdded();
        loadMainScreen(actionEvent);
    }

    /**Product validation. This validates that the product text boxes and added parts is valid.
     * @return boolean of if the validation was true or false.*/
    private boolean productValidation() {
        if (productName.getText().trim().isEmpty()
                || productInventory.getText().trim().isEmpty()
                || productPrice.getText().trim().isEmpty()
                || productMax.getText().trim().isEmpty()
                || productMin.getText().trim().isEmpty()){
            AlertMessage.productError(1);
            return false;
        }
        if (productInventory.getText().trim().isEmpty()){
            productInventory.setText("0");
        }
        if (productMax.getText().trim().isEmpty()){
            productMax.setText("1");
        }
        if (productMin.getText().trim().isEmpty()){
            productMin.setText("0");
        }
        if (!productInventory.getText().trim().matches("[0-9]*")
                || !productMax.getText().trim().matches("[0-9]*")
                || !productMin.getText().trim().matches("[0-9]*")){
            AlertMessage.productError(2);
            return false;
        }
        if (!productPrice.getText().trim().matches("^(0|[1-9][0-9]*)(\\.[0-9]+)?$")){
            AlertMessage.productError(6);
        }
        if (Integer.parseInt(productMax.getText().trim()) < Integer.parseInt(productMin.getText().trim())){
            AlertMessage.productError(3);
            return false;
        }
        if (Integer.parseInt(productInventory.getText().trim()) < Integer.parseInt(productMin.getText().trim())
                || Integer.parseInt(productInventory.getText().trim()) > Integer.parseInt(productMax.getText().trim())){
            AlertMessage.productError(4);
            return false;
        }
        if (Double.parseDouble(productPrice.getText().trim()) < addedPartPrice()){
            AlertMessage.productError(9);
            return false;
        }
        return true;
    }

    /**Added part price. Adds the part prices from the associated parts table on the product.
     * @return double of the all added parts prices.*/
    private double addedPartPrice(){
        double sum = 0;
        for (int i = 0; i < associatedParts.size(); i++){
            sum += associatedParts.get(i).getPrice();
        }
        return sum;
    }

    /**Search part. Used to search through the parts list. If parts are found they are populated into a table.
     * @param actionEvent of the text entered into the search and pressing enter.*/
    @FXML
    public void searchPart(ActionEvent actionEvent) {
        if (partSearch.getText().trim().isEmpty()){
            updatePartTableView();
        }
        else if (!partSearch.getText().trim().isEmpty()&& !partSearch.getText().trim().matches("[0-9]*")){
            partsTableView.setItems(Inventory.lookupPart(partSearch.getText().trim()));
            partsTableView.refresh();
        }
        else if (partSearch.getText().trim().matches("[0-9]*")){
            if (Inventory.lookupPart(Integer.parseInt(partSearch.getText().trim())) != null){
                Part part = Inventory.lookupPart(Integer.parseInt(partSearch.getText().trim()));
                if (Inventory.lookupPart(part.getName()) != null){
                    partsTableView.setItems(Inventory.lookupPart(part.getName()));
                    partsTableView.refresh();
                }
            }

        }
    }

    /**Update part table. Updates the parts table with the current parts list.*/
    public void updatePartTableView(){
        partsTableView.setItems(Inventory.getAllParts());
    }

    /**Add part. Adds the part to the parts table view.
     * @param actionEvent the add part button being pushed.*/

    public void addPart(ActionEvent actionEvent) {
        Part selected = partsTableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            AlertMessage.productError(7);
            return;
        }

        if (!associatedParts.isEmpty()) {
            int id = selected.getId();
            for (int i = 0; i < associatedParts.size(); i++) {
                if (associatedParts.get(i).getId() == id) {
                    AlertMessage.productError(8);
                    return;
                }
            }
        }
        associatedParts.add(selected);
        selectedPartsTableView.refresh();
    }

    /**Load main screen. This loads the main screen after exiting this window.
     * @param actionEvent pushing the cancel button.*/
    private void loadMainScreen(ActionEvent actionEvent) {
        stage = (Stage)((Button) actionEvent.getSource()).getScene().getWindow();
        try {
            scene = FXMLLoader.load(getClass().getResource("../View_Controller/MainScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Cancel adding product. This cancels the product from being modified and returns to the main screen.
     * @param actionEvent pushing the cancel button.*/
    @FXML
    void cancelModifyProduct(ActionEvent actionEvent) {
        AlertMessage.productCancelled();
        loadMainScreen(actionEvent);
    }
}

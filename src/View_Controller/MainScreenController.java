package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.application.Platform;
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
import java.util.Optional;
import java.util.ResourceBundle;

/**MainScreenController class. This creates a main screen controller class that controls the main screen of the application.
 * Functionality could be extended by connecting a database to the application to save parts and products to.
 * This would allow for the application to save information long term.*/
public class MainScreenController implements Initializable {

    Stage stage;
    Parent scene;


    @FXML private TextField productSearch;
    @FXML private TextField partSearch;

    @FXML private TableView<Product> productTableView;
    @FXML private TableColumn<Product, Integer> productID;
    @FXML private TableColumn<Product, String> productName;
    @FXML private TableColumn<Product, Integer> productInventoryLevel;
    @FXML private TableColumn<Product, Double> productPrice;

    @FXML private TableView<Part> partTableView;
    @FXML private TableColumn<Part, Integer> partID;
    @FXML private TableColumn<Part, String> partName;
    @FXML private TableColumn<Part, Integer> partInventoryLevel;
    @FXML private TableColumn<Part, Double> partPrice;

    public MainScreenController() {

    }

    /**Initialize method. Used to initialize the class.
     * @param url the location of the fxml.
     * @param resourceBundle the functions used in the class.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productTableView.setItems(Inventory.getAllProducts());
        productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        partTableView.setItems(Inventory.getAllParts());
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**Delete product. Deletes a product from the table view for products.
     * @param actionEvent the delete button being clicked.*/
    public void deleteProduct(ActionEvent actionEvent) {
        Product product = productTableView.getSelectionModel().getSelectedItem();

        if (Inventory.getAllProducts().contains(product)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Product");
            alert.setHeaderText("Delete");
            alert.setContentText("Do you want to delete this part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (!product.getAllAssociatedParts().isEmpty()){
                AlertMessage.mainScreenError(5);
            }
            else{
                if (result.get() == ButtonType.OK) {
                    Inventory.deleteProduct(product);
                    updateProductTableView();
                }
            }
        }
    }

    /**Add product. A new product is created and the screen for the add product is created.
     * @param  actionEvent add product button.*/
    public void addProduct(ActionEvent actionEvent) {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        try {
            scene = FXMLLoader.load(getClass().getResource("../View_Controller/AddProduct.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Modify product. Modifies a selected product from the table view. The modify product screen is loaded.
     * @param actionEvent the modify product button.*/
    public void modifyProduct(ActionEvent actionEvent) throws IOException {

        Product selected = productTableView.getSelectionModel().getSelectedItem();

        if (Inventory.getAllProducts().isEmpty()){
            AlertMessage.mainScreenError(3);
            return;
        }
        if (selected == null){
            AlertMessage.mainScreenError(4);
            return;
        }
        /**This created a run time error during the first writing. It was corrected by changing the way the controller sent data to the modify product controller.*/
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View_Controller/modifyProduct.fxml"));
        loader.load();

        ModifyProductController controller = loader.getController();

        controller.sendData(selected);

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();


    }

    /**Add part. A new part is added and the add part screen is loaded.
     * @param actionEvent the add part button is clicked.*/
    public void addPart(ActionEvent actionEvent) {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        try {
            scene = FXMLLoader.load(getClass().getResource("../View_Controller/AddPart.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Modify part. The part that is selected is modified. The scene for the modify part is loaded.
     * @param actionEvent modify part button is clicked.*/
    public void modifyPart(ActionEvent actionEvent) throws IOException {

        Part selected = partTableView.getSelectionModel().getSelectedItem();

        if (Inventory.getAllParts().isEmpty()){
            AlertMessage.mainScreenError(1);
            return;
        }
        if (selected == null){
            AlertMessage.mainScreenError(2);
            return;
        }
        /**This created a run time error during the first writing. It was corrected by changing the way the controller sent data to the modify part controller.*/
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View_Controller/modifyPart.fxml"));
        loader.load();

        ModifyPartController controller = loader.getController();

        controller.sendData(selected);

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Delete part. The selected part is deleted.
     * @param actionEvent the delete part button is clicked.*/
    public void deletePart(ActionEvent actionEvent) {
        Part part = partTableView.getSelectionModel().getSelectedItem();
        if (Inventory.getAllParts().contains(part)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Part");
            alert.setHeaderText("Delete");
            alert.setContentText("Do you want to delete this part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK){
                Inventory.deletePart(part);
                updatePartTableView();
            }
        }
    }

    /**Search part. The part entered into the text box is searched for.
     * @param actionEvent when enter is pressed after typing in the search box.*/
    public void searchPart(ActionEvent actionEvent) {
        if (partSearch.getText().trim().isEmpty()){
            updatePartTableView();
        }
        else if (!partSearch.getText().trim().isEmpty()&& !partSearch.getText().trim().matches("[0-9]*")){
            partTableView.setItems(Inventory.lookupPart(partSearch.getText().trim()));
            partTableView.refresh();
        }
        else if (partSearch.getText().trim().matches("[0-9]*")){
            if (Inventory.lookupPart(Integer.parseInt(partSearch.getText().trim())) != null){
                Part part = Inventory.lookupPart(Integer.parseInt(partSearch.getText().trim()));
                if (Inventory.lookupPart(part.getName()) != null){
                    partTableView.setItems(Inventory.lookupPart(part.getName()));
                    partTableView.refresh();
                }
            }

        }
    }

    /**Search product. Searches the product table for a listed product.
     * @param  actionEvent the enter key is pressed after entering text in the search text box.*/
    public void searchProduct(ActionEvent actionEvent) {
        if (productSearch.getText().trim().isEmpty()){
            updateProductTableView();
        }
        else if(!productSearch.getText().trim().isEmpty() && !productSearch.getText().trim().matches("[0-9]*")){
            productTableView.setItems(Inventory.lookupProduct(productSearch.getText().trim()));
            productTableView.refresh();
        }
        else if (productSearch.getText().trim().matches("[0-9]*")){
            if (Inventory.lookupProduct(Integer.parseInt(productSearch.getText().trim())) != null){
                Product product = Inventory.lookupProduct(Integer.parseInt(productSearch.getText().trim()));
                if (Inventory.lookupProduct(product.getName()) != null){
                    productTableView.setItems(Inventory.lookupProduct(product.getName()));
                    productTableView.refresh();
                }
            }

        }
    }

    /**Exit program. Exits the program.
     * @param actionEvent when the exit button is pressed.*/
    public void exitProgram(ActionEvent actionEvent) {
        Platform.exit();
    }

    /**Update part table view. This updates the parts table view.*/
    public void updatePartTableView(){
        partTableView.setItems(Inventory.getAllParts());
    }

    /**Update product table view. This updates the product table view.*/
    public void updateProductTableView(){
        productTableView.setItems(Inventory.getAllProducts());
    }
}

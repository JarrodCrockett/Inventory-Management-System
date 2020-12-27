package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**AddPartController class. This creates a part controller class to control the add part screen.*/
public class AddPartController implements Initializable{

    Stage stage;
    Parent scene;


    @FXML
    private RadioButton inHouseRadio;

    @FXML
    private ToggleGroup source;

    @FXML
    private RadioButton outsourcedRadio;

    @FXML
    private Label sourceOfPart;

    @FXML
    private TextField IDTextBox;

    @FXML
    private TextField nameTextBox;

    @FXML
    private TextField invTextBox;

    @FXML
    private TextField priceTextBox;

    @FXML
    private TextField maxTextBox;

    @FXML
    private TextField machineIDCompanyNameTextBox;

    @FXML
    private TextField minTextBox;

    public AddPartController() {

    }

    /**Initialize method. Used to initialize the class.
     * @param url the location of the fxml.
     * @param resourceBundle the functions used in the class.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generatedID(Inventory.getAllParts());
        outsourcedRadio.setSelected(true);
    }

    /**In house selected. Used to activate the in house radio button.
     * @param event by clicking the button.*/
    @FXML
    void inHouseSelected(ActionEvent event) {
        if (this.source.getSelectedToggle().equals(inHouseRadio)){
            this.sourceOfPart.setText("Machine ID");
        }
    }

    /**Outsourced selected. Used to select the outsourced radio button.
     * @param event used by clicking the button.*/
    @FXML
    void outsourcedSelected(ActionEvent event) {
        if (this.source.getSelectedToggle().equals(outsourcedRadio)){
            this.sourceOfPart.setText("Company Name");
        }
    }

    /**Save part. Used to save the part that has been newly added.
     * @param actionEvent by clicking the save button.*/
    @FXML
    void savePart(ActionEvent actionEvent) {
        //Get all Text fields from the user interface also added casting to correct data types.
        if (!partValidation()){
            return;
        }
        if (inHouseRadio.isSelected()){
            addInHouse(actionEvent);
        }
        else if (outsourcedRadio.isSelected()){
            addOutsourced(actionEvent);
        }
        else {
            AlertMessage.partError(2);
        }
    }

    /**Add outsourced. Saves and outsourced part if the part is selected as outsourced.
     * @param actionEvent is the button being clicked.*/
    private void addOutsourced(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(IDTextBox.getText().trim());
            String name = nameTextBox.getText().trim();
            int inv = Integer.parseInt(invTextBox.getText().trim());
            double price = Double.parseDouble(priceTextBox.getText().trim());
            int max = Integer.parseInt(maxTextBox.getText().trim());
            int min = Integer.parseInt(minTextBox.getText().trim());
            String company = machineIDCompanyNameTextBox.getText().trim();
            Inventory.addPart(new Outsourced(id, name, price, inv, min, max, company));
        }
        catch (ClassCastException e){
            e.printStackTrace();
        }
        AlertMessage.partAdded();
        loadMainScreen(actionEvent);
    }

    /**Add in house. Adds a new in house part if the in house radio is selected.
     * @param actionEvent button being clicked.*/
    private void addInHouse(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(IDTextBox.getText().trim());
            String name = nameTextBox.getText().trim();
            int inv = Integer.parseInt(invTextBox.getText().trim());
            double price = Double.parseDouble(priceTextBox.getText().trim());
            int max = Integer.parseInt(maxTextBox.getText().trim());
            int min = Integer.parseInt(minTextBox.getText().trim());
            int machineID = Integer.parseInt(machineIDCompanyNameTextBox.getText().trim());
            Inventory.addPart(new InHouse(id, name, price, inv, min, max, machineID));
        }
        catch (ClassCastException e){
            e.printStackTrace();
        }
        AlertMessage.partAdded();
        loadMainScreen(actionEvent);
    }

    /**Part validation. Checks all the text boxes of the part and validates correct and completeness.
     * @return boolean true if the part validates.*/
    private boolean partValidation(){
        if (inHouseRadio.isSelected() || outsourcedRadio.isSelected()) {
            if (nameTextBox.getText().trim().isEmpty()
                    || priceTextBox.getText().trim().isEmpty()
                    || machineIDCompanyNameTextBox.getText().trim().isEmpty()) {
                AlertMessage.partError(1);
                return false;
            }
            if (invTextBox.getText().trim().isEmpty()){
                invTextBox.setText("0");
            }
            if (maxTextBox.getText().trim().isEmpty()){
                maxTextBox.setText("1");
            }
            if (minTextBox.getText().trim().isEmpty()){
                minTextBox.setText("0");
            }
            if (!invTextBox.getText().trim().matches("[0-9]*")
                    || !minTextBox.getText().trim().matches("[0-9]*")
                    || !maxTextBox.getText().trim().matches("[0-9]*")) {
                AlertMessage.partError(3);
                return false;
            }
            if (!priceTextBox.getText().trim().matches("^(0|[1-9][0-9]*)(\\.[0-9]+)?$")) {
                AlertMessage.partError(7);
                return false;
            }
            if (Integer.parseInt(minTextBox.getText()) > Integer.parseInt(maxTextBox.getText().trim())) {
                AlertMessage.partError(5);
                return false;
            }
            if (Integer.parseInt(invTextBox.getText().trim()) < Integer.parseInt(minTextBox.getText().trim())
                    || Integer.parseInt(invTextBox.getText().trim()) > Integer.parseInt(maxTextBox.getText().trim())) {
                AlertMessage.partError(6);
                return false;
            }
            if (inHouseRadio.isSelected() && !machineIDCompanyNameTextBox.getText().trim().matches("[0-9]*")) {
                AlertMessage.partError(4);
                return false;
            }
        }
        return true;
    }

    /**Generated id. This generates an Id every time a new part is created. It uses the count of the current list.
     * @param partsList is used to generate the id.*/
    private void generatedID(ObservableList<Part> partsList){
        int generatedID = partsList.size() + 1;
        IDTextBox.setText(Integer.toString(generatedID));
    }

    /**Cancel adding part. This cancels the part from being added and returns to the main screen.
     * @param actionEvent pushing the cancel button.*/
    @FXML
    void cancelAddingPart(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Part");
        alert.setHeaderText("Cancelling");
        alert.setContentText("You did not add a new part.");
        alert.showAndWait();

        loadMainScreen(actionEvent);


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
}

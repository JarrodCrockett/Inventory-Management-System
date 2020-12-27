package Main;


import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class Main.java
 */

/**
 *
 * @author Jarrod Crockett
 */
public class Main extends Application {


    /** Start method. Starts to main screen of the application. Also extends the Application class in javafx.
     * @param primaryStage
     * */
    @Override
    public void start(Stage primaryStage) throws Exception{

        addTestData();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../View_Controller/MainScreen.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(scene);

        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /** Add test data method. This allows me to add test data to the application to work with inside the application.*/
    public void addTestData(){
        //This Adds Test Data to See when the Program starts
        // In House Parts are added first
        Part oilFilter = new InHouse(1, "Oil Filter", 8.99, 13,5,20,101);
        Part drainPlug = new InHouse(2, "Drain Plug", 1.20, 40,25,100,102);
        Part crushWasher = new InHouse(3, "Crush Washer", 0.89, 120,50,300,103);
        Inventory.addPart(oilFilter);
        Inventory.addPart(drainPlug);
        Inventory.addPart(crushWasher);
        Inventory.addPart(new InHouse(4,"Tire Rotation", 13.50, 0,0,0,104));
        Inventory.addPart(new InHouse(5,"Alignment", 25.66, 0,0,0,105));

        // Out Sourced Parts are added second
        Part airFilter = new Outsourced(6,"Air Filter", 21.99, 8,5,20,"NAPA");
        Part pollenFilter = new Outsourced(7,"Pollen Filter", 31.00, 6,5,15,"NAPA");
        Part bulkOil = new Outsourced(8,"Bulk Oil", 9.99, 52,30,150,"Castrol");
        Part syntheticOil =new Outsourced(9,"Synthetic Oil", 12.80, 41,30,100,"Syntec");
        Part transmissionFluid =new Outsourced(10,"Transmission Fluid", 14.50, 8,5,20,"Syntec");
        Inventory.addPart(airFilter);
        Inventory.addPart(pollenFilter);
        Inventory.addPart(bulkOil);
        Inventory.addPart(syntheticOil);
        Inventory.addPart(transmissionFluid);

        // Adding all Products, These are listed as package deals with the listed parts
        Product basicOilChange = new Product(100, "Basic Oil Change", 37.50, 12,5,21);
        basicOilChange.addAssociatedPart(oilFilter);
        basicOilChange.addAssociatedPart(drainPlug);
        basicOilChange.addAssociatedPart(crushWasher);
        basicOilChange.addAssociatedPart(bulkOil);

        Product syntheticPackage = new Product(101, "Synthetic Oil Change", 49.50,8,4,15);
        syntheticPackage.addAssociatedPart(oilFilter);
        syntheticPackage.addAssociatedPart(drainPlug);
        syntheticPackage.addAssociatedPart(crushWasher);
        syntheticPackage.addAssociatedPart(syntheticOil);
        syntheticPackage.addAssociatedPart(airFilter);

        Product goldPackage = new Product(103, "Gold Package", 97.99, 4,0,9);
        goldPackage.addAssociatedPart(oilFilter);
        goldPackage.addAssociatedPart(drainPlug);
        goldPackage.addAssociatedPart(crushWasher);
        goldPackage.addAssociatedPart(syntheticOil);
        goldPackage.addAssociatedPart(airFilter);
        goldPackage.addAssociatedPart(transmissionFluid);

        Inventory.addProduct(basicOilChange);
        Inventory.addProduct(syntheticPackage);
        Inventory.addProduct(goldPackage);

        Inventory.addProduct(new Product(104,"Tire Rotation",14.99, 2,0,4));
        Inventory.addProduct(new Product(105,"Alignment",265.99, 0,0,4));

    }

    /**Main method. Launches to application and calls the start method.*/
    public static void main(String[] args) {
        launch(args);
    }
}

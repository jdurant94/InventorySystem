/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;
import model.Product;

/**
 *
 * @author jdura
 */
public class InventorySystem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Part partA1 = new Outsourced(1, "part a1", 10.99, 7, 0, 30, "Acme");
        Part partA2 = new Outsourced(2, "part a2", 12.99, 4, 10, 30, "ABC");
        
        
        Part partB1 = new InHouse(3, "part b1", 17.99, 4, 10, 30, 3181);
        Part partB2 = new Outsourced(4, "part b2", 62.00, 1, 1, 15, "Big Company");
        
        Product productA = new Product(1, "Product A", 42.99, 5, 1, 10);
        productA.addAssociatedPart(partA1);
        productA.addAssociatedPart(partA2);
        
        Product productB = new Product(2, "Product B", 69.99, 10, 5, 20);
        productB.addAssociatedPart(partB1);
        productB.addAssociatedPart(partB2);
        
        Inventory.addPart(partA1);
        Inventory.addPart(partA2);
        Inventory.addPart(partB1);
        Inventory.addPart(partB2);
        
        Inventory.addProduct(productA);
        Inventory.addProduct(productB);
        
        //Inventory.updatePart(2, partB2);
        
   
        launch(args);
        
    
        
        
        
    }
    
}

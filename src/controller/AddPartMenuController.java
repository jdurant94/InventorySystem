/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import static model.Inventory.getAllParts;
import model.Outsourced;

/**
 * FXML Controller class
 *
 * @author jdura
 */
public class AddPartMenuController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private GridPane addPartGrid;
    @FXML
    private Label varLbl;
    @FXML
    private TextField partNameTxt;
    @FXML
    private TextField invTxt;
    @FXML
    private TextField priceTxt;
    @FXML
    private TextField varTxt;
    @FXML
    private TextField maxTxt;
    @FXML
    private TextField minTxt;
    @FXML
    private RadioButton inHouseBtn;
    @FXML
    private ToggleGroup typeTgl;
    @FXML
    private RadioButton outsourcedBtn;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    
    @FXML
    void onActionSetInHouse(ActionEvent event) {
        
        if (inHouseBtn.isArmed())
        {
            varLbl.setText("Machine ID");
            varTxt.setPromptText("Mach ID");
        }
            

    }

    @FXML
    void onActionSetOutsourced(ActionEvent event) {
        
       if (outsourcedBtn.isArmed())
        {
            varLbl.setText("Company Name");
            varTxt.setPromptText("Company");
        }

    }
    
    @FXML
    private void savePart(javafx.scene.input.MouseEvent event) throws IOException {
        
        if (inHouseBtn.isSelected())
        {
        int id = Inventory.getAllParts().size() + 1;
        String name = partNameTxt.getText();
        double price = Double.parseDouble(priceTxt.getText());
        int stock = Integer.parseInt(invTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        int machineID = Integer.parseInt(varTxt.getText());
        
        Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineID));
        }
        
        if(outsourcedBtn.isSelected())
        {
        int id = Inventory.getAllParts().size() + 1;
        String name = partNameTxt.getText();
        double price = Double.parseDouble(priceTxt.getText());
        int stock = Integer.parseInt(invTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        String companyName = varTxt.getText();
        
        Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName)); 
        }
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
    }


    @FXML
    private void cancel(javafx.scene.input.MouseEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Any data entered will be lost.");
        alert.setContentText("Are you sure you want to continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show(); 
        }
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    

}

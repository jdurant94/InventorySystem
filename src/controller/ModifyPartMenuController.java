/* 
 * 
 * 
 * 
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

/**
 * FXML Controller class
 *
 * @author Jordan Durant
 */
public class ModifyPartMenuController implements Initializable {

    Stage stage;
    Parent scene;
    
    @FXML
    private GridPane addPartGrid;
    @FXML
    private Label varLbl;
    @FXML
    private TextField partIDTxt;
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
    private Button savePartButton;
    @FXML
    private Button cancelButton;
    Part part;

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
    private void savePart(MouseEvent event) throws IOException {
        
        
        if (inHouseBtn.isSelected())
        {
        int id = Integer.parseInt(partIDTxt.getText());
        String name = partNameTxt.getText();
        double price = Double.parseDouble(priceTxt.getText());
        int stock = Integer.parseInt(invTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        int machineID = Integer.parseInt(varTxt.getText());
        
        Part newPart = new InHouse(id, name, price, stock, min, max, machineID);
        
        Inventory.addPart(newPart);
        }
        
        if (outsourcedBtn.isSelected())
        {
        int id = Integer.parseInt(partIDTxt.getText());
        String name = partNameTxt.getText();
        double price = Double.parseDouble(priceTxt.getText());
        int stock = Integer.parseInt(invTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        String companyName = varTxt.getText();
        
        Part newPart = new Outsourced(id, name, price, stock, min, max, companyName);
        
        Inventory.addPart(newPart);
        }
      
        Inventory.deletePart(part);
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
    }
        
        
    public void setPart(Part part) throws IOException {
        this.part = part;
        
        if(part instanceof InHouse){
        
        
        inHouseBtn.fire();
        
        varLbl.setText("Machine ID");
        partIDTxt.setText(Integer.toString(part.getId()));
        partNameTxt.setText(part.getName());
        priceTxt.setText(Double.toString(part.getId()));
        invTxt.setText(Integer.toString(part.getStock()));
        minTxt.setText(Integer.toString(part.getMin()));
        maxTxt.setText(Integer.toString(part.getMax()));
        varTxt.setText(Integer.toString((((InHouse) part).getMachineID())));
        }
        
        if(part instanceof Outsourced){
        
        
        outsourcedBtn.fire();
        
        varLbl.setText("Company Name");
        partIDTxt.setText(Integer.toString(part.getId()));
        partNameTxt.setText(part.getName());
        priceTxt.setText(Double.toString(part.getId()));
        invTxt.setText(Integer.toString(part.getStock()));
        minTxt.setText(Integer.toString(part.getMin()));
        maxTxt.setText(Integer.toString(part.getMax()));
        varTxt.setText(((Outsourced) part).getCompanyName());
        }
        
        
    }    

    @FXML
    private void cancel(MouseEvent event) throws IOException {
        
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

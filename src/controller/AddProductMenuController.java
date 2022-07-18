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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

/**
 * FXML Controller class
 *
 * @author Jordan Durant
 */
public class AddProductMenuController implements Initializable {
    
    Stage stage;
    Parent scene;

    @FXML
    private GridPane addPartGrid;
    @FXML
    private TextField productNameTxt;
    @FXML
    private TextField invTxt;
    @FXML
    private TextField priceTxt;
    @FXML
    private TextField maxTxt;
    @FXML
    private TextField minTxt;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Part> associatedPartsTblView;
    @FXML
    private Button addButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchTxt;
    @FXML
    private TableView<Part> allPartsTblView;
    @FXML
    private TableColumn<Part, Integer> associatedPartIDCol;
    @FXML
    private TableColumn<Part, String> associatedPartNameCol;
    @FXML
    private TableColumn<Part, Integer> associatedInventoryLvlCol;
    @FXML
    private TableColumn<Part, Double> associatedPricePerUnitCol;
    @FXML
    private TableColumn<Part, Integer> allPartIDCol;
    @FXML
    private TableColumn<Part, String> allPartNameCol;
    @FXML
    private TableColumn<Part, Integer> allInventoryLvlCol;
    @FXML
    private TableColumn<Part, Double> allPricePerUnitCol;
    
    public ObservableList<Part> transferData=FXCollections.observableArrayList();
    
    
    @FXML
    private void saveProduct(MouseEvent event) throws IOException {
        
        
        int id = Inventory.getAllProducts().size() + 1;
        String name = productNameTxt.getText();
        double price = Double.parseDouble(priceTxt.getText());
        int stock = Integer.parseInt(invTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        
        Product product = new Product(id, name, price, stock, min, max);
        for (Part part : transferData)
        {
            product.addAssociatedPart(part);
        }
        
        if (product.getAllAssociatedParts().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Associated Parts");
            alert.setHeaderText("No parts associated with " + product.getName() + "!");
            alert.setContentText("Please add at least one part to " + product.getName() + "!");
            
            alert.showAndWait();
            
        } else {
            
        Inventory.addProduct(product);
        
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
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

    @FXML
    private void deletePartFromAssociatedParts(MouseEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("This part will be deleted from this product");
        alert.setContentText("Are you sure you want to continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            transferData.remove(associatedPartsTblView.getSelectionModel().getSelectedItem());
        }
        
        
    }

    @FXML
    private void addPartToAssociatedParts(MouseEvent event) {
       
        
        transferData = associatedPartsTblView.getItems();
            transferData.add(allPartsTblView.getSelectionModel().getSelectedItem()); 
                    associatedPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                    associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                    associatedInventoryLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                    associatedPricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

                associatedPartsTblView.setItems(transferData);
        
        
    }

    @FXML
    private void search(MouseEvent event) {
        
        String searchItem = searchTxt.getText();
        if (searchItem.equals("")){
            allPartsTblView.setItems(Inventory.getAllParts());               
        } else {
        
        boolean found = false;

            try{
            int itemNumber = Integer.parseInt(searchTxt.getText());
            for(Part part: Inventory.getAllParts()){
                if(part.getId() == itemNumber){
                    
                    found=true;

                    transferData.clear();
                    transferData.add(part);

                    allPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                    allPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                    allInventoryLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                    allPricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));


                    allPartsTblView.setItems(transferData);

                }
                
            }
                if (found == false){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Error!");
                alert.setContentText("Part does not exist");

                alert.showAndWait();
            }
        }
        catch(NumberFormatException e){
            for(Part part: Inventory.getAllParts()){
                if(part.getName().equals(searchItem)){
                    
                    found=true;

                   transferData.clear();
                   transferData.add(part);

                    allPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                    allPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                    allInventoryLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                    allPricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));


                    allPartsTblView.setItems(transferData);
                }

            }
                if (found==false){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Error");
                alert.setContentText("Part does not exist!");

                alert.showAndWait();
            }
        }
    }    
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        allPartsTblView.setItems(Inventory.getAllParts());
        
        allPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        allInventoryLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
    }    
}

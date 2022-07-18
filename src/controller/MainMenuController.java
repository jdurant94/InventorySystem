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
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

/**
 * FXML Controller class
 *
 * @author Jordan Durant
 */
public class MainMenuController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    public ObservableList<Part> transferPartData=FXCollections.observableArrayList();
    public ObservableList<Product> transferProductData=FXCollections.observableArrayList();

    @FXML
    private TableView<Part> partTblView;
    @FXML
    private Button addPartButton;
    @FXML
    private Button modifyPartButton;
    @FXML
    private Button deletePartButton;
    @FXML
    private Button searchPartButton;
    @FXML
    private TableView<Product> productTblView;
    @FXML
    private Button addProductButton;
    @FXML
    private Button modifyProductButton;
    @FXML
    private Button deleteProductButton;
    @FXML
    private Button searchProductButton;
    @FXML
    private Button exitButton;
    @FXML
    private TableColumn<Part, Integer> partIDCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Product, Integer> productIDCol;
    @FXML
    private TableColumn<Product, String> productNameCol;
    @FXML
    private TableColumn<Part, Integer> partInventoryLvlCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    @FXML
    private TableColumn<Product, Integer> productInventoryLvlCol;
    @FXML
    private TableColumn<Product, Double> productPriceCol;
    @FXML
    private TextField searchPartTxt;
    @FXML
    private TextField searchProductTxt;

    @FXML
    private void toAddPartMenu(MouseEvent event) throws IOException {
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
    }

    @FXML
    private void toModifyPartMenu(MouseEvent event) throws IOException {
              
        stage=(Stage) modifyPartButton.getScene().getWindow();
        FXMLLoader loader=new FXMLLoader(getClass().getResource(
               "/view/ModifyPart.fxml"));
        scene =loader.load();
        Scene newScene = new Scene(scene);
        stage.setScene(newScene);
        stage.show();
        ModifyPartMenuController controller = loader.getController();
        Part part=partTblView.getSelectionModel().getSelectedItem();
        controller.setPart(part);
        
        /*stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();*/
        
    }
    
    @FXML
    private void toAddProductMenu(MouseEvent event) throws IOException {
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    @FXML
    private void toModifyProductMenu(MouseEvent event) throws IOException {
        
        stage=(Stage) modifyProductButton.getScene().getWindow();
        FXMLLoader loader=new FXMLLoader(getClass().getResource(
               "/view/ModifyProduct.fxml"));
        scene =loader.load();
        Scene newScene = new Scene(scene);
        stage.setScene(newScene);
        stage.show();
        ModifyProductMenuController controller = loader.getController();
        Product product = productTblView.getSelectionModel().getSelectedItem();
        controller.setProduct(product);
        
        
        /*stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();*/
              
    }
    
    @FXML
    private void searchPart(MouseEvent event) {
        
        String searchItem = searchPartTxt.getText();
        if (searchItem.equals("")){
            partTblView.setItems(Inventory.getAllParts());               
        } else {
        
        boolean found = false;

            try{
            int itemNumber = Integer.parseInt(searchPartTxt.getText());
            for(Part part: Inventory.getAllParts()){
                if(part.getId() == itemNumber){
                    
                    found=true;

                    transferPartData.clear();
                    transferPartData.add(part);

                    partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                    partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                    partInventoryLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                    partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


                    partTblView.setItems(transferPartData);

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

                    transferPartData.clear();
                    transferPartData.add(part);

                    partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                    partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                    partInventoryLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                    partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
                    
                    partTblView.setItems(transferPartData);
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

    @FXML
    private void searchProduct(MouseEvent event) {
        
        String searchItem = searchProductTxt.getText();
        if (searchItem.equals("")){
            productTblView.setItems(Inventory.getAllProducts());               
        } else {
        
        boolean found = false;

            try{
            int itemNumber = Integer.parseInt(searchProductTxt.getText());
            for(Product product : Inventory.getAllProducts()){
                if(product.getId() == itemNumber){
                    
                    found=true;

                    transferProductData.clear();
                    transferProductData.add(product);

                    partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                    partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                    partInventoryLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                    partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


                    productTblView.setItems(transferProductData);

                }
                
            }
                if (found == false){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Error!");
                alert.setContentText("Product does not exist");

                alert.showAndWait();
            }
        }
        catch(NumberFormatException e){
            for(Product product: Inventory.getAllProducts()){
                if(product.getName().equals(searchItem)){
                    
                    found=true;

                    transferProductData.clear();
                    transferProductData.add(product);

                    productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                    productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                    productInventoryLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                    productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
                    
                    productTblView.setItems(transferProductData);
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
    
    @FXML
    private void deleteProduct(MouseEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("This product will be deleted from the product list");
        alert.setContentText("Are you sure you want to continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            Inventory.deleteProduct(productTblView.getSelectionModel().getSelectedItem());
        }
        
    }
        
    @FXML
    private void deletePart(MouseEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("This part will be deleted from the part list");
        alert.setContentText("Are you sure you want to continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
             Inventory.deletePart(partTblView.getSelectionModel().getSelectedItem());
        }
        
    }
    
    @FXML
    private void exitProgram(MouseEvent event) {
        
        System.exit(0);
        
    }
    
    /**
     * Initializes the controller class.
     */
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        partTblView.setItems(Inventory.getAllParts());
        
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        productTblView.setItems(Inventory.getAllProducts());
        
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
    }

    


}

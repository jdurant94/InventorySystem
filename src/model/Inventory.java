/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jdura
 */
public class Inventory {
    
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    
    public static void addPart(Part newPart)
    {
        allParts.add(newPart);
    }
    
    public static void addProduct(Product newProduct)
    {
        allProducts.add(newProduct);
    }
    
    public static Part lookupPart(int partID)
    {
        for (Part part : getAllParts())
        {
            if (part.getId() == partID)
            {
                return part;
            }            
        }    
        return null;
    }
    
    public static ObservableList<Part> lookupPart(String partName)
    {
        return null;       
    }
    
    public static Product lookupProduct(int productID)
    {
        return null;
    }
    
    public static ObservableList<Product> lookupProduct(String productName)
    {
        return null;
    }
    
    public static void updatePart(int index, Part selectedPart)
    {
        
        allParts.set(index, selectedPart);
        
    }
    
    public static void updateProduct(int index, Product selectedProduct)
    {
        allProducts.set(index, selectedProduct);
    }
    
    public static void deletePart(Part selectedPart)
    {
        allParts.remove(selectedPart);
    }
    
    public static void deleteProduct(Product selectedProduct)
    {
        allProducts.remove(selectedProduct);
    }

}

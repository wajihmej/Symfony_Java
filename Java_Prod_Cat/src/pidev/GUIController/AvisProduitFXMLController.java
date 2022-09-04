/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.GUIController;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;
import pidev.Entities.Categorie;
import pidev.Entities.Produit;
import pidev.Services.ServiceCategorie;
import pidev.Services.ServiceProduit;
import pidev.Utils.Database;

/**
 * FXML Controller class
 *
 * @author Wajih
 */
public class AvisProduitFXMLController implements Initializable {

    @FXML
    private TableView<Produit> tableProduit;
    @FXML
    private TableColumn<Produit, Integer> idt;
    @FXML
    private TableColumn<Produit, Float> prixt;
    @FXML
    private TableColumn<Produit, String> nomt;
    @FXML
    private TableColumn<Produit, ImageView> imaget;
    @FXML
    private TableColumn<Produit, String> categoriet;
    @FXML
    private TableColumn<Produit, Double> ratingt;
    @FXML
    private Rating stars;

    private Statement ste;
    private Connection con;
    private final ObservableList<Produit> data = FXCollections.observableArrayList();
    ServiceCategorie sc = new ServiceCategorie();
    ServiceProduit sp = new ServiceProduit();
    @FXML
    private Label idlabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Aff();
        stars.setRating(0);
    }  
    
        public void Aff(){
                                try {
            con = Database.getInstance().getConnection();
            ste = con.createStatement();
            data.clear();

            ResultSet rs = ste.executeQuery("select * from produit");
            while(rs.next()){
                Produit f = new Produit(rs.getInt(1), rs.getInt(2),rs.getString(3), rs.getFloat(4), rs.getDouble(5), rs.getString(6));
                                
                File file = new File("C:\\Users\\Wajih\\Desktop\\Prod_Cat\\public\\uploads\\images\\"+rs.getString(6));
                Image image = new Image(file.toURI().toString());
                
                ImageView imageView =new ImageView(image);
                imageView.setImage(image);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);

                f.setImg(imageView);                

                Categorie d = sc.getById(f.getId_categorie());
                f.setCategorie(d.getNom());
                data.add(f);
            }
        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
            idt.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomt.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prixt.setCellValueFactory(new PropertyValueFactory<>("prix"));
            imaget.setCellValueFactory(new PropertyValueFactory<>("img"));
            categoriet.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            ratingt.setCellValueFactory(new PropertyValueFactory<>("rating"));
            tableProduit.setItems(data);

    }

    @FXML
    private void btnavis(ActionEvent event) throws SQLException {
        if(idlabel.getText()!="")
        {
        Produit r = new Produit(Integer.valueOf(idlabel.getText()),stars.getRating());
        sp.updaterate(r);
        Aff();
        stars.setRating(0);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Avis");
        alert.setHeaderText(null);
        alert.setContentText("Avis donner!");

        alert.showAndWait();

        }
        else
        {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Selectionner un produit!");

        alert.showAndWait();
        }
        idlabel.setText("");
    }

    @FXML
    private void LoadModif(MouseEvent event) {
             ObservableList<Produit> allrelationnels,Singlerelationnel ;
             allrelationnels=tableProduit.getItems();
             Singlerelationnel=tableProduit.getSelectionModel().getSelectedItems();
             Produit A = Singlerelationnel.get(0);
             
             stars.setRating(A.getRating());
             idlabel.setText(String.valueOf(A.getId()));

    }
    
}

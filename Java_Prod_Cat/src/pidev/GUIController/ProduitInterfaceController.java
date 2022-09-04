/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.GUIController;

import pidev.Entities.Categorie;
import pidev.Entities.Pdf;
import pidev.Entities.Produit;
import pidev.Services.ServiceCategorie;
import pidev.Services.ServiceProduit;
import pidev.Utils.Database;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ProduitInterfaceController implements Initializable {

    @FXML
    private TextField affiche;
    @FXML
    private Button uploadbutton;
    @FXML
    private ComboBox<String> ComboCategorie;
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

    private Statement ste;
    private Connection con;
    private final ObservableList<Produit> data = FXCollections.observableArrayList();

    ServiceCategorie sc = new ServiceCategorie();
    ServiceProduit sp = new ServiceProduit();
    ObservableList<String> listRole = FXCollections.observableArrayList();
    
    @FXML
    private TextField recherche;
    @FXML
    private TextField nomtxt;
    @FXML
    private Label idlabel;
    @FXML
    private TableColumn<Produit, Double> ratingt;
    @FXML
    private TextField prixtxt;
    @FXML
    private Label filepath;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            fillcombo();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Aff();
        RechercheAV();
        idlabel.setVisible(false);
        nomtxt.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
         nomtxt.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });

    }    
    
    public void fillcombo() throws SQLException{     
        List<Categorie> list = sc.readAll();
        for (Categorie aux : list)
        {
          listRole.add(aux.getNom());
        }
        ComboCategorie.setItems(listRole);
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
    public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Produit> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(relation -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (relation.getCategorie().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(relation.getNom()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Produit> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableProduit.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableProduit.setItems(sortedData);
    }

    @FXML
    private void Uploadfile(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(uploadbutton.getScene().getWindow());

        affiche.setText(file.getName());
        filepath.setText(file.getPath());

    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException, IOException {
                
        Categorie cat= sc.getByName(ComboCategorie.getValue());

        if(Validchamp(nomtxt))
        {
        if(filepath.getText()!="")
        {
         File f = new File(filepath.getText());
         Produit r = new Produit(nomtxt.getText(), Float.parseFloat(prixtxt.getText()),affiche.getText(), cat.getId(),0);
         sp.ajouter(r);
         Files.copy(Paths.get(filepath.getText()),Paths.get("C:\\Users\\Wajih\\Desktop\\Prod_Cat\\public\\uploads\\images\\"+f.getName()),REPLACE_EXISTING);
        }
        else
        {
         Produit r = new Produit(nomtxt.getText(), Float.parseFloat(prixtxt.getText()),"NoImage.png", cat.getId(),0);
         sp.ajouter(r);
        }
   
        Aff();
        RechercheAV();
             nomtxt.setText("");
             prixtxt.setText("");
             affiche.setText("");
             ComboCategorie.setValue("");
             filepath.setText("");
             idlabel.setText("");

        TrayNotification tray = new TrayNotification();
        tray.setTitle("Ajout");
        tray.setMessage("Ajouté avec succès");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndWait();
        }
        else
        {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Verifiez les champs!");

        alert.showAndWait();
        }
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
             tableProduit.setItems(data);

             ObservableList<Produit> allrelationnels,Singlerelationnel ;
             allrelationnels=tableProduit.getItems();
             Singlerelationnel=tableProduit.getSelectionModel().getSelectedItems();
             Produit A = Singlerelationnel.get(0);
             sp.delete(A);
             Singlerelationnel.forEach(allrelationnels::remove);
             Aff();
             RechercheAV();
             TrayNotification tray = new TrayNotification();
        tray.setTitle("Supprimer");
        tray.setMessage("Supprimé avec succès");
        tray.setNotificationType(NotificationType.ERROR);
        tray.showAndWait();
    }

    @FXML
    private void Modifier(ActionEvent event) throws SQLException, IOException {
                    Categorie cat= sc.getByName(ComboCategorie.getValue());

        if(Validchamp(nomtxt))
        {
        if(filepath.getText()!="")
        {
         File f = new File(filepath.getText());
                     float tmp = Float.parseFloat(prixtxt.getText());
                    System.out.println(tmp);

        Produit r = new Produit(Integer.valueOf(idlabel.getText()), cat.getId(),nomtxt.getText(), tmp,0,affiche.getText());
        sp.update(r);
        Files.copy(Paths.get(filepath.getText()),Paths.get("C:\\Users\\Wajih\\Desktop\\Prod_Cat\\public\\uploads\\images\\"+f.getName()),REPLACE_EXISTING);
        }
        else
        {
            float tmp = Float.parseFloat(prixtxt.getText());
                    System.out.println(tmp);

         Produit r = new Produit(Integer.valueOf(idlabel.getText()), cat.getId(),nomtxt.getText(), tmp,0,affiche.getText());
         sp.update(r);
        }
   
        Aff();
        RechercheAV();
             nomtxt.setText("");
             prixtxt.setText("");
             affiche.setText("");
             ComboCategorie.setValue("");
             filepath.setText("");
             idlabel.setText("");


        TrayNotification tray = new TrayNotification();
        tray.setTitle("Modifier");
        tray.setMessage("Modifié avec succès");
        tray.setNotificationType(NotificationType.WARNING);
        tray.showAndWait();
        }
        else
        {
            
        }        
    }
    
    
    private boolean Validchamp(TextField T){
        return !T.getText().isEmpty() && T.getLength() > 2;
    }

    @FXML
    private void LoadModif(MouseEvent event) {
             ObservableList<Produit> allrelationnels,Singlerelationnel ;
             allrelationnels=tableProduit.getItems();
             Singlerelationnel=tableProduit.getSelectionModel().getSelectedItems();
             Produit A = Singlerelationnel.get(0);
             
             nomtxt.setText(A.getNom());
             prixtxt.setText(String.valueOf(A.getPrix()));
             affiche.setText(A.getImage());
             ComboCategorie.setValue(A.getCategorie());
             
             idlabel.setText(String.valueOf(A.getId()));
             filepath.setText("");
       
    }


    
}

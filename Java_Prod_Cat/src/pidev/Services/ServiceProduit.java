/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Services;

import pidev.Entities.Produit;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.sql.PreparedStatement;
import pidev.Utils.Database;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author msi
 */
public class ServiceProduit implements IService<Produit> {

    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;

    public ServiceProduit() {
        con = Database.getInstance().getConnection();

    }

    @Override
    public void ajouter(Produit a) throws SQLException {
        try {
        PreparedStatement PS = con.prepareStatement("INSERT INTO `Produit` (`nom`, `prix`, `image`, `categorie_id`, `rate`) VALUES (?, ?, ?, ?, ? );");
        PS.setString(1, a.getNom());
        PS.setFloat(2, a.getPrix());
        PS.setString(3, a.getImage());
        PS.setInt(4, a.getId_categorie());
        PS.setDouble(5, a.getRating());
        PS.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @Override
    public void delete(Produit t) throws SQLException {
        try {
            String requete = " delete from Produit where id='"+t.getId()+"'" ;
            pst = con.prepareStatement(requete);
              ste=con.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public void update(Produit t) throws SQLException {
        try {
            String requete = " update Produit set nom=? , prix=? , image=? , categorie_id=?   where id='"+t.getId()+"'"  ;
            pst = con.prepareStatement(requete);
            pst.setString(1,t.getNom());
            pst.setFloat(2,t.getPrix());
            pst.setString(3,t.getImage());
            pst.setInt(4,t.getId_categorie());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void updaterate(Produit t) throws SQLException {
        try {
            String requete = " update Produit set rate=?   where id='"+t.getId()+"'"  ;
            pst = con.prepareStatement(requete);
            pst.setDouble(1,t.getRating());

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public List<Produit> readAll() throws SQLException {
    List<Produit> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Produit");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               float prix=rs.getFloat(3);
               String image=rs.getString("image");
               int idcategorie=rs.getInt(5);
               float rating=rs.getFloat(6);
               Produit p=new Produit(id, idcategorie,nom, prix,rating,image);
     arr.add(p);
     }
    return arr;
    }
    
    @Override
    public List<Produit> getTrier() throws SQLException {
    List<Produit> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Produit ORDER BY nom DESC");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               float prix=rs.getFloat(3);
               String image=rs.getString("image");
               int idcategorie=rs.getInt(5);
               float rating=rs.getFloat(6);
               Produit p=new Produit(id, idcategorie,nom, prix,rating,image);
     arr.add(p);
     }
    return arr;
    }

  public Produit getByName(String n) {
          Produit a = null;
         String requete = " select* from Produit  where (nom like '"+n+"%')" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Produit(res.getInt(1),res.getInt(2),res.getString(3),res.getFloat(4),res.getDouble(5),res.getString(6));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
    }


    public Produit getById(Produit f) {
          Produit a = null;
         String requete = " select* from Produit  where id='"+f.getId()+"'" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Produit(res.getInt(1),res.getInt(2),res.getString(3),res.getFloat(4),res.getDouble(5),res.getString(6));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
    }

}

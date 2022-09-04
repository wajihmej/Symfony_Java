/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author msi
 */
public class Produit {
    private int id;
    private String nom;
    private float prix;
    private String image;
    private ImageView img;
    private int id_categorie;
    private String categorie;
    private double rating;

    public Produit(int id, int id_categorie, String nom, float prix,  double rating, String image) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.image = image;
        this.id_categorie = id_categorie;
        this.rating = rating;
    }
    public Produit(int id, double rating) {
        this.id = id;
        this.rating = rating;
    }

    public Produit(String nom, float prix, String image, int id_categorie, double rating) {
        this.nom = nom;
        this.prix = prix;
        this.image = image;
        this.id_categorie = id_categorie;
        this.categorie = categorie;
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Relationnel{" + "id=" + id + ", nom=" + nom + ", prix=" + prix + ", image=" + image + ", id_categorie=" + id_categorie + ", categorie=" + categorie + '}';
    }

    


}

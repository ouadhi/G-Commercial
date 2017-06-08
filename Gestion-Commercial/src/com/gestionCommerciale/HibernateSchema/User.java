
package com.gestionCommerciale.HibernateSchema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", nullable = false)
    int idUser;
    @Column(name = "nom", nullable = false)
    String nom;
    //@Column(name = "prenom", nullable = false)
    //String prenom;
    @Column(name = "password", nullable = false)
    String password;
    @Column(name = "type", nullable = false)
    String type;
    @Column(name = "photo", nullable = true)
    String photoLien;
    @Column(name = "deleted", nullable = false)
    boolean deleted;
    
    public  static User userConnected  ;
            //= new User("admin", "admin", "administrateur") ;

    public User() {
    }

    public User(String nom, String password, String type) {
        this.nom = nom;
        this.password = password;
        this.type = type;
        this.deleted=false;
        //this.photoLien= photoLien;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhotoLien() {
        return photoLien;
    }

    public void setPhotoLien(String photoLien) {
        this.photoLien = photoLien;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public static User getUserConnected() {
        return userConnected;
    }

    

    public static void setUserConnected(User userConnected) {
        User.userConnected = userConnected;
    }
    
    public static boolean  isAdministrateur() {
        return userConnected.getType().equals("Administrateur");
    }
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionCommerciale.HibernateSchema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Hicham
 */
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
    @Column(name = "photo", nullable = false)
    String photoLien;

    public User() {
    }

    public User(String nom, String password, String type) {
        this.nom = nom;
        //this.prenom = prenom;
        this.password = password;
        this.type = type;
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

    /*public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }*/

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


}

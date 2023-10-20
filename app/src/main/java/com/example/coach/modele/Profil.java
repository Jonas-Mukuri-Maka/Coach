package com.example.coach.modele;

import java.io.Serializable;
import java.lang.Math;
import java.util.Date;

import com.example.coach.outils.Serializer;

public class Profil implements Serializable {

    // constantes
    private static final Integer minFemme = 15; // maigre si en dessous
    private static final Integer maxFemme = 30; // gros si au dessus
    private static final Integer minHomme = 10; // maigre si en dessous
    private static final Integer maxHomme = 25; // gros si au dessus

    //Proprietes d'un profil

    private int poids;

    private int taille;

    private int age;

    private int sexe;

    private float img;

    private String message = "";

    private Date dateMesure;

    public Profil(int poids, int taille, int age, int sexe, Date dateMesure) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.dateMesure = dateMesure;
    }

    public int getPoids() {
        return poids;
    }

    public int getTaille() {
        return taille;
    }

    public int getAge() {
        return age;
    }

    public int getSexe() {
        return sexe;
    }

    public float getImg() {
        if (img == 0) {
            img = (float) (1.2 * poids/(Math.pow(((float)taille/100), 2)) + (0.23 * age) + (10.83 * sexe) - 5.4);
        }
        return img;
    }

    public Date getDateMesure() {
        return dateMesure;
    }

    public String getMessage() {
        if (message == ""){
            message = "normal";
            img = getImg();
            switch (sexe){
                case (1):
                    if (minHomme > img){
                        message = "trop faible";
                    }
                    if (maxHomme < img){
                        message = "trop élevé";
                    }
                case (0):
                    if (minFemme > img){
                        message = "trop faible";
                    }
                    if (maxFemme < img){
                    message = "trop élevé";
                    }
            }
        }
        return message;
    }

}

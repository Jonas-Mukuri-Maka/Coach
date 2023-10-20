package com.example.coach.controleur;


import com.example.coach.outils.Serializer;

import android.content.Context;

import com.example.coach.modele.Profil;
import com.example.coach.vue.MainActivity;

public final class Controle {
    private static Controle instance = null;
    private static Profil profil;
    private static String nomfic = "saveprofil";

    private Controle(Context context) {
        recupSerialize(context);
    }

    public static final Controle getInstance(Context context){
        if (instance == null){
            instance = new Controle(context);
        }
        return instance;
    }

    /**
     *
     * @param poids
     * @param taille
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context context) {
        profil = new Profil(poids, taille, age, sexe);
        Serializer.serialize(nomfic, profil, context);
    }

    public float getImg(){
        if(profil != null) {
            return profil.getImg();
        }else{
            return 0;
        }
    }

    public String getMessage(){
        if(profil != null) {
            return profil.getMessage();
        }else{
            return "";
        }
    }
    public Integer getPoids(){
        if (profil == null){
            return null;
        }
        else{
            return profil.getPoids();
        }
    }
    public Integer getTaille(){
        if (profil == null){
            return null;
        }
        else{
            return profil.getTaille();
        }
    }
    public Integer getAge(){
        if (profil == null){
            return null;
        }
        else{
            return profil.getAge();
        }
    }
    public Integer getSexe(){
        if (profil == null){
            return null;
        }
        else{
            return profil.getSexe();
        }
    }

    private static void recupSerialize(Context context){
        profil = (Profil)Serializer.deSerialize(nomfic, context);
    }
}

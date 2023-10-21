package com.example.coach.controleur;


import com.example.coach.modele.AccesDistant;
import com.example.coach.modele.AccesLocal;
import com.example.coach.outils.Serializer;

import android.content.Context;

import com.example.coach.modele.Profil;
import com.example.coach.vue.MainActivity;

import org.json.JSONObject;

import java.util.Date;

public final class Controle {
    private static Controle instance = null;
    private static Profil profil;
    private static String nomfic = "saveprofil";

    private static Context context;

//  private AccesLocal accesLocal;

    private static AccesDistant accesDistant;

    private Controle(Context context) {
        /*
        recupSerialize(context);
         */
        /*
        accesLocal = AccesLocal.getInstance(context);
        profil = accesLocal.recupDernier();
         */
        if(context != null) {
            Controle.context = context;
        }

    }

    public static final Controle getInstance(Context context){
        if (instance == null){
            instance = new Controle(context);
            accesDistant = AccesDistant.getInstance();
            accesDistant.envoi("dernier", new JSONObject());
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
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe) {
        profil = new Profil(poids, taille, age, sexe, new Date());
        accesDistant.envoi("enreg", profil.convertToJSONObject());
        /*
        accesLocal.ajout(profil);
        Serializer.serialize(nomfic, profil, context);
         */
    }

    public void setProfil(Profil profil){
        Controle.profil = profil;
        ((MainActivity)context).recupProfil();
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

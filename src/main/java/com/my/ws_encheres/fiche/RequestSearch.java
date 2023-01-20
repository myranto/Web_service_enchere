package com.my.ws_encheres.fiche;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestSearch {
    private Date date_min=null;
    private Date date_max=null;
    private String keyWord="";
    private int[] categorie=null;
    private int status=-1;
    private double prix=-1;

    public Date getDate_min() {
        return date_min;
    }

    public void setDate_min(Date date_min) {
        this.date_min = date_min;
    }

    public Date getDate_max() {
        return date_max;
    }

    public void setDate_max(Date date_max) {
        this.date_max = date_max;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int[] getCategorie() {
        return categorie;
    }

    public void setCategorie(int[] categorie) {
        this.categorie = categorie;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }


    private String getConditionCatego(){
        String condition = "";
        if ((categorie == null) || (categorie.length<=0)) {
            return " 1=1 ";
        }
        condition = "(";
        for (int i=0;i<categorie.length;i++) {
            if (i==categorie.length-1){
                condition+="idcategorie="+categorie[i]+")";
                break;
            }
            condition+="idcategorie="+categorie[i]+" or ";
        }
        return condition;
    }
    private String getDateCondition(){

        if ((date_min == null) && (date_max==null)){
            return " 2=2 ";
        }
        if (date_min==null)
            return  " (date_enchere<='"+date_max+"') ";
        if (date_max==null)
            return  " (date_enchere>='"+date_min+"') ";
        return " ( date_enchere between '"+date_min+"' and '"+date_max+"' ) ";
    }
    public String createQuery(){
        String condP = (getPrix()==-1)?" 4=4 ":" (prix_vente <= "+getPrix()+" ) ";
        String condS = (getStatus()==-1)?" 5=5 ":" (status= "+getStatus()+" ) ";
        String sql = "select * from Enchere where upper(description) like upper('%"+getKeyWord()+"%') and 3=3 and "+condS+"and"+condP+" and "+getConditionCatego()+" and "+getDateCondition();
        System.out.println(sql);
        return sql;
    }
}

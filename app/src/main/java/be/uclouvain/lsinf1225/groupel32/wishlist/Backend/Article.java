//CLASSE ARTICLE PERMETTANT UNE MEILLEURE STRUCTURE DE CETTE DERNIÈRE.

package be.uclouvain.lsinf1225.groupel32.wishlist.Backend;

public class Article {
    private int id;
    private String designation;
    private double prix;
    private double qte;

    public Article(int id, String designation, double prix, double qte) {
        this.id = id;
        this.designation = designation;
        this.prix = prix;
        this.qte = qte;
    }

    public int getId() {
        return id;
    }

    public String getDesignation() {

        return designation;
    }

    public double getPrix() {

        return prix;
    }

    public double getQte() {

        return qte;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setPrix(double prix) {

        this.prix = prix;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public String toString(){
        return this.id+" : "+ this.designation+" , Prix: "+ this.prix+"€. Quantité: "+ this.qte+" pièce(s).";
    }
}

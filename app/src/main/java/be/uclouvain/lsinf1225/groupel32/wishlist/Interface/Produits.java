package be.uclouvain.lsinf1225.groupel32.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import be.uclouvain.lsinf1225.groupel32.wishlist.Backend.DBManager;
import be.uclouvain.lsinf1225.groupel32.wishlist.R;

public class Produits extends AppCompatActivity {
    private TextView lblArticles;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produits);




        //TODO : FAIRE EN SORTE QUE LA LISTE DES PRODUITS NE SE FASSENT QU'UNE FOIS. (IL FAUT LA CRÉER DIRECTEMENT DANS LA BASE DE DONNÉE.)

        //lblArticles=(TextView)findViewById(R.id.lblArticles);
        //lblArticles = findViewById(R.id.lblArticles);
        //dbManager = new DBManager(this);
        //dbManager.insert("Ordinateur", 245, 4);
        //dbManager.insert("Tablette", 1245, 3);
        //dbManager.insert("Ordinateur2", 2145, 2);
        //dbManager.insert("Portable", 2415, 1);
        //dbManager.insert("Portable2", 2451, 5);
        //List<Article> details = dbManager.afficherDetail();
        //for(Article detail:details) {
            //lblArticles.append(detail.toString() + "\n\n");
            //dbManager.close();
        //}
        //dbManager.close();
    }
}

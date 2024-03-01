package be.uclouvain.lsinf1225.groupel32.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import be.uclouvain.lsinf1225.groupel32.wishlist.R;

public class DeleteAccount extends AppCompatActivity {

    private Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        //Lorsque je refuse d'éffacer mon compte
        this.play = findViewById(R.id.cancel_delete);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), Profile.class);
                startActivity(otherActivity);
                finish();
            }
        });

        //Lorsque j'accepte la suppression complète de mes données
        this.play = findViewById(R.id.accept_delete);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), MainActivity.class);
                //TODO : SUPPRIMER L'ENSEMBLE DES DONNEES DE l'UTILISATEUR
                startActivity(otherActivity);
                finish();
            }
        });


    }
}

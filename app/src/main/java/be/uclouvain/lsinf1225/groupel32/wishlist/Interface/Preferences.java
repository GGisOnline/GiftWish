package be.uclouvain.lsinf1225.groupel32.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import be.uclouvain.lsinf1225.groupel32.wishlist.Backend.wishlist_intermediate;
import be.uclouvain.lsinf1225.groupel32.wishlist.DAO.UserDAO;
import be.uclouvain.lsinf1225.groupel32.wishlist.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Preferences extends AppCompatActivity {
    UserDAO userDAO;
    private final AppCompatActivity activity = Preferences.this;
    private Button play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        Intent intent = getIntent();
        final String pseudo = intent.getStringExtra(EXTRA_MESSAGE);


        View interet = findViewById(R.id.interet2);
        final String centreinteret = interet.toString();

        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.couleur, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.taille_vêtement, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        this.play = findViewById(R.id.save);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), Profile.class);
                String couleur = spinner2.getSelectedItem().toString();
                String taille = spinner.getSelectedItem().toString();
                userDAO= new UserDAO(activity);
                userDAO.add_preferences_db(pseudo,taille,couleur,centreinteret);
                //TODO : Sauvegarder les données entrées dans la bd
                //NB : Les infos qui déroulent dans le spinner sont dans String.XML
                startActivity(otherActivity);
                finish();
            }
        });


    }
}

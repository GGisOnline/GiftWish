package be.uclouvain.lsinf1225.groupel32.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import be.uclouvain.lsinf1225.groupel32.wishlist.Backend.wishlist_intermediate;
import be.uclouvain.lsinf1225.groupel32.wishlist.DAO.UserDAO;
import be.uclouvain.lsinf1225.groupel32.wishlist.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class EditProfile extends AppCompatActivity {
    UserDAO userDAO;
    private Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent intent = getIntent();
        final String message = intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.loadPseudo);
        textView.setText("Votre Pseudo actuel : "+ message);

        this.play = findViewById(R.id.set_preferences);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), Preferences.class);
                otherActivity.putExtra(EXTRA_MESSAGE, message);
                startActivity(otherActivity);
                finish();
            }
        });


    }
}
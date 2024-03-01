//TODO : RENDRECETTE PARTIE FONCTIONNELLE.
package be.uclouvain.lsinf1225.groupel32.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import be.uclouvain.lsinf1225.groupel32.wishlist.DAO.UserDAO;
import be.uclouvain.lsinf1225.groupel32.wishlist.R;


import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Register_Next extends AppCompatActivity {

    //UserDAO userDao;
    private Button play;
    private final AppCompatActivity activity = Register_Next.this;
    UserDAO userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__next);
        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.error);
        textView.setText(message);
    }
    public void CreateData(View view){
        userDao= new UserDAO(activity);
        Intent intent = new Intent(this, Menu.class);
        EditText nom = findViewById(R.id.createName);
        EditText prenom = findViewById(R.id.createFirstname);
        EditText age = findViewById(R.id.setupAge);
        String nom_bis= nom.getText().toString();
        String prenom_bis= prenom.getText().toString();

        try {
            String age_bis= age.getText().toString();
            Intent intent2= getIntent();
            String pseudo_bis = intent2.getStringExtra(EXTRA_MESSAGE);
            Log.e("pseudo", pseudo_bis);
            int real_age= Integer.parseInt(age_bis);
            if(userDao.AddDataDB(pseudo_bis, nom_bis, prenom_bis, age_bis)){
                intent.putExtra(EXTRA_MESSAGE, pseudo_bis);
                startActivity(intent);
            }
        }
        catch (Exception e){
            Intent intent4 = new Intent(this, Register_Next.class);
            intent4.putExtra(EXTRA_MESSAGE, "la valeur doit etre un nombre");
            startActivity(intent4);
            finish();
        }


    }

}




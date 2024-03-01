//Ceci est l'interface d'inscription.

package be.uclouvain.lsinf1225.groupel32.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import be.uclouvain.lsinf1225.groupel32.wishlist.DAO.UserDAO;
import be.uclouvain.lsinf1225.groupel32.wishlist.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Register extends AppCompatActivity {

    private Button play;
    private final AppCompatActivity activity = Register.this;
    UserDAO userDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.play = findViewById(R.id.continuer);

       /* C'est ça qui cassait tout
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), Menu.class);
                startActivity(otherActivity);
                finish();
            }
        });*/
        Intent intent= getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.error);
        textView.setText(message);
        userDao=new UserDAO(activity);
    }


    //Lorsque l'on décide de créer un compte, si le pseudo entrée se trouve déja dans la base de donnée, celui-ci renvoie une erreur et nous donne une seconde tentative. Sinon, nous accèdons au menu principal.
    public void CreateUser(View view){
        Intent intent = new Intent(this, Register_Next.class);
        Intent intent2 = new Intent(this, Register.class);
        EditText pseudo = (EditText) findViewById(R.id.pseudo);
        EditText mdp = (EditText) findViewById(R.id.MDP);
        EditText cmdp = (EditText) findViewById(R.id.CMDP);
        String pseudo_bis = pseudo.getText().toString();
        String mdp_bis = mdp.getText().toString();
        String cmdp_bis = cmdp.getText().toString();
        if (!cmdp_bis.equals(mdp_bis)){
            intent2.putExtra(EXTRA_MESSAGE, "les deux mots de passe doivent correspondre");
            startActivity(intent2);
            finish();
        }
        else {
            if (userDao.AddUserDB(pseudo_bis, mdp_bis)) {
                userDao.connection_all_other_users(pseudo_bis);
                intent.putExtra(EXTRA_MESSAGE, pseudo_bis);
                startActivity(intent); }
            else {
                intent2.putExtra(EXTRA_MESSAGE, "Pseudo déjà utilisé");
                startActivity(intent2);
                finish();

            }
        }
    }
}

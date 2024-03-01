package be.uclouvain.lsinf1225.groupel32.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import be.uclouvain.lsinf1225.groupel32.wishlist.DAO.UserDAO;
import be.uclouvain.lsinf1225.groupel32.wishlist.R;


import java.util.UUID;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class friend_item_creation extends AppCompatActivity {
    public static final String EXTRA_MESSAGE4 ="" ;
    private static final String EXTRA_MESSAGE5 ="" ;
    private final AppCompatActivity activity = friend_item_creation.this;
    UserDAO userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userDao = new UserDAO(activity);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_item_creation);
        Intent intent4=getIntent();
        String erreur=intent4.getStringExtra(EXTRA_MESSAGE5);
        TextView erreur_text= findViewById(R.id.error_item);
        erreur_text.setText(erreur);
    }
    public void item_creation_friend(View view){
        String uniqueID = UUID.randomUUID().toString();
        EditText item= findViewById(R.id.Nitem2);
        EditText prix= findViewById(R.id.Pitem2);
        EditText desc= findViewById(R.id.desc2);
        String item_bis= item.getText().toString();
        String prix_bis= prix.getText().toString();
        String desc_bis= desc.getText().toString();
        Intent intent= getIntent();
        String idwl=intent.getStringExtra(EXTRA_MESSAGE);
        boolean ret= userDao.create_item(uniqueID, item_bis, desc_bis, prix_bis, idwl);
        if(ret){
            Intent intent2= new Intent(this, alterable_wishlist.class);
            intent2.putExtra(EXTRA_MESSAGE4, "Item créé");
            intent2.putExtra(EXTRA_MESSAGE, idwl);
            startActivity(intent2);
            finish();
        }
        else{
            Intent intent3 = new Intent(this, friend_item_creation.class);
            intent3.putExtra(EXTRA_MESSAGE5, "Données érronées");
            startActivity(intent3);
            finish();
        }

    }
}

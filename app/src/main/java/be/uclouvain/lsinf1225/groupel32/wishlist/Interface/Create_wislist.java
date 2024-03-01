package be.uclouvain.lsinf1225.groupel32.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import be.uclouvain.lsinf1225.groupel32.wishlist.Backend.wishlist_intermediate;
import be.uclouvain.lsinf1225.groupel32.wishlist.DAO.UserDAO;
import be.uclouvain.lsinf1225.groupel32.wishlist.R;


import java.util.UUID;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Create_wislist extends AppCompatActivity {
    public static final String EXTRA_MESSAGE2 ="";
    private static final String EXTRA_MESSAGE3 ="" ;
    private final AppCompatActivity activity = Create_wislist.this;
    UserDAO userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wislist);
        Intent intent2=getIntent();
        final String error = intent2.getStringExtra(EXTRA_MESSAGE3);
        TextView erreur = findViewById(R.id.error);
        erreur.setText(error);
    }
    public void create_wishlist(View view){
        Intent intent = new Intent(this, wishlist_intermediate.class);
        String uniqueID = UUID.randomUUID().toString();
        userDao = new UserDAO(activity);
        Intent intent2=getIntent();
        final String pseudo_bis = intent2.getStringExtra(EXTRA_MESSAGE);
        EditText name = findViewById(R.id.nwl);
        EditText desc = findViewById(R.id.dwl);
        String name_bis= name.getText().toString();
        String dwl_bis= desc.getText().toString();
        CheckBox check= findViewById(R.id.editbox);
        boolean checked = check.isChecked();
        int edit=0;
        if(checked){
            edit=1;
        }
        boolean ret = userDao.create_wishlist(pseudo_bis, name_bis, dwl_bis, edit, uniqueID);
        if (ret) {
            intent.putExtra(EXTRA_MESSAGE, pseudo_bis);
            intent.putExtra(EXTRA_MESSAGE2,"La WishList a bien été créée");
            startActivity(intent);
            finish();
        }
        else{
            Intent intent3 = new Intent(this, Create_wislist.class);
            intent3.putExtra(EXTRA_MESSAGE3, "Données erronées");
            intent3.putExtra(EXTRA_MESSAGE, pseudo_bis);
            startActivity(intent3);
            finish();
        }



    }
}

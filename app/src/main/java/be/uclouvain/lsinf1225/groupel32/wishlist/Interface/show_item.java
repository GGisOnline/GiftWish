package be.uclouvain.lsinf1225.groupel32.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import be.uclouvain.lsinf1225.groupel32.wishlist.DAO.UserDAO;
import be.uclouvain.lsinf1225.groupel32.wishlist.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class show_item extends AppCompatActivity {
    private final AppCompatActivity activity = show_item.this;
    UserDAO userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item);
        TextView name=findViewById(R.id.show_item);
        TextView desc=findViewById(R.id.show_desc);
        TextView prix=findViewById(R.id.prix);
        TextView etat=findViewById(R.id.etat);
        Intent intent= getIntent();
        String name_bis= intent.getStringExtra(Wishes.name);
        String desc_bis= intent.getStringExtra(Wishes.desc);
        Log.e(Wishes.name, Wishes.desc);
        String prix_bis= intent.getStringExtra(Wishes.prix);
        String etat_bis= intent.getStringExtra(Wishes.etat);
        Log.e(Wishes.name,Wishes.etat);
        name.setText(name_bis);
        desc.setText(desc_bis);
        prix.setText(prix_bis);
        if(etat_bis.equals('1')){
            etat.setText("déjà reçu");
        }
        else{
            etat.setText("Toujours désiré");
        }
    }
    public void delete_item(View view){
        userDao= new UserDAO(activity);
        Intent intent= getIntent();
        String name_bis= intent.getStringExtra(Wishes.name);
        String idwl = intent.getStringExtra(EXTRA_MESSAGE);
        String iid =userDao.get_item_id(name_bis, idwl);
        userDao.delete_item(iid);
        Intent intent2= new Intent(this, wishes_intermediate.class);
        intent2.putExtra(EXTRA_MESSAGE, idwl);
        startActivity(intent2);
    }
}

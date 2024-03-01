package be.uclouvain.lsinf1225.groupel32.wishlist.Backend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import be.uclouvain.lsinf1225.groupel32.wishlist.Interface.Create_wislist;
import be.uclouvain.lsinf1225.groupel32.wishlist.Interface.WishLists;
import be.uclouvain.lsinf1225.groupel32.wishlist.R;


import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class wishlist_intermediate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_intermediate);
        Intent intent2=getIntent();
        final String pseudo_bis = intent2.getStringExtra(EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.wl_user);
        textView.setText("WishLists de "+pseudo_bis);
        final String msg =intent2.getStringExtra(Create_wislist.EXTRA_MESSAGE2);
        TextView reussite = findViewById(R.id.wl_created);
        reussite.setText(msg);
    }
    public void go_to_wishlists(View view){
        Intent intent= new Intent(this, WishLists.class);
        Intent intent2=getIntent();
        final String pseudo_bis = intent2.getStringExtra(EXTRA_MESSAGE);
        intent.putExtra(EXTRA_MESSAGE, pseudo_bis);
        startActivity(intent);
        finish();
    }
    public void go_to_create_wishlist(View view){
        Intent intent= new Intent(this, Create_wislist.class);
        Intent intent2=getIntent();
        final String pseudo_bis = intent2.getStringExtra(EXTRA_MESSAGE);
        intent.putExtra(EXTRA_MESSAGE, pseudo_bis);
        startActivity(intent);
        finish();
    }
}

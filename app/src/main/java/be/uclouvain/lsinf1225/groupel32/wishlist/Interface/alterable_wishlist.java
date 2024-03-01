package be.uclouvain.lsinf1225.groupel32.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import be.uclouvain.lsinf1225.groupel32.wishlist.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class alterable_wishlist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterable_wishlist);
        Intent intent=getIntent();
        String feedback= intent.getStringExtra(friend_item_creation.EXTRA_MESSAGE4);
        String nwl=intent.getStringExtra(friend_wishlists.EXTRA_MESSAGE3);
        TextView nwl_show=findViewById(R.id.nwl);
        nwl_show.setText(nwl);
        TextView fb_item= findViewById(R.id.feedback_item_friend);
        fb_item.setText(feedback);
    }
    public void open_wl(View view){
        Intent intent= getIntent();
        Intent intent2 =new Intent(this, WishesFriend.class);
        TextView fb_item= findViewById(R.id.feedback_item_friend);
        String fbc = fb_item.getText().toString();
        String idwl;
        idwl= intent.getStringExtra(EXTRA_MESSAGE);
        Log.e(idwl, "truc2");
        intent2.putExtra(EXTRA_MESSAGE, idwl);
        startActivity(intent2);
    }
    public void add_item(View view){
        Intent intent=new Intent(this, friend_item_creation.class);
        Intent intent2=getIntent();
        String idwl= intent2.getStringExtra(EXTRA_MESSAGE);
        Log.e(idwl, "truc");
        intent.putExtra(EXTRA_MESSAGE, idwl);
        startActivity(intent);
        finish();

    }
}

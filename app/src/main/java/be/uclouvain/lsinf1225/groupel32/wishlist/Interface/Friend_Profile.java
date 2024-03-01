package be.uclouvain.lsinf1225.groupel32.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import be.uclouvain.lsinf1225.groupel32.wishlist.Interface.Friends;

import be.uclouvain.lsinf1225.groupel32.wishlist.DAO.UserDAO;
import be.uclouvain.lsinf1225.groupel32.wishlist.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Friend_Profile extends AppCompatActivity {
    public static final String EXTRA_MESSAGE2 ="" ;
    private final AppCompatActivity activity = Friend_Profile.this;
    UserDAO userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend__profile);
        Intent intent=getIntent();
        String pseudo=intent.getStringExtra(Friends.EXTRA_MESSAGE2);
        TextView pseudo_show=findViewById(R.id.friend_pseudo);
        pseudo_show.setText(pseudo);
    }
    public void delete_friend(View view){
        userDao= new UserDAO(activity);
        Intent intent=getIntent();
        String pseudo_friend=intent.getStringExtra(Friends.EXTRA_MESSAGE2);
        String my_pseudo = intent.getStringExtra(EXTRA_MESSAGE);
        userDao.delete_friend(my_pseudo, pseudo_friend);
        Intent intent2=new Intent(this, friends_intermediate.class);
        intent2.putExtra(EXTRA_MESSAGE2,"ami supprimé");
        intent2.putExtra(EXTRA_MESSAGE, my_pseudo);
        startActivity(intent2);
        finish();
    }
    public void open_friend_wishlist(View view){
        userDao= new UserDAO(activity);
        Intent intent=getIntent();
        String pseudo_friend=intent.getStringExtra(Friends.EXTRA_MESSAGE2);
        String my_pseudo = intent.getStringExtra(EXTRA_MESSAGE);
        Intent intent2= new Intent(this, friend_wishlists.class);
        intent2.putExtra(EXTRA_MESSAGE, my_pseudo);
        intent2.putExtra(EXTRA_MESSAGE2, pseudo_friend);
        startActivity(intent2);
        finish();
    }

}

package be.uclouvain.lsinf1225.groupel32.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import be.uclouvain.lsinf1225.groupel32.wishlist.Backend.MyAdapterFWL;
import be.uclouvain.lsinf1225.groupel32.wishlist.Backend.MyAdapterFriend;
import be.uclouvain.lsinf1225.groupel32.wishlist.DAO.UserDAO;
import be.uclouvain.lsinf1225.groupel32.wishlist.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class friend_wishlists extends AppCompatActivity {
    public static final String EXTRA_MESSAGE2 ="" ;
    public static final String EXTRA_MESSAGE3 = "";
    public static final String EXTRA_MESSAGE4 ="" ;
    public static final String EXTRA_MESSAGE5 ="" ;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private final AppCompatActivity activity = friend_wishlists.this;
    UserDAO userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_wishlists);
        userDao= new UserDAO(activity);
        Intent intent= getIntent();
        final String pseudo = intent.getStringExtra(Friend_Profile.EXTRA_MESSAGE2);
        String[] myDataset= userDao.get_wishlists(pseudo);
        recyclerView = findViewById(R.id.my_recycler_view5);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapterFWL(myDataset);
        recyclerView.setAdapter(mAdapter);
    }
    public void open_friend_wl(View view){
        userDao= new UserDAO(activity);
        Intent intent= getIntent();
        final String friend = intent.getStringExtra(Friend_Profile.EXTRA_MESSAGE2);
        final String user = intent.getStringExtra(EXTRA_MESSAGE);
        TextView nwl= findViewById(R.id.row_friends_wl);
        String nwl_bis= nwl.getText().toString();
        String idwl= userDao.obtain_idwl(friend, nwl_bis);
        Log.e(user, idwl);
        //boolean ret=userDao.authorized(user, idwl);
        boolean ret2=userDao.modifiable_wl(friend, idwl);
        Log.e(nwl_bis,idwl);
        Intent intent2 = new Intent();
        if(ret2){
            intent2= new Intent(this, alterable_wishlist.class);
            intent2.putExtra(EXTRA_MESSAGE5, "true");
        }
        else{
            intent2 =new Intent(this, WishesFriend.class);
            intent2.putExtra(EXTRA_MESSAGE5, "false");
            startActivity(intent2);
        }
        intent2.putExtra(EXTRA_MESSAGE4, user);
        intent2.putExtra(EXTRA_MESSAGE2, friend);
        intent2.putExtra(EXTRA_MESSAGE3,nwl_bis);
        intent2.putExtra(EXTRA_MESSAGE,idwl);
        startActivity(intent2);
        //else{
            //intent2= new Intent(this, not_alterable_wishlist.class);
        //}
        
    }
}

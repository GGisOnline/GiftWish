package be.uclouvain.lsinf1225.groupel32.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import be.uclouvain.lsinf1225.groupel32.wishlist.Backend.MyAdapterFriend;
import be.uclouvain.lsinf1225.groupel32.wishlist.DAO.UserDAO;
import be.uclouvain.lsinf1225.groupel32.wishlist.R;


import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class Friends extends AppCompatActivity {
    public static final String EXTRA_MESSAGE2 = "";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private final AppCompatActivity activity = Friends.this;
    UserDAO userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        userDao= new UserDAO(activity);
        Intent intent= getIntent();
        final String pseudo = intent.getStringExtra(EXTRA_MESSAGE);
        String[] myDataset= userDao.get_friendlist(pseudo);
        recyclerView = findViewById(R.id.my_recycler_view3);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapterFriend(myDataset);
        recyclerView.setAdapter(mAdapter);

    }
    public void open_friend(View view){
        Intent intent=getIntent();
        String pseudo = intent.getStringExtra(EXTRA_MESSAGE);
        Intent intent2= new Intent(this, Friend_Profile.class);
        intent2.putExtra(EXTRA_MESSAGE, pseudo);
        TextView friend= findViewById(R.id.row_friends);
        String friend_pseudo= friend.getText().toString();
        intent2.putExtra(EXTRA_MESSAGE2, friend_pseudo);
        startActivity(intent2);
    }
}
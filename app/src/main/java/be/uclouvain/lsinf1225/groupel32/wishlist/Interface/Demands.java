package be.uclouvain.lsinf1225.groupel32.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import be.uclouvain.lsinf1225.groupel32.wishlist.Backend.MyAdapterDemands;
import be.uclouvain.lsinf1225.groupel32.wishlist.DAO.UserDAO;
import be.uclouvain.lsinf1225.groupel32.wishlist.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Demands extends AppCompatActivity {
    public static final String EXTRA_MESSAGE3 ="" ;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private final AppCompatActivity activity = Demands.this;
    UserDAO userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demands);
        userDao= new UserDAO(activity);
        Intent intent= getIntent();
        final String pseudo = intent.getStringExtra(EXTRA_MESSAGE);
        String[] myDataset= userDao.get_demands(pseudo);
        recyclerView = findViewById(R.id.my_recycler_view4);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapterDemands(myDataset);
        recyclerView.setAdapter(mAdapter);
    }
    public void accept(View view){
        Intent intent= new Intent(this,friends_intermediate.class);
        Intent intent2=getIntent();
        String pseudo= intent2.getStringExtra(EXTRA_MESSAGE);
        TextView friend= findViewById(R.id.row_d);
        String friend_bis= friend.getText().toString();
        userDao= new UserDAO(activity);
        userDao.accept_friend(pseudo, friend_bis);
        intent.putExtra(EXTRA_MESSAGE, pseudo);
        intent.putExtra(EXTRA_MESSAGE3, "demande d'ami acceptée");
        startActivity(intent);
        finish();
    }
}

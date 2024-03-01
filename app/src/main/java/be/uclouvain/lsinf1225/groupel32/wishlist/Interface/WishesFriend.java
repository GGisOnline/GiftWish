package be.uclouvain.lsinf1225.groupel32.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import be.uclouvain.lsinf1225.groupel32.wishlist.Backend.MyAdapterWishesFriend;
import be.uclouvain.lsinf1225.groupel32.wishlist.DAO.UserDAO;
import be.uclouvain.lsinf1225.groupel32.wishlist.R;


import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class WishesFriend extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private final AppCompatActivity activity = WishesFriend.this;
    UserDAO userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishes_friend);
        userDao= new UserDAO(activity);
        Intent intent= getIntent();
        String id = intent.getStringExtra(EXTRA_MESSAGE);
        String[] myDataset= userDao.get_wishes_friend(id);
        recyclerView = findViewById(R.id.my_recycler_view6);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapterWishesFriend(myDataset);
        recyclerView.setAdapter(mAdapter);

    }
}

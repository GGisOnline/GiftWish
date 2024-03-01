package be.uclouvain.lsinf1225.groupel32.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import be.uclouvain.lsinf1225.groupel32.wishlist.Backend.MyAdapterWishes;
import be.uclouvain.lsinf1225.groupel32.wishlist.DAO.UserDAO;
import be.uclouvain.lsinf1225.groupel32.wishlist.R;


import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Wishes extends AppCompatActivity {
    public static final String name ="" ;
    public static final String desc ="" ;
    public static final String prix ="" ;
    public static final String etat = "";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private final AppCompatActivity activity = Wishes.this;
    UserDAO userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishes);
        userDao= new UserDAO(activity);
        Intent intent= getIntent();
        final String id = intent.getStringExtra(EXTRA_MESSAGE);
        String[] myDataset= userDao.get_wishes(id);
        recyclerView = findViewById(R.id.my_recycler_view2);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapterWishes(myDataset);
        recyclerView.setAdapter(mAdapter);

    }
    public void open_item(View view){
        TextView item= findViewById(R.id.namewish);
        String item_name= item.getText().toString();
        userDao= new UserDAO(activity);
        Intent intent= getIntent();
        String id = intent.getStringExtra(EXTRA_MESSAGE);
        Log.e(id, item_name);
        String[] item_info= userDao.get_item_info(item_name, id);
        int i=0;
       Intent intent2= new Intent(this, show_item.class);
       intent2.putExtra(name,item_info[0]);
       intent2.putExtra(desc,item_info[1]);
       Log.e(item_info[0], item_info[1]);
       intent2.putExtra(prix,item_info[2]);
       intent2.putExtra(etat,item_info[3]);
        Log.e(item_info[2], item_info[3]);
       intent2.putExtra(EXTRA_MESSAGE, id);
       startActivity(intent2);
       finish();
    }
}


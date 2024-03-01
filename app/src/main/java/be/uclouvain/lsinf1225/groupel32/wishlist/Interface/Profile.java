//Ceci est l'interface du profil.

package be.uclouvain.lsinf1225.groupel32.wishlist.Interface;
;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import be.uclouvain.lsinf1225.groupel32.wishlist.Backend.FeedReaderDbHelper;
import be.uclouvain.lsinf1225.groupel32.wishlist.Backend.ImageToBlob;
import be.uclouvain.lsinf1225.groupel32.wishlist.Backend.USER;
import be.uclouvain.lsinf1225.groupel32.wishlist.DAO.UserDAO;
import be.uclouvain.lsinf1225.groupel32.wishlist.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Profile extends AppCompatActivity {

    private USER user;
    private static final int REQUEST_GET_SINGLE_FILE = 1 ;
    private static final int IMAGE_CAPTURED = 2;
    private Button play;
    private String photoPath;
    private Bitmap img;
    UserDAO userDAO;
    FeedReaderDbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        final int REQUEST_IMAGE_CAPTURE = 1;

        //Affiche le pseudo de l'user connecté.
        Intent intent = getIntent();
        //String message = intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.pseudo_user);
        //textView.setText(message);
        final String pseudo_bis = intent.getStringExtra(EXTRA_MESSAGE);
        textView.setText(pseudo_bis);
        //ImageView photo = findViewById(R.id.photo_user);

        //this.play = findViewById(R.id.edit_photo);
        //play.setOnClickListener(new View.OnClickListener() {
           // @Override
            //public void onClick(View view) {
                //if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    //requestPermissions(new String[] {
                            //Manifest.permission.READ_EXTERNAL_STORAGE
                    //}, 2);
                //}else {
                    //Intent pick = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    //startActivityForResult(pick, 1);
                //}
            //}
        //});



        //Ouvre notre galerie photo, nous permettant de choisir une photo.
        //TODO : Enegistrer l'image dans la base de donnée
        //this.play = findViewById(R.id.edit_photo);
        //play.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
                //Cursor cursor = null;
                //Intent otherActivity = new Intent(getApplicationContext(), Profile.class);
                //Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //intent.addCategory(Intent.CATEGORY_OPENABLE);
                //intent.setType("image/*");
                //startActivityForResult(Intent.createChooser(intent, "Select Picture"),REQUEST_GET_SINGLE_FILE);
                //byte[] profilePicture = cursor.getBlob(0);
               // userDAO.updateProfilePicture(profilePicture);
                //startActivity(otherActivity);
            //}
        //});

                //Permet à l'utilisateur de modifier son profil.
        this.play = findViewById(R.id.edit_profile);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), EditProfile.class);
                Intent intent = getIntent();
                String getPseudo = intent.getStringExtra(EXTRA_MESSAGE);
                otherActivity.putExtra(EXTRA_MESSAGE, getPseudo);
                startActivity(otherActivity);
            }
        });

        this.play = findViewById(R.id.delete_account);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), DeleteAccount.class);
                startActivity(otherActivity);
                finish();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK && requestCode == REQUEST_GET_SINGLE_FILE) {
                Uri selected = data.getData();
                img = ImageToBlob.getBytePhoto(ImageToBlob.getBytes(selected, this));
                ImageView photo = findViewById(R.id.photo_user);
                photo.setImageBitmap(img);
            }
        } catch (Exception e) {
            Log.e("FileSelectorActivity", "File select error", e);
        }
    }
}

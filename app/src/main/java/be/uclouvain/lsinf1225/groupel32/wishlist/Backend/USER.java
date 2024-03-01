package be.uclouvain.lsinf1225.groupel32.wishlist.Backend;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.widget.Toast;

import be.uclouvain.lsinf1225.groupel32.wishlist.DAO.UserDAO;

public class USER {
    public String Pseudo;
    public String MDP;
    UserProfile userprofile;
    public UserDAO dao;
    private String Nom;
    private String Prenom;
    private String sexe;
    private Bitmap photo = null;
    private String langue;
    private Context context;
    private FeedReaderDbHelper db2;
    private SQLiteDatabase db;


    public USER (Context context){
        this.context = context;
        this.db2 = new FeedReaderDbHelper(this.context);
    }

    public void updateProfile(){
        String req = "SELECT * FROM PROFIL WHERE Pseudo=\""+this.getPseudo()+"\";";
        this.db = db2.getReadableDatabase();

        Cursor cursor =db.rawQuery(req, null);
        cursor.moveToFirst();
        if(!cursor.isAfterLast()){
            setPseudo(cursor.getString(cursor.getColumnIndex("Pseudo")));
            setPrenom(cursor.getString(cursor.getColumnIndex("Prenom")));
            setNom(cursor.getString(cursor.getColumnIndex("Nom")));
            if(cursor.getBlob(cursor.getColumnIndex("Photo"))!= null) {
                byte[] picture = cursor.getBlob(cursor.getColumnIndex("Photo"));
                setPhoto(ImageToBlob.getBytePhoto(picture));
            }
            //System.out.println("***** ERREUR *****");
        } else{
            //Give a Toast error (not yet register for example)
            Toast.makeText(this.context, "text to display", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

    public Bitmap getPhoto() {
        return this.photo;
    }

    private void setPhoto(Bitmap Photo){
        this.photo = photo;
    }

    public void updatePhoto(Bitmap photo) {
    }

    public String getNom(){
        return this.Nom;
    }

    public void setPseudo(String pseudo){
        this.Pseudo=pseudo;
    }

    public String getPseudo(){
        return this.Pseudo;
    }

    public void setMDP(String mdp){
        this.MDP=mdp;
    }

    public String getMDP(){
        return this.MDP;
    }

    public void setNom(String nom){
        this.Nom=nom;
    }

    public String getPrenom(){
        return this.Prenom;
    }

    public void setPrenom(String prenom){
        this.Prenom=prenom;
    }


    public String getSexe(){
        return this.sexe;
    }

    public void setSexe(String sexe){
        this.sexe=sexe;
    }

    public String getLangue(){
        return this.langue;
    }

    public void setLangue(String langue){
        this.langue=langue;
    }
}

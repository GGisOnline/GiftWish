package be.uclouvain.lsinf1225.groupel32.wishlist.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import be.uclouvain.lsinf1225.groupel32.wishlist.Backend.FeedReaderContract;
import be.uclouvain.lsinf1225.groupel32.wishlist.Backend.FeedReaderDbHelper;
import be.uclouvain.lsinf1225.groupel32.wishlist.Backend.USER;


public class UserDAO {
    FeedReaderDbHelper dbHelper;
    public UserDAO(Context context) {

        dbHelper = new FeedReaderDbHelper(context);
    }
    public boolean CreateFirstUser (USER user,String pseudo, String mdp){
        boolean ret=false;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_USER_MDP
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_USER_PSEUDO + " = ?";
        String[] selectionArgs = {pseudo};
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_USER,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        cursor.moveToFirst();
        if (!cursor.isAfterLast()){
            String lst =cursor.getString(0);
            if (lst.equals(mdp)){
                user.Pseudo=pseudo;
                user.MDP=mdp;
                ret=true;
                Log.e("try to connect", "connected");
                }
            }
        cursor.close();

        db.close();
        return ret;
    }

    public boolean AddUserDB(String pseudo, String mdp){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_USER_PSEUDO, pseudo);
        values.put(FeedReaderContract.FeedEntry.COLUMN_USER_MDP, mdp);
        long newRowId = db.insertWithOnConflict(FeedReaderContract.FeedEntry.TABLE_USER, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if (newRowId==-1){
            db.close();
            return false;
        }
        db.close();
        return true;
    }

    public boolean AddDataDB(String pseudo, String name, String firstname, String age){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_PROFIL_PSEUDO, pseudo);
        values.put(FeedReaderContract.FeedEntry.COLUMN_PROFIL_NOM, name);
        values.put(FeedReaderContract.FeedEntry.COLUMN_PROFIL_PRENOM, firstname);
        values.put(FeedReaderContract.FeedEntry.COLUMN_PROFIL_AGE, age);
        long newRowId = db.insertWithOnConflict(FeedReaderContract.FeedEntry.TABLE_PROFIL, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if (newRowId==-1){
            db.close();
            Log.e("Création profil", "failed");
            return false;
        }
        db.close();
        Log.e("Création profil", "suceed");
        return true;
    }
    public String[] get_wishlists(String pseudo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_WL_NWL
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_WL_PSEUDO+ " = ?";
        String[] selectionArgs = {pseudo};
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_WL,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        String[] Liste = new String[cursor.getCount()];
        cursor.moveToFirst();
        int ind=0;
        while(!cursor.isAfterLast()){
            String lst =cursor.getString(0);
            Liste[ind]=lst;
            cursor.moveToNext();
            ind+=1;
        }
        cursor.close();
        return Liste;
        }
    public String[] get_wishes(String idwl){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_ITEM_NOM

        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_ITEM_IDWL+ " = ?";
        String[] selectionArgs = {idwl};
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_ITEM,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        String[] Liste = new String[cursor.getCount()];
        cursor.moveToFirst();
        int ind=0;
        while(!cursor.isAfterLast()){
            String lst =cursor.getString(0);
            Liste[ind]=lst;
            cursor.moveToNext();
            ind+=1;
        }
        cursor.close();
        return Liste;
    }
    public String obtain_idwl(String pseudo, String nwl){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_WL_IDWL

        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_WL_NWL+ " = ? AND " + FeedReaderContract.FeedEntry.COLUMN_WL_PSEUDO + " = ?";
        String[] selectionArgs = {
                nwl,
                pseudo
        };
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_WL,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        cursor.moveToFirst();
        String lst = null;
        if (!cursor.isAfterLast()) {
            lst =cursor.getString(0); 
        }
        cursor.close();
        return lst;

    }
    public boolean create_wishlist(String pseudo, String nwl, String desc, int edit, String id ){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (nwl.length()==0){
            return false;
        }
        values.put(FeedReaderContract.FeedEntry.COLUMN_WL_PSEUDO, pseudo);
        values.put(FeedReaderContract.FeedEntry.COLUMN_WL_NWL, nwl);
        values.put(FeedReaderContract.FeedEntry.COLUMN_WL_DESCRIPTION, desc);
        values.put(FeedReaderContract.FeedEntry.COLUMN_WL_PSEUDO, pseudo);
        values.put(FeedReaderContract.FeedEntry.COLUMN_WL_EDIT, edit);
        values.put(FeedReaderContract.FeedEntry.COLUMN_WL_IDWL, id);
        long newRowId = db.insertWithOnConflict(FeedReaderContract.FeedEntry.TABLE_WL, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();
        if(newRowId==-1){
            return false;
        }
        return true;

    }
    public boolean create_item(String IID, String item, String desc,String prix,String IDWL){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (item.length()==0){
            return false;
        }
        if (prix.length()==0){
            return false;
        }
        values.put(FeedReaderContract.FeedEntry.COLUMN_ITEM_ID, IID);
        values.put(FeedReaderContract.FeedEntry.COLUMN_ITEM_NOM, item);
        values.put(FeedReaderContract.FeedEntry.COLUMN_ITEM_DESCRIPTION, desc);
        values.put(FeedReaderContract.FeedEntry.COLUMN_ITEM_PRIX, prix);
        values.put(FeedReaderContract.FeedEntry.COLUMN_ITEM_ETAT, 0);
        values.put(FeedReaderContract.FeedEntry.COLUMN_ITEM_IDWL, IDWL);
        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_ITEM, null, values);
        db.close();
        if(newRowId==-1){
            return false;
        }
        return true;
    }
    public String get_item_id(String name, String idwl){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_ITEM_ID
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_ITEM_IDWL+ " = ? AND " + FeedReaderContract.FeedEntry.COLUMN_ITEM_NOM + " = ?";
        String[] selectionArgs = {idwl, name};
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_ITEM,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        cursor.moveToFirst();
        String lst= cursor.getString(0);
        return lst;
    }
    public void delete_item(String iid){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = FeedReaderContract.FeedEntry.COLUMN_ITEM_ID + " = ?";
        String[] selectionArgs={iid};
        int deletedRows = db.delete(FeedReaderContract.FeedEntry.TABLE_ITEM, selection, selectionArgs);

    }
    public void delete_wl(String idwl){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_ITEM_ID
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_ITEM_IDWL+ " = ?";
        String[] selectionArgs = {idwl};
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_ITEM,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        String[] Liste = new String[cursor.getCount()];
        int e=cursor.getCount();
        cursor.moveToFirst();
        int ind=0;
        while(!cursor.isAfterLast()){
            String lst =cursor.getString(0);
            Liste[ind]=lst;
            cursor.moveToNext();
            ind+=1;
        }
        cursor.close();
        int i=0;
        while(i<e){
            Log.e("item", Liste[i]);
            delete_item(Liste[i]);
            i+=1;
            Log.e("delete", "line");
        }
        String selection2 = FeedReaderContract.FeedEntry.COLUMN_WL_IDWL+ " = ?";
        String[] selectionArgs2={idwl};
        int deletedRows = db.delete(FeedReaderContract.FeedEntry.TABLE_WL, selection2, selectionArgs2);
        db.close();
    }

    public String[] get_friendlist(String pseudo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO

        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2+ " = ? AND "  + FeedReaderContract.FeedEntry.COLUMN_FRIEND_FRIEND+" = ? " ;
        String[] selectionArgs = {pseudo,"1"};
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_FRIEND,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        String[] projection2 = {
                FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2

        };
        String selection2 = FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO+ " = ? AND "  + FeedReaderContract.FeedEntry.COLUMN_FRIEND_FRIEND+" = ? " ;
        String[] selectionArgs2 = {pseudo,"1"};
        Cursor cursor2 = db.query(
                FeedReaderContract.FeedEntry.TABLE_FRIEND,   // The table to query
                projection2,             // The array of columns to return (pass null to get all)
                selection2,              // The columns for the WHERE clause
                selectionArgs2,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        String[] Liste = new String[cursor.getCount()+ cursor2.getCount()];
        cursor.moveToFirst();
        cursor2.moveToFirst();
        int ind=0;
        if (cursor.getCount()!=0){
            while(!cursor.isAfterLast()){
                String lst =cursor.getString(0);
                Liste[ind]=lst;
                cursor.moveToNext();
                ind+=1;
            }
        }
        if(cursor2.getCount()!=0){
            while(!cursor2.isAfterLast()){
                String lst =cursor2.getString(0);
                Liste[ind]=lst;
                cursor2.moveToNext();
                ind+=1;
            }
        }

        cursor.close();
        cursor2.close();
        return Liste;
    }

    //public void updatePseudoUser(String pseudo_user){
        //SQLiteDatabase db = dbHelper.getWritableDatabase();
        //ContentValues values = new ContentValues();
        //values.put(FeedReaderContract.FeedEntry.COLUMN_ITEM_ID, IID);
        //String selection = FeedReaderContract.FeedEntry.COLUMN_ITEM_IDWL+ " = ?";
        //Bitmap[]selectionArg = {String.valueOf(profilePicture)};
        //db.update("TABLE_PROFILE", values, selection, selectionArg);
    //}

    //public void updateProfilePicture(Bitmap profilePicture){
    //SQLiteDatabase db = dbHelper.getWritableDatabase();
    //ContentValues values = new ContentValues();
    //profilePicture = ImageToBlob.getBytes(profilePicture);
    //values.put("Photo", ImageToBlob.getBytes(profilePicture));
    //String selection = FeedReaderContract.FeedEntry.COLUMN_PROFIL_PHOTO+ " = ?";
    //String[]selectionArg = {profilePicture};
    //db.update("TABLE_PROFILE", values, selection, selectionArg);
    //}

    public void updateProfilePicture(byte[] profilePicture) throws SQLiteException {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new  ContentValues();
        cv.put("Photo", profilePicture);
        db.insert(FeedReaderContract.FeedEntry.TABLE_PROFIL, null, cv );
        db.close();
    }


    public boolean friend_request(String pseudo, String pseudo2) {
        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String[] projection = {
                    FeedReaderContract.FeedEntry.COLUMN_FRIEND_FRIEND,
                    FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST1,
                    FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST2,
                    FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO,
                    FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2

            };
            String selection = FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO + " = ? AND " + FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2 + " = ? OR " +
                    FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO + " = ? AND " + FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2 + " = ?";
            String[] selectionArgs = {pseudo, pseudo2, pseudo2, pseudo};
            Cursor cursor = db.query(
                    FeedReaderContract.FeedEntry.TABLE_FRIEND,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );
            cursor.moveToFirst();
            if (cursor.getInt(0) == 0 && cursor.getInt(1) == 0 && cursor.getInt(2) == 0) {
                ContentValues values = new ContentValues();
                if (cursor.getString(3).equals(pseudo)) {
                    values.put(FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST1, 1);
                    String selection2 = FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST1 + " = ? AND " + FeedReaderContract.FeedEntry.COLUMN_FRIEND_FRIEND + " = ? AND " +
                            FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST2 + " = ? AND " +
                            FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO + "= ? AND " + FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2 + " = ? ";
                    String[] selectionArgs2 = {"0", "0", "0", pseudo, pseudo2};
                    int count = db.update(
                            FeedReaderContract.FeedEntry.TABLE_FRIEND,
                            values,
                            selection2,
                            selectionArgs2);

                } else {
                    values.put(FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST2, 1);
                    String selection2 = FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST2 + " = ? AND " + FeedReaderContract.FeedEntry.COLUMN_FRIEND_FRIEND + " = ? AND " +
                            FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST1 + " = ? AND " +
                            FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO + "= ? AND " + FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2 + " = ? ";
                    String[] selectionArgs2 = {"0", "0", "0", pseudo2, pseudo};
                    int count = db.update(
                            FeedReaderContract.FeedEntry.TABLE_FRIEND,
                            values,
                            selection2,
                            selectionArgs2);

                }
                cursor.close();
                db.close();
                return true;
            } else {
                cursor.close();
                db.close();
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }

    public String[]  ALL_users(String pseudo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_USER_PSEUDO
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_USER_PSEUDO+ " != ? " ;
        String[] selectionArgs = {pseudo};
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_USER,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        String[] Liste = new String[cursor.getCount()];
        cursor.moveToFirst();
        int ind=0;
        while(!cursor.isAfterLast()){
            String lst =cursor.getString(0);
            Liste[ind]=lst;
            cursor.moveToNext();
            ind+=1;
        }
        cursor.close();
        return Liste;
    }
    public void connection_all_other_users(String pseudo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] everyUser = ALL_users(pseudo);
        for (int i=0; i<= (everyUser.length)-1; i++) {
            ContentValues values = new ContentValues();
            values.put(FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO, pseudo);
            values.put(FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2, everyUser[i]);
            values.put(FeedReaderContract.FeedEntry.COLUMN_FRIEND_FRIEND, "0");
            values.put(FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST1, "0");
            values.put(FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST2, "0");
            db.insertWithOnConflict(FeedReaderContract.FeedEntry.TABLE_FRIEND, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        }

    }
    public String[] get_demands(String pseudo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO+ " = ? AND " + FeedReaderContract.FeedEntry.COLUMN_FRIEND_FRIEND+ " = ? AND " +
                FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST1 + " = ? AND " +FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST2 + " = ? ";
        String[] selectionArgs = {pseudo,"0","0","1"};
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_FRIEND,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        String[] projection2 = {
                FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO
        };
        String selection2 = FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2+ " = ? AND " + FeedReaderContract.FeedEntry.COLUMN_FRIEND_FRIEND+ " = ? AND " +
                FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST1 + " = ? AND " +FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST2 + " = ? ";
        String[] selectionArgs2 = {pseudo,"0","1","0"};
        Cursor cursor2 = db.query(
                FeedReaderContract.FeedEntry.TABLE_FRIEND,   // The table to query
                projection2,             // The array of columns to return (pass null to get all)
                selection2,              // The columns for the WHERE clause
                selectionArgs2,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        String[] Liste = new String[cursor.getCount()+ cursor2.getCount()];
        cursor.moveToFirst();
        cursor2.moveToFirst();
        int ind=0;
        if(cursor.getCount()!=0){
            while(!cursor.isAfterLast()){
                String lst =cursor.getString(0);
                Liste[ind]=lst;
                cursor.moveToNext();
                ind+=1;
            }
        }
        if(cursor2.getCount()!=0){
            while(!cursor2.isAfterLast()){
                String lst =cursor2.getString(0);
                Liste[ind]=lst;
                cursor2.moveToNext();
                ind+=1;}
        }
        cursor.close();
        cursor2.close();
        db.close();
        return Liste;
        }

    public void accept_friend(String user, String ami){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST1, 0);
        values.put(FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST2, 0);
        values.put(FeedReaderContract.FeedEntry.COLUMN_FRIEND_FRIEND, 1);
        String selection2 = FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO + " = ? AND "+FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2 + " = ? OR " +
                FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2 + " = ? AND " + FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO + " = ?";
        String[] selectionArgs2= {user, ami, user, ami};
        int count = db.update(
                FeedReaderContract.FeedEntry.TABLE_FRIEND,
                values,
                selection2,
                selectionArgs2);
        db.close();
    }
    public void delete_friend(String user, String ami){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST1, 0);
        values.put(FeedReaderContract.FeedEntry.COLUMN_FRIEND_REQUEST2, 0);
        values.put(FeedReaderContract.FeedEntry.COLUMN_FRIEND_FRIEND, 0);
        String selection2 = FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO + " = ? AND "+FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2 + " = ? OR " +
                FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO2 + " = ? AND " + FeedReaderContract.FeedEntry.COLUMN_FRIEND_PSEUDO + " = ?";
        String[] selectionArgs2= {user, ami, user, ami};
        int count = db.update(
                FeedReaderContract.FeedEntry.TABLE_FRIEND,
                values,
                selection2,
                selectionArgs2);
        db.close();
    }
    public boolean authorized(String pseudo, String id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {FeedReaderContract.FeedEntry.COLUMN_AUTORISATION_EDIT
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_AUTORISATION_PSEUDO+ " = ? AND " + FeedReaderContract.FeedEntry.COLUMN_AUTORISATION_IDWL+ " = ?";
        String[] selectionArgs = {pseudo, id};
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_AUTORISATION,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        cursor.moveToFirst();
        if(cursor.getInt(0)==1){
            cursor.close();
            return true;
        }
        else{
            cursor.close();
            return false;
        }
    }
    public boolean modifiable_wl(String pseudo, String id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_WL_EDIT
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_WL_PSEUDO+ " = ? AND " + FeedReaderContract.FeedEntry.COLUMN_WL_IDWL+ " = ?";
        String[] selectionArgs = {pseudo, id};
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_WL,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        cursor.moveToFirst();
        if(cursor.getInt(0)==1){
            cursor.close();
            db.close();
            return true;
        }
        else{
            cursor.close();
            db.close();
            return false;
        }
    }
    public String[] get_wishes_friend(String id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_ITEM_NOM

        };
        Log.e(FeedReaderContract.FeedEntry.COLUMN_ITEM_NOM, "truc");
        String selection = FeedReaderContract.FeedEntry.COLUMN_ITEM_IDWL+ " = ?";
        String[] selectionArgs = {id};
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_ITEM,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        String[] Liste = new String[cursor.getCount()];
        cursor.moveToFirst();
        int ind=0;
        while(!cursor.isAfterLast()){
            String lst =cursor.getString(0);
            Liste[ind]=lst;
            cursor.moveToNext();
            ind+=1;
        }
        cursor.close();
        return Liste;
    }
    public String[] get_item_info(String item, String idwl){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_ITEM_NOM,
                FeedReaderContract.FeedEntry.COLUMN_ITEM_DESCRIPTION,
                FeedReaderContract.FeedEntry.COLUMN_ITEM_PRIX,
                FeedReaderContract.FeedEntry.COLUMN_ITEM_ETAT
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_ITEM_IDWL+ " = ? AND " +FeedReaderContract.FeedEntry.COLUMN_ITEM_NOM + " = ?";
        String[] selectionArgs = {idwl, item};
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_ITEM,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        String[] Liste= new String[cursor.getColumnCount()];
        cursor.moveToFirst();
        Liste[0]= cursor.getString(0);
        Liste[1]= cursor.getString(1);
        Log.e(Liste[0], Liste[1]);
        Liste[2]= cursor.getString(2);
        Liste[3]= cursor.getString(3);
        Log.e(Liste[2], Liste[3]);
        cursor.close();
        return Liste;
    }

    public void add_preferences_db(String pseudo, String taille, String couleur, String centrei){
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_PREFERENCES_PSEUDO, pseudo);
        values.put(FeedReaderContract.FeedEntry.COLUMN_PREFERENCES_TAILLE_H, taille);
        values.put(FeedReaderContract.FeedEntry.COLUMN_PREFERENCES_COULEUR, couleur);
        values.put(FeedReaderContract.FeedEntry.COLUMN_PREFERENCES_CENTREI, centrei);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_PREFERENCES, null, values);
    }
}





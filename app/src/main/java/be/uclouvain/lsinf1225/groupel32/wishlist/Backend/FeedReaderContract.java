package be.uclouvain.lsinf1225.groupel32.wishlist.Backend;

import android.provider.BaseColumns;

public final class FeedReaderContract {

    public FeedReaderContract(){}
    public static class FeedEntry implements BaseColumns{
        public static final String TABLE_AUTORISATION="Autorisation";
        public static final String COLUMN_AUTORISATION_PSEUDO="PSEUDO";
        public static final String COLUMN_AUTORISATION_IDWL="ID_WL";
        public static final String COLUMN_AUTORISATION_EDIT="Edit";
        public static final String TABLE_FRIEND="FRIEND";
        public static final String COLUMN_FRIEND_PSEUDO="PSEUDO";
        public static final String COLUMN_FRIEND_PSEUDO2="PSEUDO_2";
        public static final String COLUMN_FRIEND_FRIEND = "Friend";
        public static final String COLUMN_FRIEND_REQUEST1 = "Request";
        public static final String COLUMN_FRIEND_REQUEST2 = "Request2";
        public static final String TABLE_ITEM ="ITEM";
        public static final String COLUMN_ITEM_ID="ID";
        public static final String COLUMN_ITEM_WL="WL";
        public static final String COLUMN_ITEM_NOM="Nom";
        public static final String COLUMN_ITEM_DESCRIPTION="Description";
        public static final String COLUMN_ITEM_PRIX="Prix";
        public static final String COLUMN_ITEM_ETAT="etat";
        public static final String COLUMN_ITEM_IDWL="ID_WL";
        public static final String TABLE_PROFIL="PROFIL";
        public static final String COLUMN_PROFIL_PSEUDO="Pseudo";
        public static final String COLUMN_PROFIL_PHOTO="Photo";
        public static final String COLUMN_PROFIL_NOM="Nom";
        public static final String COLUMN_PROFIL_PRENOM="Prenom";
        public static final String COLUMN_PROFIL_AGE="Age";
        public static final String COLUMN_PROFIL_ADRESSE="Adresse";
        public static final String TABLE_PREFERENCES="Preferences";
        public static final String COLUMN_PREFERENCES_PSEUDO="Pseudo";
        public static final String COLUMN_PREFERENCES_TAILLE_P="TailleP";
        public static final String COLUMN_PREFERENCES_TAILLE_H="TailleH";
        public static final String COLUMN_PREFERENCES_POINTURE="Pointure";
        public static final String COLUMN_PREFERENCES_COULEUR="Couleur";
        public static final String COLUMN_PREFERENCES_CENTREI="CentreI";
        public static final String TABLE_USER="USER";
        public static final String COLUMN_USER_PSEUDO="Pseudo";
        public static final String COLUMN_USER_MDP="MDP";
        public static final String TABLE_WL="WL";
        public static final String COLUMN_WL_PSEUDO="Pseudo";
        public static final String COLUMN_WL_NWL="NWL";
        public static final String COLUMN_WL_DESCRIPTION="Description";
        public static final String COLUMN_WL_EDIT="EDIT";
        public static final String COLUMN_WL_IDWL="ID_WL";
    }
    public static final String SQL_CREATE_ENTRIES="CREATE TABLE " + FeedEntry.TABLE_AUTORISATION +"(" +
    FeedEntry.COLUMN_AUTORISATION_PSEUDO + " varchar (20) NOT NULL REFERENCES USER (Pseudo)," +
    FeedEntry.COLUMN_AUTORISATION_IDWL + " VARCHAR (20) NOT NULL REFERENCES WL (ID_WL)," +
    FeedEntry.COLUMN_AUTORISATION_EDIT + " bit NOT NULL" + ")";
    public static final String SQL_CREATE_ENTRIES2="CREATE TABLE " + FeedEntry.TABLE_FRIEND + "(" +
    FeedEntry.COLUMN_FRIEND_PSEUDO + " varchar(20) NOT NULL," +
    FeedEntry.COLUMN_FRIEND_PSEUDO2 + " varchar(20) NOT NULL," +
    FeedEntry.COLUMN_FRIEND_FRIEND + " bit NOT NULL," +
            FeedEntry.COLUMN_FRIEND_REQUEST1+ " bit NOT NULL," +
    FeedEntry.COLUMN_FRIEND_REQUEST2 + " bit NOT NULL " +  ")";
    public static final String SQL_CREATE_ENTRIES7="CREATE TABLE " +  FeedEntry.TABLE_PROFIL  + "("+
            FeedEntry.COLUMN_PROFIL_PSEUDO + " VARCHAR (20) PRIMARY KEY NOT NULL REFERENCES USER (Pseudo)," +
            FeedEntry.COLUMN_PROFIL_NOM + " VARCHAR (20) NOT NULL," +
            FeedEntry.COLUMN_PROFIL_PRENOM + " VARCHAR (20) NOT NULL," +
            FeedEntry.COLUMN_PROFIL_AGE + " VARCHAR (20) NOT NULL," +
            FeedEntry.COLUMN_PROFIL_ADRESSE +" VARCHAR (100)," +
            FeedEntry.COLUMN_PROFIL_PHOTO +" BLOB DEFAULT (NULL)" +")";
    public static final String SQL_CREATE_ENTRIES3="CREATE TABLE " +  FeedEntry.TABLE_ITEM + "(" +
            FeedEntry.COLUMN_ITEM_ID + " varchar (20) PRIMARY KEY NOT NULL," +
            FeedEntry.COLUMN_ITEM_NOM + " varchar (20) NOT NULL,"+
            FeedEntry.COLUMN_ITEM_DESCRIPTION +  " varchar (100) NOT NULL,"+
            FeedEntry.COLUMN_ITEM_PRIX + " varchar (20) NOT NULL," +
            FeedEntry.COLUMN_ITEM_ETAT + " Bit NOT NULL,"+
            FeedEntry.COLUMN_ITEM_IDWL + " VARCHAR (20) REFERENCES WL (ID_WL)" + ")";
    public static final String SQL_CREATE_ENTRIES4="CREATE TABLE " + FeedEntry.TABLE_PREFERENCES +"(" +
            FeedEntry.COLUMN_PREFERENCES_PSEUDO + " VARCHAR (20) PRIMARY KEY NOT NULL REFERENCES USER (Pseudo)," +
            FeedEntry.COLUMN_PREFERENCES_TAILLE_P +  " VARCHAR (20),"+
            FeedEntry.COLUMN_PREFERENCES_TAILLE_H + " VARCHAR (20),"+
            FeedEntry.COLUMN_PREFERENCES_POINTURE + " VARCHAR (20),"+
            FeedEntry.COLUMN_PREFERENCES_COULEUR + " VARCHAR (20),"+
            FeedEntry.COLUMN_PREFERENCES_CENTREI +" VARCHAR (50)" + ")";
    public static final String SQL_CREATE_ENTRIES5="CREATE TABLE "+ FeedEntry.TABLE_USER + "(" +
            FeedEntry.COLUMN_USER_PSEUDO + " VARCHAR (20) PRIMARY KEY NOT NULL,"+
            FeedEntry.COLUMN_USER_MDP + " VARCHAR (20) NOT NULL" +")";
    public static final String SQL_CREATE_ENTRIES6="CREATE TABLE " + FeedEntry.TABLE_WL + "(" +
            FeedEntry.COLUMN_WL_PSEUDO + " VARCHAR (20) NOT NULL REFERENCES USER (Pseudo)," +
            FeedEntry.COLUMN_WL_NWL + " VARCHAR (20) NOT NULL," +
            FeedEntry.COLUMN_WL_DESCRIPTION + " VARCHAR (30)," +
            FeedEntry.COLUMN_WL_EDIT + " BIT NOT NULL," +
            FeedEntry.COLUMN_WL_IDWL + " VARCHAR (20)" +")";
    public static final String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS " + FeedEntry.TABLE_AUTORISATION;
    public static final String SQL_DELETE_ENTRIES2 ="DROP TABLE IF EXISTS " + FeedEntry.TABLE_FRIEND;
    public static final String SQL_DELETE_ENTRIES3 ="DROP TABLE IF EXISTS " + FeedEntry.TABLE_ITEM ;
    public static final String SQL_DELETE_ENTRIES4 ="DROP TABLE IF EXISTS " + FeedEntry.TABLE_PROFIL;
    public static final String SQL_DELETE_ENTRIES5 ="DROP TABLE IF EXISTS " + FeedEntry.TABLE_PREFERENCES;
    public static final String SQL_DELETE_ENTRIES6 ="DROP TABLE IF EXISTS " + FeedEntry.TABLE_USER;
    public static final String SQL_DELETE_ENTRIES7 ="DROP TABLE IF EXISTS " + FeedEntry.TABLE_WL;
    public static final String Create_user="INSERT INTO " + FeedEntry.TABLE_USER+ "\n"+
            "VALUES" +
            "('User1', 'user1'),\n" +
            "('User2', 'user2'),\n" +
            "('User3', 'user3'),\n" +
            "('User4', 'user4'),\n" +
            "('User5', 'user5');";
    public static final String Create_profil ="INSERT INTO " + FeedEntry.TABLE_PROFIL+ "\n"+
            "(Pseudo, Prenom, Nom, Age, Adresse) VALUES " +
            "('User1', 'Avril', 'Lavigne', '20', 'Bastogne'),\n" +
            "('User2', 'Bruce', 'Willis', '68', 'Bois de boulogne'),\n" +
            "('User3', 'Johnny', 'Cadillac', '35', 'Charleroi'),\n" +
            "('User4', 'Anthony', 'Delperdange', '20', 'Liège'),\n" +
            "('User5', 'Ingrid', 'Bettencourt', '20', 'On cherche');";
    public static final String Create_wishlist = "INSERT INTO " + FeedEntry.TABLE_WL+ "\n"+
            "VALUES" +
            "('User1', 'Noel', 'Idee de cadeau pour noel soyez pas radins sinon je change de nom de famille', 1, '1'),\n" +
            "('User1', 'voiture', 'Bolides que j aimerais recevoir pour mes 18 ans, merci papa <3', 0, '2'),\n" +
            "('User1', 'vetements', 'liste de vêtement dont j ai besoin', 0, '5'),\n" +
            "('User1', 'jeux', 'jeux-vidéo qui me plairait', 0, '6'),\n" +
            "('User1', 'mobilier', 'meuble pour le kot', 0, '7'),\n" +
            "('User1', 'ecole', 'fournitures scolaires', 0, '8'),\n" +
            "('User1', 'chaussures', 'Nike ou adidas', 0, '9'),\n" +
            "('User1', 'telephone', 'Samsung please', 0, '10'),\n" +
            "('User1', 'sport', 'vetements athlétisme', 0, '11'),\n" +
            "('User1', 'animal', 'pas de chien', 0, '12'),\n" +
            "('User1', 'anniversaire', 'pas deux fois le même cadeaux svp', 0, '13'),\n" +
            "('User1', 'tableau', 'pour ma galerie d art', 0, '2'),\n" +
            "('User2', 'Noel', 'Idee de cadeau pour noel pas de la quincaille', 0, '3'),\n" +
            "('User3', 'vêtements', 'Contribuez à ma street cred les loosers', 1, '4');";
    public static final String Create_items= "INSERT INTO " + FeedEntry.TABLE_ITEM+ "\n"+
            "VALUES" +
            "('1', 'velo', 'bmx de feu', '300', '1', '1'),\n" +
            "('2', 'ps4', 'en attendant la ps5', '200', '0', '1'),\n" +
            "('3', 'chaussures', 'jordans', '150', '0', '1'),\n" +
            "('4', 'Aventador', 'Pour rouler sur Hollywood bld', '1 000 000', '0', '2');";

    public static final String Create_friends = "INSERT INTO " + FeedEntry.TABLE_FRIEND +"\n"+
            "VALUES" +
            "('User1', 'User2', '1', '0', '0'), \n " +
            "('User1', 'User3', '1', '0', '0'), \n " +
            "('User2', 'User3', '0', '0', '0'), \n " +
            "('User2', 'User5', '0', '0', '0'), \n " +
            "('User2', 'User4', '0', '0', '0'), \n " +
            "('User3', 'User4', '0', '0', '0'), \n " +
            "('User3', 'User5', '0', '0', '0'), \n " +
            "('User4', 'User5', '0', '1', '0'), \n " +
            "('User1', 'User4', '1', '0', '0');" ;
    public static final String Create_autorisation = "INSERT INTO "+ FeedEntry.TABLE_AUTORISATION + "\n" +
            "VALUES"+
            "('User2', '1', '1'), \n " +
            "('User2', '2', '1'), \n " +
            "('User3', '0', '1'), \n " +
            "('User1', '3', '1'), \n " +
            "('User3', '2', '1');";
}

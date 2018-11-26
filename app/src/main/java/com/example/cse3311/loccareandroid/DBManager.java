package com.example.cse3311.loccareandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import java.util.Vector;


public class DBManager extends SQLiteOpenHelper {
    //Create DB
    private static final int Db_VERSION = 1;

    private static final String DB_NAME = "loccare";

    // Table names
    private static final String USER_TABLE = "users";
    private static final String ROOM_TABLE = "rooms";

    // Users Table Columns
    private static final String USER_FIRST_NAME  = "first_name";
    private static final String USER_LAST_NAME  = "last_name";
    private static final String USER_ID = "id";
    private static final String USER_ROLE   = "role";
    private static final String USER_EMAIL  = "email";
    private static final String USER_PASS   = "password";
    private static final String USER_ROOM_CODE = "room_code";
    private static final String USER_LAT = "latitude";
    private static final String USER_LON = "longitude";

    // Room Table Columns
    private static final String ROOM_ID = "id";
    private static final String ROOM_CODE = "room_code";
    private static final String ROOM_NAME = "name";

    public DBManager(Context context) {
        super(context, DB_NAME, null, Db_VERSION);
    }

    private static final String CREATE_TABLE_USER = "CREATE TABLE " + USER_TABLE + "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
             USER_FIRST_NAME + " TEXT," +USER_LAST_NAME + " TEXT," + USER_ROLE + " TEXT," + USER_EMAIL + " TEXT," + USER_PASS + " TEXT, " +
            USER_ROOM_CODE + " TEXT," + USER_LAT + " REAL," + USER_LON + " REAL" /*" FOREIGN KEY(" + ROOM_CODE + ") "*/ + ")";

    private static final String CREATE_TABLE_ROOM = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s CHAR(5), %s TEXT)",
            ROOM_TABLE, ROOM_ID, ROOM_CODE, ROOM_NAME);

    @Override
    public void onCreate(SQLiteDatabase sqLitedb) {
        sqLitedb.execSQL(CREATE_TABLE_ROOM);
        sqLitedb.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLitedb, int i, int i1) {
        sqLitedb.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        sqLitedb.execSQL("DROP TABLE IF EXISTS " + ROOM_TABLE);
        onCreate(sqLitedb);
    }

    public void addNewUser(UserModel user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_FIRST_NAME, user.getFirstName());
        values.put(USER_LAST_NAME, user.getLastName());
        values.put(USER_ROLE, user.getRole());
        values.put(USER_EMAIL, user.getEmail());
        values.put(USER_PASS, user.getPassword());
        db.insert(USER_TABLE, null, values);
        db.close();
    }

    public void addNewRoom(RoomModel room) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ROOM_NAME, room.getName());
        values.put(ROOM_CODE, room.getCode());
        db.insertOrThrow(ROOM_TABLE, null, values);
        db.close();
    }

    public UserModel retrieveUser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = String.format("SELECT * from %s WHERE %s = \"%s\";", USER_TABLE, USER_EMAIL, email);
        Cursor cursor = db.rawQuery(query, null);

        UserModel user = new UserModel();

        if (cursor.moveToFirst()) {
            user.setEmail(cursor.getString(cursor.getColumnIndex(USER_EMAIL)));
            user.setFirstName(cursor.getString(cursor.getColumnIndex(USER_FIRST_NAME)));
            user.setLastName(cursor.getString(cursor.getColumnIndex(USER_LAST_NAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(USER_PASS)));
            user.setRole(cursor.getString(cursor.getColumnIndex(USER_ROLE)));
            user.setRoomCode(cursor.getString(cursor.getColumnIndex(USER_ROOM_CODE)));
        } else {
            user = null;
        }
        cursor.close();
        return user;
    }

    public boolean roomExists(String roomCode) {
        boolean exists = false;
        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format("SELECT * from %s WHERE %s = \"%s\";", ROOM_TABLE, ROOM_CODE, roomCode);
        Cursor cursor = db.rawQuery(query, null);

        RoomModel room = new RoomModel();

        if (cursor.moveToFirst()) {
//            room.setName(cursor.getString(cursor.getColumnIndex(USER_EMAIL)));
//            room.setCode(cursor.getString(cursor.getColumnIndex(USER_FIRST_NAME)));
            exists = true;
        }

        cursor.close();
        return exists;
    }

    public RoomModel retrieveRoom(String roomCode) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format("SELECT * from %s WHERE %s = \"%s\";", ROOM_TABLE, ROOM_CODE, roomCode);
        Cursor cursor = db.rawQuery(query, null);

        RoomModel room = new RoomModel();

        if (cursor.moveToFirst()) {
            room.setName(cursor.getString(cursor.getColumnIndex(ROOM_NAME)));
            room.setCode(cursor.getString(cursor.getColumnIndex(ROOM_CODE)));
        }

        cursor.close();
        return room;
    }

    public UserModel updateUserRoomCode(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER_ROOM_CODE, user.getRoomCode());
        String whereClause = String.format("%s=?", USER_EMAIL);
        String[] whereArgs = new String[] {
                user.getEmail()
        };

        db.update(USER_TABLE, values, whereClause, whereArgs);
        db.close();
        return user;
    }

    public UserModel updateUserLocation(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER_LAT, user.getLatitude());
        values.put(USER_LON, user.getLongitude());
        String whereClause = String.format("%s=?", USER_EMAIL);
        String[] whereArgs = new String[] {
                user.getEmail()
        };

        db.update(USER_TABLE, values, whereClause, whereArgs);
        db.close();
        return user;
    }

    public Vector<UserModel> getUsersByRoom(String roomCode) {

        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format("SELECT * from %s WHERE %s = \"%s\";", USER_TABLE, USER_ROOM_CODE, roomCode);
        Cursor cursor = db.rawQuery(query, null);

        Vector<UserModel> users = new Vector<UserModel>();

        if (cursor.moveToFirst()) {
            do {
                UserModel user = new UserModel();
                user.setFirstName(cursor.getString(cursor.getColumnIndex(USER_FIRST_NAME)));
                user.setLastName(cursor.getString(cursor.getColumnIndex(USER_LAST_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(USER_EMAIL)));
                user.setRole(cursor.getString(cursor.getColumnIndex(USER_ROLE)));
                user.setRoomCode(cursor.getString(cursor.getColumnIndex(USER_ROOM_CODE)));

                users.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return users;
    }
}

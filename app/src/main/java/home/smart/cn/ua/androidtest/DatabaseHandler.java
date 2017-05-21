package home.smart.cn.ua.androidtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "accountsManager";
    private static final String TABLE_ACCOUNTS = "accounts";
    private static final String KEY_ID = "id";
    private static final String KEY_LOGIN = "login";
    private static final String KEY_MD5PASSWORD = "md5password";

    private static DatabaseHandler instance;

    public static synchronized DatabaseHandler getInstance(Context context)
    {
        if (instance == null)
            instance = new DatabaseHandler(context);
        return instance;
    }

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ACCOUNTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_LOGIN + " TEXT,"
                + KEY_MD5PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN, account.getLogin());
        values.put(KEY_MD5PASSWORD, account.getMd5password());

        db.insert(TABLE_ACCOUNTS, null, values);
        db.close();
    }

    public int updatePassword(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN, account.getLogin());
        values.put(KEY_MD5PASSWORD, account.getMd5password());

        return db.update(TABLE_ACCOUNTS, values, KEY_LOGIN + " = ?",
                new String[]{String.valueOf(account.getLogin())});
    }

    public void deleteAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACCOUNTS, KEY_LOGIN + " = ?", new String[]{String.valueOf(account.getLogin())});
        db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACCOUNTS, null, null);
        db.close();
    }

    public int getAccountsCount() {
        String countQuery = "SELECT 1 FROM " + TABLE_ACCOUNTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    public boolean isAccountExist(Account account) {
        boolean isExist = false;
        String countQuery = "SELECT 1 FROM " + TABLE_ACCOUNTS + " WHERE " + KEY_LOGIN + "=\"" + account.getLogin() + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.getCount() > 0) isExist = true;
        cursor.close();
        return isExist;
    }

    public boolean checkAccount(Account account) {
        boolean isOK = false;
        String countQuery = "SELECT " + KEY_MD5PASSWORD + " FROM " + TABLE_ACCOUNTS + " WHERE " + KEY_LOGIN + "=\"" + account.getLogin() + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.toString().equalsIgnoreCase(account.getMd5password())) isOK = true;
        cursor.close();
        return isOK;
    }


}
package com.example.soukaina.shopping_app_advanced_java_FINAL;
// SOUKAINA SALIHI
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productsDB.db";
    public static final String TABLE_PRODUCTS = "productsTable";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productName";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_PRIORITY = "priority";

    // create a constructor (We need to pass database information along to superclass)
    public MyDatabaseHandler(Context context, String name,
                             SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    // create the table columns that will home each item information
    @Override
    public void onCreate(SQLiteDatabase db) {
        // string query
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_PRODUCTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_PRODUCTNAME + " TEXT,"
                + COLUMN_PRICE + " REAL,"
                + COLUMN_QUANTITY + " INTEGER,"
                + COLUMN_PRIORITY + " INTEGER" + ")";
        // create the table using the string query CREATE_PRODUCTS_TABLE
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //Add a new row to the database by extracting the information from the passed object
    public void addProduct(Item item) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, item.getName());
        values.put(COLUMN_PRICE, item.getPrice());
        values.put(COLUMN_QUANTITY, item.getQuantity());
        values.put(COLUMN_PRIORITY, item.getPriority());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }
    // delete the database
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_PRODUCTS);
        db.close();
    }

    // get all saved items from database into a string
    // ( this will be used by the function called printDatabase in the Main activity)
    public String databaseToString() {
        //  dbString is data holder
        String dbString = "";
        dbString += String.format("%8s%22s%18s%18s\n", "Name", "Price", "Quantity", "Priority");

        //regular query
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // get items data going trough each column
        if (cursor.moveToFirst()) {
            do {
                dbString += String.format("%10s%22s%20s%20s", cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCTNAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_PRICE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_QUANTITY)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_PRIORITY)));

                dbString += "\n";

            } while (cursor.moveToNext());
        }
        db.close();

        return dbString;
    }


}
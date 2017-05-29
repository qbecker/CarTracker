package qbecker.cartracker;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
/**
 * Created by qbecker on 5/28/17.
 */

public class CarDB extends SQLiteOpenHelper{
    private static final boolean debugon = false;
    private static final int DATABASE_VERSION = 3;
    private static String dbName = "cardb";
    private String dbPath;
    private SQLiteDatabase crsDB;
    private final Context context;

    public CarDB(Context context) {
        super(context, dbName, null, DATABASE_VERSION);
        this.context = context;
        dbPath = context.getFilesDir().getPath() + "/";
        android.util.Log.d(this.getClass().getSimpleName(), "dbpath: " + dbPath);
    }

    public void createDB() throws IOException {
        this.getReadableDatabase();
        try {
            copyDB();
        } catch (IOException e) {
            android.util.Log.w(this.getClass().getSimpleName(),
                    "createDB Error copying database " + e.getMessage());
        }
    }
    private boolean checkDB() {    //does the database exist and is it initialized?
        SQLiteDatabase checkDB = null;
        boolean ret = false;
        try {
            String path = dbPath + dbName + ".db";
            File aFile = new File(path);
            if (aFile.exists()) {
                checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
                if (checkDB != null) {
                    Cursor tabChk = checkDB.rawQuery("SELECT name FROM cars;", null);
                    boolean crsTabExists = false;
                    if (tabChk == null) {
                    } else {
                        tabChk.moveToNext();
                        crsTabExists = !tabChk.isAfterLast();
                    }
                    if (crsTabExists) {
                        Cursor c = checkDB.rawQuery("SELECT * FROM cars", null);
                        c.moveToFirst();
                        while (!c.isAfterLast()) {
                            String NAME = c.getString(0);
                            c.moveToNext();
                        }
                        ret = true;
                    }
                }
            }
        } catch (SQLiteException e) {
            android.util.Log.w("carDB->checkDB", e.getMessage());
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return ret;
    }

    public void copyDB() throws IOException {
        try {
            if (!checkDB()) {
                // only copy the database if it doesn't already exist in my database directory
                InputStream ip = context.getResources().openRawResource(R.raw.cardb);
                // make sure the database path exists. if not, create it.
                File aFile = new File(dbPath);
                if (!aFile.exists()) {
                    aFile.mkdirs();
                }
                String op = dbPath + dbName + ".db";
                OutputStream output = new FileOutputStream(op);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = ip.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
                output.flush();
                output.close();
                ip.close();
            }
        } catch (IOException ex) {
            android.util.Log.w(this.getClass().getSimpleName(), "IOException: " + ex.getMessage());
        }
    }


    public SQLiteDatabase openDB() throws SQLException {
        String myPath = dbPath + dbName + ".db";
        if (checkDB()) {
            crsDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        } else {
            try {
                this.copyDB();
                crsDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            } catch (Exception ex) {
                android.util.Log.w(this.getClass().getSimpleName(), "unable to copy and open db: " + ex.getMessage());
            }
        }
        return crsDB;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

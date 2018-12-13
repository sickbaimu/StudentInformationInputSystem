package baton.studentinformationinputsystem;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String CREATE_STUDENT = "create table Student ("
            +"id integer primary key autoincrement,"
            +"name text,"
            +"student_id text,"
            +"id_number text,"
            +"sex text,"
            +"native_place text,"
            +"birthday text,"
            +"school text,"
            +"major text,"
            +"student_class text,"
            +"phone text,"
            +"email text,"
            +"weixin_number text,"
            +"special_skill text)";
    private Context mContext;
    MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext =context;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_STUDENT);
        Toast.makeText(mContext,"Create SQLite succeeded",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){}
}

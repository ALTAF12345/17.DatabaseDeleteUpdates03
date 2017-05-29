package org.sawhers.altafhussain.databasedeleteupdates03.DATABASE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.sawhers.altafhussain.databasedeleteupdates03.MODEL.Student;

import java.util.ArrayList;

/**
 * Created by ALTAFHUSSAIN on 12/31/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="mystudent.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_STUDENT="newstudent";
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_COURSE="course";
    private static final String KEY_TOTAL_FEE="totalfee";
    private static final String KEY_FEE_PAID="feepaid";
    private static final String KEY_CANTACT="cantact";
    private static final String KEY_ADDRESS="address";

//CREATE TABLE TABALENAME ( ID, NAME, COURSE ,totalfee);//etc
    private static final String CREATE_TABLE_STUDENT="CREATE TABLE "+ TABLE_STUDENT + " ( "

                              +KEY_ID +" Integer Primary Key Autoincrement, "
                              +KEY_NAME + " Text, "
                              +KEY_COURSE + " Text, "
                              +KEY_TOTAL_FEE + " Integer, "
                              +KEY_FEE_PAID + " Integer, "
                              +KEY_CANTACT + " Interger, "
                              +KEY_ADDRESS + " Text "
                                +" ); ";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+CREATE_TABLE_STUDENT);
        onCreate(sqLiteDatabase);


    }

    public long saveStudent(Student student){
        long result=-1;

        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,student.getName());
        values.put(KEY_COURSE,student.getCourse());
        values.put(KEY_TOTAL_FEE,student.getTotalFee());
        values.put(KEY_FEE_PAID,student.getFeePaid());
        values.put(KEY_CANTACT,student.getContact());
        values.put(KEY_ADDRESS,student.getAddress());

        result=sqLiteDatabase.insert(TABLE_STUDENT,null,values);
        Log.e("INSERT","DATA IS ="+result);

        return result;
    }





    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> studentsList=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor c =db.rawQuery("select * from "+TABLE_STUDENT,null);
        if (c.moveToFirst()){
            do {


                int id=c.getInt(c.getColumnIndex(KEY_ID));
                String name=c.getString(c.getColumnIndex(KEY_NAME));
                String course=c.getString(c.getColumnIndex(KEY_COURSE));
                int totalFee=c.getInt(c.getColumnIndex(KEY_TOTAL_FEE));
                int feePaid=c.getInt(c.getColumnIndex(KEY_FEE_PAID));
                int cantact=c.getInt(c.getColumnIndex(KEY_CANTACT));
                Log.e("ALTAF","CANTACT IS "+cantact);
                String address=c.getString(c.getColumnIndex(KEY_ADDRESS));


                Student s = new Student(id,name,course,totalFee,feePaid,cantact,address);

                studentsList.add(s);


            }while (c.moveToNext());

        }
        return studentsList;
    }

    public int upDatesStudent(Student s) {
        int result=0;
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,s.getName());
        values.put(KEY_COURSE,s.getCourse());
        values.put(KEY_TOTAL_FEE,s.getTotalFee());
        values.put(KEY_FEE_PAID,s.getFeePaid());
        values.put(KEY_CANTACT,s.getContact());
        values.put(KEY_ADDRESS,s.getAddress());
        result=sqLiteDatabase.update(TABLE_STUDENT, values , KEY_ID + "=?" , new String[]{String.valueOf(s.getId())});
        //result=sqLiteDatabase.update(TABLE_STUDENT, values , null,null);
        //null,null mean we dont give any condition so all the database are update with these values
                //sqLiteDatabase.update(TABLE_STUDENT,values, KEY_ID + "=?" ,new String[]{String.valueOf(s.getId())});
//KEY_ID + "=?" they update the the record which keyid is =? but the id at the same time is we give the ? mean we provide the
        //id in the second peramter in the form of string like now we first get the id from student b/c we pass
        //the complet object from student list activity and we also define the the setter and getter of
        //the student obj
        //the bottom line is =? mean we get the id from student s and give in sql query in where condition


        return result;
    }

    public int deleteStudent(String id ){

        int result=0;
        try {
            SQLiteDatabase db=this.getWritableDatabase();
           result=db.delete(TABLE_STUDENT, KEY_ID + "=?" , new String[]{id} );

            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e("ERROR","deleteStudent Wrong Table name ERROR");
        }
    return result;
    }
}

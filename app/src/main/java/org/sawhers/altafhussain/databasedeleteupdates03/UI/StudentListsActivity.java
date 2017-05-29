package org.sawhers.altafhussain.databasedeleteupdates03.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.sawhers.altafhussain.databasedeleteupdates03.DATABASE.DBHelper;
import org.sawhers.altafhussain.databasedeleteupdates03.MODEL.Student;
import org.sawhers.altafhussain.databasedeleteupdates03.R;

import java.util.ArrayList;

public class StudentListsActivity extends AppCompatActivity {
ListView lvStudent;
    ArrayAdapter<Student> studentArrayAdapter;
    ArrayList<Student> studentArrayList;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_lists);
        lvStudent=(ListView)findViewById(R.id.listView);
        dbHelper = new DBHelper(this);
        //we add these code onResume for update the ui for update
        /*studentArrayList = new ArrayList<>();
        studentArrayList=dbHelper.getAllStudents();
        studentArrayAdapter=new ArrayAdapter<>(StudentListsActivity.this,android.R.layout.simple_list_item_1,studentArrayList);
        lvStudent.setAdapter(studentArrayAdapter);*/


//+++++++++++++++++++++++++++++++++++++++++ONCLICK LISTNER+++++++++++++++++++

        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //studentArrayList.get(position);//now we get value from arraylist through index
                Student s=studentArrayList.get(position);//now the selected value(name) we give to student object s
                //now we pass the complete object to other activity like StudentDetailActivity
                Bundle bundle=new Bundle();
                //now there is not any builtin ftn to pass complete object but we make the object first
                //serilizable and then we write the rtn bundle.putSerializable("KEYNAME",s);
                bundle.putSerializable("STUDENT" , s);
                Log.e("ASDF","STD listview ID ="+s.getId());
                Intent intent=new Intent(StudentListsActivity.this,StudentDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });
//**********************************************ONlong click************************
       lvStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
               AlertDialog.Builder builder1 = new AlertDialog.Builder(StudentListsActivity.this);
               builder1.setMessage("Are you sure want to delete");
                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                               Student s=studentArrayList.get(position);
                               int studentid=s.getId();
                               int result= dbHelper.deleteStudent(String.valueOf(studentid));

                               if (result>0){
                                   Toast.makeText(StudentListsActivity.this, "Student Deleted", Toast.LENGTH_SHORT).show();
                                   studentArrayList.remove(s);
                                   studentArrayAdapter.notifyDataSetChanged();
                               }

                               else{
                                   Toast.makeText(StudentListsActivity.this, "Student fail to delete", Toast.LENGTH_SHORT).show();
                               }


                           }
                       });

               builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                               dialog.cancel();
                           }
                       });

               AlertDialog alert11 = builder1.create();
               alert11.show();

               return true;//mean that we don't call onclicklistner here we call longonclicklistner
               //b/c long click come in onclicklistner if "false" first come onclicklistner not longonclick listner

           }
       });


    }

    @Override
    protected void onResume() {
        super.onResume();

        studentArrayList = new ArrayList<>();
        studentArrayList=dbHelper.getAllStudents();
        studentArrayAdapter=new ArrayAdapter<>(StudentListsActivity.this,android.R.layout.simple_list_item_1,studentArrayList);
        lvStudent.setAdapter(studentArrayAdapter);
    }
}

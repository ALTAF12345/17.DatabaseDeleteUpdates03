package org.sawhers.altafhussain.databasedeleteupdates03.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.sawhers.altafhussain.databasedeleteupdates03.DATABASE.DBHelper;
import org.sawhers.altafhussain.databasedeleteupdates03.MODEL.Student;
import org.sawhers.altafhussain.databasedeleteupdates03.R;

import static android.R.attr.id;
import static org.sawhers.altafhussain.databasedeleteupdates03.R.id.etAddress;
import static org.sawhers.altafhussain.databasedeleteupdates03.R.id.etCantact;
import static org.sawhers.altafhussain.databasedeleteupdates03.R.id.etCantactD;

public class StudentDetailActivity extends AppCompatActivity {
    EditText etNameD,etCourseD,etTotalFeeD,etFeePaidD,etContactD,etAddressD;
    Button btnUpdate;
    int studentID;//to update the record we need to get id also
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        dbHelper=new DBHelper(StudentDetailActivity.this);

        etNameD= (EditText) findViewById(R.id.etNameD);
        etCourseD= (EditText) findViewById(R.id.etCourseD);
        etTotalFeeD= (EditText) findViewById(R.id.etTotalFeeD);
        etFeePaidD= (EditText) findViewById(R.id.etFeePaidD);
        etContactD= (EditText) findViewById(etCantactD);
        etAddressD= (EditText) findViewById(R.id.etAddressD);
        btnUpdate= (Button) findViewById(R.id.btnUpdateD);
        //******************************Here we get the bundle and set value to edittex
        Bundle b=getIntent().getExtras();
        Student s= (Student) b.getSerializable("STUDENT");

        //here now the value in s object , now we pass the value to its edittextview
        etNameD.setText(s.getName());
        etCourseD.setText(s.getCourse());
        etTotalFeeD.setText(String.valueOf(s.getTotalFee()));
        etFeePaidD.setText(String.valueOf(s.getFeePaid()));
        etContactD.setText(String.valueOf(s.getContact()));
        etAddressD.setText(s.getAddress());
        //here we need to get id from student obj they need in updation
        studentID=s.getId();
        Log.e("ASDF","STD DETAILD ID ="+studentID);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student s=getStudentFromFileds();
                int result =dbHelper.upDatesStudent(s);
                if (result>0){
                    Toast.makeText(StudentDetailActivity.this, "Record updtes", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(StudentDetailActivity.this, "Updation is Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private Student getStudentFromFileds(){// this used for updation
        String name=etNameD.getText().toString().trim();
        String course=etCourseD.getText().toString().trim();
        String totalFee=etTotalFeeD.getText().toString().trim();
        String feePaid=etFeePaidD.getText().toString().trim();
        String cantact=etContactD.getText().toString().trim();
        String address=etAddressD.getText().toString().trim();


        Student student = new Student(studentID ,name, course, Integer.parseInt(totalFee), Integer.parseInt(feePaid),
                Integer.parseInt(cantact),address);
        return student;
    }
}

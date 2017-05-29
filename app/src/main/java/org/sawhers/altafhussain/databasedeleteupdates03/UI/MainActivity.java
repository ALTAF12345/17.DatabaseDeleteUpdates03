package org.sawhers.altafhussain.databasedeleteupdates03.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.sawhers.altafhussain.databasedeleteupdates03.DATABASE.DBHelper;
import org.sawhers.altafhussain.databasedeleteupdates03.MODEL.Student;
import org.sawhers.altafhussain.databasedeleteupdates03.R;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    EditText etName,etCourse,etTotalFee,etFeePiad,etCantact,etAddress;
    Button btnSave,btnShowAll;

    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName= (EditText) findViewById(R.id.etName);
        etCourse= (EditText) findViewById(R.id.etCourse);
        etTotalFee= (EditText) findViewById(R.id.etTotalFee);
        etFeePiad= (EditText) findViewById(R.id.etFeePaid);
        etCantact= (EditText) findViewById(R.id.etCantact);
        etAddress= (EditText) findViewById(R.id.etAddress);
        btnShowAll= (Button) findViewById(R.id.btnShowAll);

        btnSave= (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Student student = getStudentFromFileds();
                if (student!=null) {

                    dbHelper = new DBHelper(MainActivity.this);
                    long result = dbHelper.saveStudent(student);
                    if (result != -1) {
                        Toast.makeText(MainActivity.this, "Record added with ID " + result, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Record not added", Toast.LENGTH_SHORT).show();
                    }

                }







            }
        });
        btnShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,StudentListsActivity.class));
            }
        });

    }
    private Student getStudentFromFileds(){
        String name=etName.getText().toString().trim();
        String course=etCourse.getText().toString().trim();
        String totalFee=etTotalFee.getText().toString().trim();
        String feePaid=etFeePiad.getText().toString().trim();
        String cantact=etCantact.getText().toString().trim();
        String address=etAddress.getText().toString().trim();


        Student student = new Student(name, course, Integer.parseInt(totalFee), Integer.parseInt(feePaid),
                //Integer.parseInt(cantact),address);
                Long.parseLong(cantact) ,address);
        return student;
    }
}

package com.example.notestaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText Et1,Et2;
    String word="",mean="";
    boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb =new DatabaseHelper(this);
        Et1 = findViewById(R.id.noid);
        Et2 = findViewById(R.id.notes);
    }
    public void addword(View view) {
        flag=false;
        word=Et1.getText().toString();
        mean=Et2.getText().toString();
        if((word.length()!=0)&&(mean.length()!=0)) {
            flag = myDb.insertData(word, mean);
            if (flag == false)
                Toast.makeText(this, "Word could not be added!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Word added!", Toast.LENGTH_SHORT).show();
        }
        else
            showMessage("Err!","Empty word not accepted");
    }

    public void showMessage(String Title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.show();
    }

    public void view_all_asc(View view) {
        Cursor res=myDb.get_all_data_hist_asc();
        if(res.getCount()==0){
            showMessage("Error","Nothing Fnd!");
            return;
        }
        StringBuffer buffer=new StringBuffer();

        while (res.moveToNext()){
            buffer.append(" Word:- "+res.getString(0)+"\n");
            buffer.append(" Meaning/Note:- \n"+res.getString(1)+"\n\n\n");

        }
        showMessage("word/note_id arranged in alphabetic order",buffer.toString());
    }
}

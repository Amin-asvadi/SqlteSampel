package com.example.novindevelopers.database_project;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.novindevelopers.database_project.Model.Data;

public class Update_Activity extends AppCompatActivity
{
    private TextView textSave;
    private EditText edName,edFamily,edPhone;
    Context context =this;
    private DbSQL dbSQL = new DbSQL(context);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_);

        edName =(EditText)findViewById(R.id.edName);
        edFamily =(EditText)findViewById(R.id.edFamily);
        edPhone =(EditText)findViewById(R.id.edPhONE);
        textSave =(TextView)findViewById(R.id.textSave);


        final String id = getIntent().getStringExtra("id");
        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
        edName.setText(getIntent().getStringExtra("name"));
        edFamily.setText(getIntent().getStringExtra("family"));
        edPhone.setText(getIntent().getStringExtra("phone"));
        final String date = getIntent().getStringExtra("date");

        textSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dbSQL.Update(new Data(edName.getText().toString(),edFamily.getText().toString()
                        ,edPhone.getText().toString(),date),Integer.parseInt(id));
                Toast.makeText(getApplicationContext(), "اعمال تغییرات", Toast.LENGTH_SHORT).show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    startActivity(new Intent(Update_Activity.this,Show_Activity.class));
                }
            },500);
            }
        });

    }


}

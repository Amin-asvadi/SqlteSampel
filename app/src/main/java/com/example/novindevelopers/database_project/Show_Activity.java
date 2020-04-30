package com.example.novindevelopers.database_project;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.novindevelopers.database_project.Model.Data;

import java.util.ArrayList;

public class Show_Activity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    AdapterData adapter;
    private ArrayList<Data> data = new ArrayList<>();
    Context context = this;
    private DbSQL dbSQL = new DbSQL(context);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_);

        data = dbSQL.ShowData();
        recyclerView = (RecyclerView) findViewById(R.id.recyShow);
        adapter = new AdapterData(data, Show_Activity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(Show_Activity.this));
        recyclerView.setAdapter(adapter);

    }
}

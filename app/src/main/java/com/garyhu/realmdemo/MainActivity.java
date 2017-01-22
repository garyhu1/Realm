package com.garyhu.realmdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Button addBtn;
    private Button deleteBtn;
    private Button queryBtn;
    private ArrayAdapter<String> mAdapter;
    private List<String> names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list_view);
        addBtn = (Button) findViewById(R.id.add_data);
        deleteBtn = (Button)findViewById(R.id.delete_data);
        queryBtn = (Button) findViewById(R.id.query_data);

        mAdapter = new ArrayAdapter<String>(MainActivity.this,R.layout.item_view,R.id.name,names);
        listView.setAdapter(mAdapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryData();
            }
        });
    }

    public void addData(){
        for (int i = 0; i < 20; i++) {
            Student s = new Student();
            s.setTitle("张三"+i);
            RealmHelper.getInstance().insertStudent(s);
        }
    }

    public void queryData(){
        names.clear();
        List<Student> studentList = RealmHelper.getInstance().getStudentList();
        for (int i = 0; i < studentList.size(); i++) {
            names.add(studentList.get(i).getTitle());
        }
        mAdapter.notifyDataSetChanged();
    }
}

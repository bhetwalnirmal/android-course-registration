package com.test.androittest12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class ViewSelectedCourseActivity extends AppCompatActivity {
    ListView courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_course);
        courseList = (ListView) findViewById(R.id.lvCourseList);

        SelectedCourseListAdapter adapter = new SelectedCourseListAdapter(this);
        courseList.setAdapter(adapter);
    }
}
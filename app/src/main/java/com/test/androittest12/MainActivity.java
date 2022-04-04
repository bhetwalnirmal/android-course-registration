package com.test.androittest12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.test.androittest12.models.Course;
import com.test.androittest12.models.CourseChoices;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // array to hold course list
    ArrayList<Course> courses = new ArrayList<>();
    // array to hold my course list
    ArrayList<Course> myCourses = new ArrayList<>();
    // array to hold course choices
    ArrayList<CourseChoices> courseChoices = new ArrayList<>();

    // array to display on Spinners
    ArrayList<String> coursesString = new ArrayList<>();
    ArrayList<String> myCoursesString = new ArrayList<>();
    ArrayList<String> coursesChoiceString = new ArrayList<>();

    // layout variables
    Spinner spCourseChoices, spCoursesMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillData();
        spCourseChoices = (Spinner) findViewById(R.id.spCourses);
        spCoursesMenu = (Spinner) findViewById(R.id.spCoursesMenu);

        // initializing the course choice spinner
        ArrayAdapter<String> courseChoiceAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, coursesChoiceString);
        spCourseChoices.setAdapter(courseChoiceAdapter);

        // initializing the course choice menu spinner
        ArrayAdapter<String> courseMenuAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, coursesString);
        spCoursesMenu.setAdapter(courseMenuAdapter);
    }

    protected void fillData () {
        courses.add(new Course("Java", 1300, 6));
        courses.add(new Course("Swift", 1500, 5));
        courses.add(new Course("iOS", 1350, 5));
        courses.add(new Course("Android", 1400, 7));
        courses.add(new Course("Database", 1000, 4));

        courseChoices.add(new CourseChoices("Graduated", 1));
        courseChoices.add(new CourseChoices("Ungraduated", 2));

        for (Course course : courses) {
            coursesString.add(course.getName());
        }

        for (CourseChoices courseChoice : courseChoices) {
            coursesChoiceString.add(courseChoice.getName());
        }
    }
}
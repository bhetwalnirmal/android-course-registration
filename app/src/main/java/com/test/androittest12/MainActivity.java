package com.test.androittest12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.test.androittest12.models.Course;
import com.test.androittest12.models.CourseChoices;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // array to hold course list
    ArrayList<Course> courses = new ArrayList<>();
    // array to hold my course list
    public static ArrayList<Course> myCourses = new ArrayList<>();
    // array to hold course choices
    ArrayList<CourseChoices> courseChoices = new ArrayList<>();

    // array to display on Spinners
    ArrayList<String> coursesString = new ArrayList<>();
    ArrayList<String> myCoursesString = new ArrayList<>();
    ArrayList<String> coursesChoiceString = new ArrayList<>();

    // layout variables
    Spinner spCourseChoices, spCoursesMenu;
    Button addCourse, viewSelectedCourse;
    TextView tvTotalFee, tvTotalHours, tvCourseFee, tvCourseHours;
    CheckBox cbAccomodation, cbMedical;
    double totalFee;
    int totalHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillData();
        spCourseChoices = (Spinner) findViewById(R.id.spCourses);
        spCoursesMenu = (Spinner) findViewById(R.id.spCoursesMenu);
        addCourse = (Button) findViewById(R.id.btnAddCourse);
        tvTotalHours = (TextView) findViewById(R.id.tvTotalHours);
        tvTotalFee = (TextView) findViewById(R.id.tvTotalFees);
        cbAccomodation = (CheckBox) findViewById(R.id.checkbox_accomodation);
        cbMedical = (CheckBox) findViewById(R.id.checkbox_medical_insurance);
        tvCourseFee = (TextView) findViewById(R.id.tvCourseFee);
        tvCourseHours = (TextView) findViewById(R.id.tvCourseHours);
        viewSelectedCourse = (Button) findViewById(R.id.btnViewSelectedCourse);

        // initializing the course choice spinner
        ArrayAdapter<String> courseChoiceAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, coursesChoiceString);
        spCourseChoices.setAdapter(courseChoiceAdapter);

        // initializing the course choice menu spinner
        ArrayAdapter<String> courseMenuAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, coursesString);
        spCoursesMenu.setAdapter(courseMenuAdapter);

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courseName = (String) spCoursesMenu.getSelectedItem();
                String courseChoice = (String) spCourseChoices.getSelectedItem();

                Course course = getCourseByName(courseName);
                boolean isAlreadySelected = false;

                for (Course myCourse : myCourses) {
                    if (myCourse.getName().equals(courseName)) {
                        isAlreadySelected = true;
                        break;
                    }
                }

                boolean isValid = checkCourseHours(courseChoice, course.getHours());

                if (!isValid) {
                    Toast.makeText(MainActivity.this, "You cannot add this course as it exceeds the allowed hours.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isAlreadySelected) {
                    myCourses.add(course);
                } else {
                    Toast.makeText(MainActivity.this, "Course is already added to the course list", Toast.LENGTH_SHORT).show();
                }

                updateTotal();
            }
        });

        viewSelectedCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewSelectedCourseActivity.class);
                intent.putExtra("selectedCourses", myCourses);
                startActivity(intent);
            }
        });

        cbAccomodation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbAccomodation.isChecked()) {
                    cbAccomodation.setChecked(true);
                    totalFee += 1000;
                } else {
                    cbAccomodation.setChecked(false);
                    totalFee -= 1000;
                }

                updateTotal();
            }
        });

        cbMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbMedical.isChecked()) {
                    cbMedical.setChecked(true);
                    totalFee += 700;
                } else {
                    cbMedical.setChecked(false);
                    totalFee -= 700;
                }

                updateTotal();
            }
        });

        spCoursesMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                Course course = courses.get(position);

                tvCourseFee.setText("$ " + course.getFee());
                tvCourseHours.setText(course.getHours() + " Hours");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
                return;
            }

        });
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

    protected Course getCourseByName (String name) {
        for (Course course : courses) {
            if (course.getName() == name) {
                return course;
            }
        }

        return null;
    }

    protected void updateTotal () {
        double fee = 0;
        int hours = 0;

        for (Course myCourse : myCourses) {
            fee += myCourse.getFee();
            hours += myCourse.getHours();
        }

        totalHours = hours;
        totalFee = fee;

        if(cbAccomodation.isChecked()) {
            totalFee += 1000;
        }

        if (cbMedical.isChecked()) {
            totalFee += 700;
        }

        tvTotalFee.setText("$ " + totalFee);
        tvTotalHours.setText("" + totalHours + " Hours");
    }

    // check if the course hours does not exceed the total allowed hours
    protected boolean checkCourseHours (String courseChoice, int newHours) {
        CourseChoices myChoice = null;
        int allowedHours = 0;

        for (CourseChoices choice : courseChoices) {
            if (choice.getName().equals(courseChoice)) {
                myChoice = choice;
            }
        }

        if (myChoice != null) {
            if (myChoice.getType() == 1) {
                allowedHours = 21;
            } else {
                allowedHours = 19;
            }
        }

        int totalHours = 0;
        for (Course myCourse : myCourses) {
            totalHours += myCourse.getHours();
        }

        return allowedHours >= (totalHours + newHours) ? true : false;
    }
}
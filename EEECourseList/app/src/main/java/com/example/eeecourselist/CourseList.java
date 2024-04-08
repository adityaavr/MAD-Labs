package com.example.eeecourselist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CourseList extends AppCompatActivity {

    // course holder as data model for the list tile to follow
    static class CourseHolder {
        ImageView courseImage;
        TextView courseTitle;
        TextView courseDesc;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Create a list of Courses
        List<Courses> coursesList = new ArrayList<>();

        // Create instances of Courses with hardcoded data
        Courses course1 = new Courses();
        course1.setCourseCode("S90");
        course1.setCourseTitle("Aerospace Electronics (S90)");
        course1.setCourseDesc("Highly recognised Aerospace Electronics diploma course to develop future-ready engineers for growing Singapore as a Smart Aviation Hub");
        coursesList.add(course1);

        Courses course2 = new Courses();
        course2.setCourseCode("S53");
        course2.setCourseTitle("Computer Engineering (S53)");
        course2.setCourseDesc("Comprehensive and highly sought-after Computing diploma course to develop engineers who can devise innovation solutions for a Smart Nation");
        coursesList.add(course2);

        // Set up the ListView and ArrayAdapter
        ListView listView = findViewById(R.id.courses);
        ArrayAdapter<Courses> adapter = new ArrayAdapter<Courses>(this, R.layout.row, R.id.courseTitle, coursesList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // Get the current row view
                View row = convertView;
                // Holder for efficient view recycling
                CourseHolder holder;

                // Check if the row (convertView) is null
                if (row == null) {
                    // if convertView is null, create a new CourseHolder
                    row = getLayoutInflater().inflate(R.layout.row, parent, false);
                    holder = new CourseHolder();
                    // Initialize references to views in the row
                    holder.courseImage = row.findViewById(R.id.courseImage);
                    holder.courseTitle = row.findViewById(R.id.courseTitle);
                    holder.courseDesc = row.findViewById(R.id.courseDesc);
                    // Set the holder as a tag for the row view
                    row.setTag(holder);
                } else {
                    // if convertView is not null, reuse the existing CourseHolder
                    holder = (CourseHolder) row.getTag();
                }

                // Get the current course
                Courses currentCourse = coursesList.get(position);

                // Set the details of a course according to the course code and based on initialised references
                holder.courseImage.setImageResource(currentCourse.getCourseImageResource());
                holder.courseTitle.setText(currentCourse.getCourseTitle());
                holder.courseDesc.setText(currentCourse.getCourseDesc());

                // Return the row view
                return row;
            }
        };
        // Set the adapter for the ListView
        listView.setAdapter(adapter);
    }
}
package com.example.eeecourselist;

public class Courses {
    // Course attributes
    private String courseCode = "";
    private String courseTitle = "";
    private String courseDesc = "";

    // Getter and setter methods for courseCode
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    // Getter and setter methods for courseTitle
    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }

    // Getter and setter methods for courseDesc
    public String getCourseDesc() { return courseDesc; }
    public void setCourseDesc(String courseDesc) { this.courseDesc = courseDesc; }

    // Method to get the resource ID of the course image based on the courseCode
    public int getCourseImageResource() {
        // Switch statement to determine the appropriate image resource based on courseCode
        switch (courseCode) {
            case "S90":
                // Return the resource ID for Aerospace Electronics image
                return R.drawable.aerospace_electronics;
            case "S53":
                // Return the resource ID for Computer Engineering image
                return R.drawable.computer_engineering;
            default:
                // Return a default image resource ID if courseCode does not match known values
                return R.drawable.ic_launcher_background;
        }
    }
}


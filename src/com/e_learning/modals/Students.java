package com.e_learning.modals;

public class Students {
    public String Student_Id = new String();
    public String fname = new String();
    public String lname = new String();
    public String grade_ = new String();
    public String gender_ = new String();
    public String Teacher = new String();



    public String getStudent_Id(){
        return this.Student_Id;
    }

    public void setStudent_Id(String student_no){
        this.Student_Id = student_no;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String f_name) {
        this.fname = f_name;
    }

    public String getLname() {
        return this.lname;
    }

    public void setLname(String l_name) {
        this.lname = l_name;
    }

    public String getGrade_() {
        return this.grade_;
    }

    public void setGrade_(String grade_) {
        this.grade_ = grade_;
    }

    public String getGender_() {
        return this.gender_;
    }

    public void setGender_(String gender_) {
        this.gender_ = gender_;
    }

    public String getTeacher() {
        return this.Teacher;
    }

    public void setTeacher(String teacher) {
        this.Teacher = teacher;
    }


    public Students(String Student_Id, String fname, String lname, String grade, String gender, String teacher) {
        this.Student_Id = Student_Id;
        this.fname = fname;
        this.lname = lname;
        this.grade_ = grade;
        this.gender_ = gender;
        this.Teacher = teacher; 
    }
    
}

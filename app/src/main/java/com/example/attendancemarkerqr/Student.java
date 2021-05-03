package com.example.attendancemarkerqr;

public class Student {

    public String username,pass,Fullname,dept ,Year,prn,mobile,email, clg;
    public Student()
    {

    }

    public Student(String Fullname, String email,String username, String pass,String dept , String Year, String prn, String mobile,String clg)
    {
        this.Fullname = Fullname;
        this.username = username;
        this.pass = pass;
        this.dept = dept;
        this.Year = Year;
        this.prn = prn;
        this.mobile = mobile;
        this.email = email;
        this.clg = clg;
    }

}

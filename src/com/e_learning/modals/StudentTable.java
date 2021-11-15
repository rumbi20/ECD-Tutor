package com.e_learning.modals;

public class StudentTable {

    public String Student_Id = new String();
    public String Student_name = new String();
    public String Math_entry = new String();
    public String Eng_entry = new String();
    public String Sci_entry = new String();
    public String Ict_entry = new String();
    public String Pe_entry = new String();


    public String getStudent_Id(){
        return this.Student_Id;
    }

    public void setStudent_Id(String student_no){
        this.Student_Id = student_no;
    }

    public String getStudent_name() {
        return this.Student_name;
    }

    public void setStudent_name(String Student_name) {
        this.Student_name = Student_name;
    }

    public String getMath_entry() {
        return this.Math_entry;
    }

    public void setMath_entry(String Math_entry) {
        this.Math_entry = Math_entry;
    }

    public String getEng_entry() {
        return this.Eng_entry;
    }

    public void setEng_entry(String Eng_entry) {
        this.Eng_entry = Eng_entry;
    }

    public String getSci_entry() {
        return this.Sci_entry;
    }

    public void setSci_entry(String Sci_entry) {
        this.Sci_entry = Sci_entry;
    }

    public String getPe_entry() {
        return this.Pe_entry;
    }

    public void setPe_entry(String Pe_entry) {
        this.Pe_entry = Pe_entry;
    }

    public String getIct_entry() {
        return this.Ict_entry;
    }

    public void setIct_entry(String Ict_entry) {
        this.Ict_entry = Ict_entry;
    }


    public StudentTable(String Student_Id, String Student_name, String Math_entry, String Eng_entry, String Sci_entry, String Pe_entry, String Ict_entry) {
        this.Student_Id = Student_Id;
        this.Student_name = Student_name;
        this.Math_entry = Math_entry;
        this.Eng_entry = Eng_entry;
        this.Sci_entry = Sci_entry;
        this.Ict_entry = Ict_entry;
        this.Pe_entry = Pe_entry;
    }
}

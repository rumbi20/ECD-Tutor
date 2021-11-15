package com.e_learning.modals;

public class SubjectTable {

    public String Maths = new String();
    public String English = new String();
    public String Science = new String();
    public String Ict = new String();
    public String Pe = new String();



    public String getMaths() {
        return this.Maths;
    }

    public void setMaths(String maths) {
        this.Maths = maths;
    }

    public String getEnglish() {
        return this.English;
    }

    public void setEnglish(String english) {
        this.English = english;
    }

    public String getScience() {
        return this.Science;
    }

    public void setScience(String Science) {
        this.Science = Science;
    }

    public String getIct() {
        return this.Ict;
    }

    public void setIct(String ict) {
        this.Ict = ict;
    }

    public String getPe() {
        return this.Pe;
    }

    public void setPe(String pe) {
        this.Pe = pe;
    }

    public SubjectTable(String maths, String english, String science, String ict, String pe) {
        this.Maths = maths;
        this.English = english;
        this.Science = science;
        this.Ict = ict;
        this.Pe = pe;
    }
}

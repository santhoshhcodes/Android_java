package com.example.myproject.model;

public class StMark {
    String Sno, stName, tamil, english, maths, science, social, total, deleteImg, editImg;

    public StMark(String Sno, String stName, String tamil, String english, String maths, String science, String social, String total, String editImg, String deleteImg

    ) {
        this.Sno = Sno;
        this.stName = stName;
        this.tamil = tamil;
        this.english = english;
        this.maths = maths;
        this.science = science;
        this.social = social;
        this.total = total;
        this.editImg = editImg;
        this.deleteImg = deleteImg;

    }

    public String getSno() {
        return Sno;
    }

    public String getDeleteImg() {
        return deleteImg;
    }

    public String getEditImg() {
        return editImg;
    }

    public String getEnglish() {
        return english;
    }

    public String getMaths() {
        return maths;
    }

    public String getScience() {
        return science;
    }

    public String getSocial() {
        return social;
    }

    public String getStName() {
        return stName;
    }

    public String getTamil() {
        return tamil;
    }

    public String getTotal() {
        return total;
    }

    public void setDeleteImg(String deleteImg) {
        this.deleteImg = deleteImg;
    }

    public void setEditImg(String editImg) {
        this.editImg = editImg;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public void setMaths(String maths) {
        this.maths = maths;
    }

    public void setScience(String science) {
        this.science = science;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public void setTamil(String tamil) {
        this.tamil = tamil;
    }

    public void setTotal(String total) {
        this.total = total;
    }


    public void setSno(String sno) {
        Sno = sno;
    }
}


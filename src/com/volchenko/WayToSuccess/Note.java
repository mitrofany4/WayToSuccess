package com.volchenko.WayToSuccess;

/**
 * Created by Максим on 25.07.13.
 */
public class Note {
    private String name;
    private String phone;
    private String date;
    private String expert;

    public Note(String expert) {
        this.expert = expert;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExpert() {
        return expert;
    }

    public void setExpert(String expert) {
        this.expert = expert;
    }

    @Override
    public String toString() {
        return "Уважаемый(ая)"+name+
        ", в ближайшее время мы свяжемся с Вами и подтвердим запись на прием к эксперту "+
        expert+" на "+date+".";
    }
}

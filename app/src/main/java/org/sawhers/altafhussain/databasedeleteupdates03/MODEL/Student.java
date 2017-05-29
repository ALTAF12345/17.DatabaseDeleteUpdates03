package org.sawhers.altafhussain.databasedeleteupdates03.MODEL;

import java.io.Serializable;

/**
 * Created by ALTAFHUSSAIN on 12/31/2016.
 */

public class Student  implements Serializable{
    int id;
    String name;
    String course;
    int totalFee;
    int feePaid;
    long contact;
    String address;

    public Student() {
    }

    public Student(String name, String course, int totalFee, int feePaid, long contact, String address) {
        this.name = name;
        this.course = course;
        this.totalFee = totalFee;
        this.feePaid = feePaid;
        this.contact = contact;
        this.address = address;
    }

    public Student(int id, String name, String course, int totalFee, int feePaid, long contact, String address) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.totalFee = totalFee;
        this.feePaid = feePaid;
        this.contact = contact;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public int getFeePaid() {
        return feePaid;
    }

    public void setFeePaid(int feePaid) {
        this.feePaid = feePaid;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return name;
    }
}

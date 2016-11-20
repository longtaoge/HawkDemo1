package model;

import java.io.Serializable;

/**
 * Created by longtaoge on 16/11/20.
 */

public class User implements Serializable {

    private  String name;
    private  int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

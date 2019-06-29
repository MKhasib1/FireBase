package com.example.login;

import java.util.ArrayList;

public class ToDo {
    private String mToDo;
    private boolean mIsChecked;

    public ToDo(String mToDo, boolean mIsChecked) {
        mToDo = mToDo;
        mIsChecked = mIsChecked;
    }

    public String getmToDo() {
        return mToDo;
    }

    public boolean ismIsChecked() {
        return mIsChecked;
    }

    private static int lastContactId = 0;

    public static ArrayList<ToDo> createContactsList(int numContacts) {
        ArrayList<ToDo> contacts = new ArrayList<ToDo>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new ToDo("Person " + ++lastContactId, i <= numContacts / 2));
        }

        return contacts;
    }
}

package com.example.todoapp;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class fileHelper {

    public static final String FILENAME = "listinfo.dat";

    public static void writeData(ArrayList<String> todo, Context context)
    {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,context.MODE_PRIVATE);
            ObjectOutputStream oas = new ObjectOutputStream(fos);
            oas.writeObject(todo);
            oas.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<String> readData(Context context)
    {
        ArrayList<String> todo = null;

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            todo = (ArrayList<String>) ois.readObject();

        } catch (FileNotFoundException e) {
            todo = new ArrayList<>();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  todo;
    }
}

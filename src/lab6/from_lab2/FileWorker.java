package lab6.from_lab2;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class FileWorker
{
    public void writeList(ArrayList<Shape> shapes, String filename) throws IOException
    {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            objectOutputStream.writeObject(shapes);
        }
    }

    public ArrayList<Shape> readList(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename))){
            ArrayList shapes = (ArrayList<Shape>) objectInputStream.readObject();
            return shapes;
        }
    }
}
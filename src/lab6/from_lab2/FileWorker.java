package lab6.from_lab2;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class FileWorker
{
  public  void writeList(ArrayList<Shape> shapes, String filename) throws IOException
    {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
        objectOutputStream.writeObject(shapes);
        objectOutputStream.close();
    }

 public   ArrayList<Shape> readList(String filename) throws IOException, ClassNotFoundException {
        ArrayList shapes = new ArrayList();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename));
        shapes = (ArrayList<Shape>)objectInputStream.readObject();
        return shapes;
    }
}
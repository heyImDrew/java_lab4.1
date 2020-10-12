package Classes;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

import Classes.Shedule.*;

public class Client {
    public static void main(String[] arg) {
        try {
            Socket clientSocket = new Socket("127.0.0.1",2525);
            System.out.println("** Connection with server established....");
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            ObjectOutputStream coos = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream cois = new ObjectInputStream(clientSocket.getInputStream());
            String clientMessage = new String("");
            ArrayList<Shedule> shedules = new ArrayList<Shedule>();
            while(!clientMessage.equals("0")) {
                System.out.print("\tMenu:\n1. Get shedule\n2. Add lesson\n3. Delete lesson\n0. Exit\n\t\tChoice: ");
                clientMessage = stdin.readLine();
                switch (Integer.valueOf(clientMessage)) {
                    case 1:
                        coos.writeObject(clientMessage);
                        int size = (Integer)cois.readObject();
                        for (int i = 0; i < size; i++) {
                            System.out.print("Lesson #" + (i+1) + ": ");
                            ((Shedule)cois.readObject()).printInfo();
                        }
                        break;
                    case 2:
                        coos.writeObject(clientMessage);
                        System.out.print("Input group number: ");
                        Scanner scanner = new Scanner(System.in);
                        int i1 = scanner.nextInt();
                        coos.writeObject(i1);
                        System.out.print("Input lesson: ");
                        String i2 = stdin.readLine();
                        coos.writeObject(i2);
                        System.out.print("Input date: ");
                        String i3 = stdin.readLine();
                        coos.writeObject(i3);
                        System.out.print("Input time from: ");
                        String i4 = stdin.readLine();
                        coos.writeObject(i4);
                        System.out.print("Input time to: ");
                        String i5 = stdin.readLine();
                        coos.writeObject(i5);
                        break;
                    case 3:
                        coos.writeObject(clientMessage);
                        System.out.print("Lesson number on delete: ");
                        Scanner scanner1 = new Scanner(System.in);
                        int ch = scanner1.nextInt();
                        coos.writeObject(ch - 1);
                        break;
                    case 0:
                        coos.writeObject(clientMessage);
                        coos.close();
                        cois.close();
                        clientSocket.close();
                        break;
                    default:
                        break;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
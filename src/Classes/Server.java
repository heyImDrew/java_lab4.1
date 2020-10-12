package Classes;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import Classes.Shedule.*;

public class Server {
    public static void main(String[] arg) {
        Shedule s1 = new Shedule(1, "oop", "20.20.20", "14", "17");
        Shedule s2 = new Shedule(2, "oop", "21.20.20", "15", "18");
        Shedule s3 = new Shedule(3, "oop", "22.20.20", "16", "19");
        ArrayList<Shedule> shedules = new ArrayList<Shedule>();
        shedules.add(s1);
        shedules.add(s2);
        shedules.add(s3);

        ServerSocket serverSocket = null;
        Socket clientAccepted = null;
        ObjectInputStream sois = null;
        ObjectOutputStream soos = null;
        try {
            System.out.println("** Server starting..");
            serverSocket = new ServerSocket(2525);
            clientAccepted = serverSocket.accept();
            System.out.println("** Connection established..");
            soos = new ObjectOutputStream(clientAccepted.getOutputStream());
            sois = new ObjectInputStream(clientAccepted.getInputStream());
            String clientMessageRecieved = new String("");
            boolean cycle = true;
            while(cycle)
            {
                clientMessageRecieved = (String)sois.readObject();
                System.out.println("** Message recieved: '" + clientMessageRecieved + "'");
                switch (clientMessageRecieved) {
                    case "1":
                        soos.writeObject(shedules.size());
                        for (int i=0;i<shedules.size();i++) {
                            soos.writeObject(shedules.get(i));
                        }
                        break;
                    case "2":
                        int gr_num = (Integer)sois.readObject();
                        String les = (String)sois.readObject();
                        String dt = (String)sois.readObject();
                        String tf = (String)sois.readObject();
                        String tt = (String)sois.readObject();
                        Shedule temp = new Shedule(gr_num, les, dt, tf, tt);
                        shedules.add(temp);
                        break;
                    case "3":
                        int ch = (Integer)sois.readObject();
                        shedules.remove(shedules.get(ch));
                        break;
                    case "0":
                        cycle = false;
                        break;
                    default:
                        break;
                }
            }
        }
        catch(Exception e) {}
        finally {
            try {
                sois.close();
                soos.close();
                clientAccepted.close();
                serverSocket.close();
            }
            catch(Exception e) {
                    e.printStackTrace();
            }
        }
    }
}
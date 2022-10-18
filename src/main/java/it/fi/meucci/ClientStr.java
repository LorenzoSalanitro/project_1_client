package it.fi.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientStr {
    String name_server = "localhost";
    int serverport = 7777;
    Socket mysocket;
    BufferedReader keyboard;
    String user;
    String received;
    DataOutputStream output;
    BufferedReader input;

    public Socket connect()
    {
        try {
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            mysocket = new Socket(name_server, serverport);
            output = new DataOutputStream(mysocket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));
        } catch (UnknownHostException e) {
            
            System.err.println("Unknowed host");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Error during connection");
            System.exit(1);
        }
        return mysocket;
        
    }

    public void comunicate()
    {   
        for(;;)
        try {
            System.out.println("Insert string "+ '\n');
            user = keyboard.readLine();
            System.out.println("Sending string to server");
            output.writeBytes(user + '\n');
            received = input.readLine();
            System.out.println("server feedback" + '\n'+ received);
            if(user.equals("END"))
            {
                System.out.println("CLIENT : execution ended");
                mysocket.close();
                break;
            }
            else if(user.equals("CLOSE"))
            {
                System.out.println("server chiuso");
                mysocket.close();
                break;
            }
           

        } catch (Exception e) {
            
            System.out.println(e.getMessage());
            System.out.println("Error during connection");
            System.exit(1);
        }
    

    } 
}

import java.io.*;
import java.net.*;

public class server{
    public static void main(String argv[]) throws Exception {
        try {

            //String clientMessage="";
            String serverMessage; //thing server sends to client when connected


            ServerSocket welcomeSocket = new ServerSocket(Integer.parseInt(argv[0])); //open a port to wait for a connection
            int maxClients = Integer.parseInt(argv[1]); //get the number of max clients from command line

            serverMessage = argv[2] + "\n"; //parse the server message


            for(int i=0;i<maxClients;i++) {

                Socket connectionSocket = welcomeSocket.accept(); //start a new port for each client that want to connect
                ClientThread t = new ClientThread(connectionSocket, serverMessage); //and start a new thread for them
                t.start();


            }


        } catch (NumberFormatException e) {
            System.out.println("One of the numbers you entered was not an int. Enter an int and try again.");
        }
    }

}

class ClientThread extends Thread{
    Socket clientSocket;
    String serverMessage;
    String clientMessage;

    ClientThread(Socket inSocket, String servermessage){
        clientSocket=inSocket;
        serverMessage=servermessage;
    }
    public void run(){
        try{
            BufferedReader inFromClient=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream outToClient=new DataOutputStream(clientSocket.getOutputStream());
            outToClient.writeBytes(serverMessage); //send server message
            while(true) {
                clientMessage = inFromClient.readLine(); //get and display client messages
                if (clientMessage.equals("\\disconnect"))
                    break;
                System.out.println(clientSocket.getInetAddress() + ":" + clientSocket.getPort() + ":" + clientMessage);
            }
        }
        catch(Exception ex){
                System.out.println(ex);
        }
        finally{
            try{clientSocket.close();}
            catch (Exception e)
                {System.out.println(e);}
        }

    }
}

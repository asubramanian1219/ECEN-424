import java.io.*;
import java.net.*;

public class client {
    public static void main(String argv[]) throws Exception {
        try {

            String hostname = argv[0];

            int port = Integer.parseInt(argv[1]);

            String userMessage;

            //String serverMessage;

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

            Socket clientSocket = new Socket(hostname, port);

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println(inFromServer.readLine());
            while (true) {

                userMessage = inFromUser.readLine();

                if (userMessage.equals("\\disconnect"))
                    break;

                outToServer.writeBytes(userMessage + '\n');

                outToServer.flush();

                //serverMessage = inFromServer.readLine();

                //System.out.println(serverMessage);

            }
            outToServer.close();
            inFromServer.close();
            outToServer.close();
            clientSocket.close();


        }
        catch(NumberFormatException e){
            System.out.println("The port number you entered was not an int. Please enter an int.");
        }
        catch(UnknownHostException e){
            System.out.println("You did not enter a valid IP address. Please enter a valid IP address.");
        }
    }

}


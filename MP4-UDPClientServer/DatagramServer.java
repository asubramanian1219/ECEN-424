import java.net.* ;

/**
 *  A simple datagram server
 *  Shows how to send and receive UDP packets in Java
 *
 */
import java.util.Random;

public class DatagramServer
{
   private final static int PACKETSIZE = 100 ;

   public static void main( String args[] )
   {
       Random rand=new Random();
      // Check the arguments
      if( args.length != 1 )
      {
         System.out.println( "usage: DatagramServer port" ) ;
         return ;
      }

      try
      {
         // Convert the argument to ensure that is it valid
         int port = Integer.parseInt( args[0] ) ;

         // Construct the socket
         DatagramSocket socket = new DatagramSocket( port ) ;

         System.out.println( "The server is ready..." ) ;
         String [] messages;

         for(;;){
             DatagramPacket firstPacket=new DatagramPacket(new byte[PACKETSIZE],PACKETSIZE);
             socket.receive(firstPacket);
             int arraySize=Integer.parseInt(new String(firstPacket.getData()).trim());

             messages=new String[arraySize];

             if(rand.nextBoolean()){
                 byte[] ack1data="1".getBytes();
                 DatagramPacket ack1=new DatagramPacket(ack1data, ack1data.length, firstPacket.getAddress(), firstPacket.getPort());
                 socket.send(ack1);
                 break;
             }
         }



         for( ;; )
         {
            // Create a packet
            DatagramPacket receivedPacket = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE ) ;

            // Receive a packet (blocking)
            socket.receive( receivedPacket ) ;
            int ack=Integer.parseInt(new String(receivedPacket.getData()).substring(0,1));
            String receivedMsg=new String(receivedPacket.getData()).substring(1);
            messages[ack]=receivedMsg;
            // Print the packet
            System.out.println( receivedPacket.getAddress() + " " + receivedPacket.getPort() + ": " + new String(receivedPacket.getData()) ) ;
            if(rand.nextBoolean()) {
                // Return the packet to the sender
                byte [] ackData=Integer.toString(ack+1).getBytes();
                DatagramPacket ackPacket=new DatagramPacket(ackData,ackData.length, receivedPacket.getAddress(), receivedPacket.getPort());
                socket.send(ackPacket);
                if((ack+1)>= messages.length){
                    break;
                }
            }
        }
         String megastring="";
         for (int i=0;i< messages.length;i++){
             megastring=megastring+messages[i];
         }
         System.out.println(megastring);

     }
     catch( Exception e )
     {
        System.out.println( e ) ;
     }
  }
}

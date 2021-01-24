import java.net.* ;

/**
 *  A simple datagram client
 *  Shows how to send and receive UDP packets in Java
 */
public class DatagramClient
{
   private final static int PACKETSIZE = 100 ;

   public static void main( String args[] )
   {
      // Check the arguments
      if( args.length != 3 )
      {
         System.out.println( "usage: java DatagramClient host port message" ) ;
         return ;
      }

      DatagramSocket socket = null ;

      try
      {
         // Convert the arguments first, to ensure that they are valid
         InetAddress host = InetAddress.getByName( args[0] ) ;
         int port         = Integer.parseInt( args[1] ) ;
         String bigMessage   = args[2];

         if(bigMessage.length()>50){
            throw new Exception("Your message is too long.");
         }
         String[] messages= bigMessage.split("(?<=\\G..........)");


         // Construct the socket
         socket = new DatagramSocket() ;

         // Construct the datagram packet
         byte [] data = "Hello Server".getBytes() ;
         DatagramPacket packet;



         // Send it
         //socket.send( packet ) ;

         // Set a receive timeout, 2000 milliseconds
         socket.setSoTimeout( 2000 ) ;

         // Prepare the packet for receive
         //packet.setData( new byte[PACKETSIZE] ) ;
         data=Integer.toString(messages.length).getBytes();


         while(true) {
            packet=new DatagramPacket( data, data.length, host, port ) ;
            socket.send( packet ) ;
            packet.setData( new byte[PACKETSIZE] ) ;

            // Wait for a response from the server
            try {
               socket.receive(packet);
               break;
            }
            catch(SocketTimeoutException e){

            }

         }

         for(int i=0;i<messages.length;i++) {
            data=(i+messages[i]).getBytes();
            while (true) {
               packet = new DatagramPacket(data, data.length, host, port);
               socket.send(packet);
               packet.setData(new byte[PACKETSIZE]);

               // Wait for a response from the server
               try {
                  socket.receive(packet);
                  System.out.println( "ACK " + (new String(packet.getData()))+ " received." ) ;
                  break;
               } catch (SocketTimeoutException e) {

               }

            }
         }

         // Print the response
         //System.out.println( new String(packet.getData()) ) ;
         
      }
      catch( Exception e )
      {
         System.out.println( e ) ;
      }
      finally
      {
         if( socket != null )
            socket.close() ;
      }
   }
}


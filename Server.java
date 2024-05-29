import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  
    public static int index = 0;
public static void main(String args[]){
    try{
    System.out.println("Server has begun running. Waiting for Connection...");

    ServerSocket serverSocket = new ServerSocket(4444);
   
  
    while(true){
        Socket clientSocket = serverSocket.accept();
    
    if(clientSocket.isConnected()){
    new Thread(()->{
        index++;
       
        try{
        BufferedReader in = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream());

        while(true){
            String message = in.readLine();
            System.out.println(message);
        out.println(message);
        }
        }
        catch(Exception e){
            index--;
            System.out.println("Client Disconnected");
        }

    }).start();
    }
    }
}
    catch(Exception e){
        System.out.println("The client has disconnected. The server will now shut down");
    }


}

}

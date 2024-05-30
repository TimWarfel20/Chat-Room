import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    static ArrayList<Socket> activeSockets = new ArrayList<Socket>();
public static void main(String args[]){
    try{
    System.out.println("Server has begun running. Waiting for Connection...");
    ServerSocket serverSocket = new ServerSocket(4444);
    while(true){
        Socket clientSocket = serverSocket.accept();
    
    if(clientSocket.isConnected()){
        System.out.println("Client Connected");
        activeSockets.add(clientSocket);
        for(int i = 0; i < activeSockets.size(); i++){
            PrintWriter out = new PrintWriter(activeSockets.get(i).getOutputStream(), true);
        out.println("Client Connected");
        }

    new Thread(()->{
        try{
        BufferedReader in = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));

        while(true){
            String message = in.readLine();
            System.out.println(message);

            for(int i = 0; i < activeSockets.size(); i++){
                PrintWriter out = new PrintWriter(activeSockets.get(i).getOutputStream(), true);
            out.println(message);
            }
        }
        }
        catch(Exception e){
            System.out.println("Client Disconnected");
            for(int i = 0; i < activeSockets.size(); i++){
                PrintWriter out;
                try {
                    out = new PrintWriter(activeSockets.get(i).getOutputStream(), true);
                    out.println("Client Disconnected");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            
            }
        }

    }).start();
    }
    }
}
    catch(Exception e){}


}

}

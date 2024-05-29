import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.net.*;
import java.io.*;



public class Client{

public static final int port = 4444;
public static final String ip = "127.0.0.1";
public static int numRows = 0;
public static JFrame frame = new JFrame("Chat Application");
public static JTextField textField = new JTextField();
public static JButton button = new JButton("Input Message Here");

public static PrintWriter out;
public static BufferedReader in;

    public static void main(String args[]){
        
    connectToServer();
    displayWindow();
    }

    public static void displayWindow(){

        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);

        frame.setSize(500,500);
    
        frame.add(textField);
        textField.setBounds(100,325,300,50);
        
        frame.add(button);
        button.setBounds(100, 400, 300, 30);

        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                numRows++;
                if(numRows >=10){
                    textArea.setText("");
                    numRows = 0;
                }
                String message = textField.getText();
                sendMessage(message);
                try{
                textArea.setText(textArea.getText() + "\n" + message);
                new Thread(()->{
                    try {
                        while(true){
                        System.out.println(in.readLine());
                        }
                    } catch (IOException AA) {
                        // TODO Auto-generated catch block
                        AA.printStackTrace();
                    }
                 
                    }).start();
               
                }catch(Exception a){
                    System.out.println(a);
                }
            }
        });
        
frame.add(textArea);
textArea.setBounds(100, 100, 300, 200);
        

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void connectToServer(){
try{




     Socket clientSocket = new Socket(ip, port);
      out = new PrintWriter(clientSocket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      sendMessage("Connected!");

      
      
}
catch(Exception e){
    System.out.println("Error: Failed to Connect");
}
      
   
        
    

    }

    public static void sendMessage(String msg){

    out.println(msg);

    }

}


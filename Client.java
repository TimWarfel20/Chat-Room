import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.net.*;
import java.io.*;



public class Client{

public static String username = "";
public static final int port = 4444;
public static final String ip = "127.0.0.1";
public static int numRows = 0;
public static JFrame frame = new JFrame("Chat Application");
public static JTextField textField = new JTextField();
public static JButton button = new JButton("Input Message Here");
public static JTextArea textArea = new JTextArea(10, 30);

public static PrintWriter out;
public static BufferedReader in;

    public static void main(String args[]) throws IOException{
        
    connectToServer();
    nameWindow();

    while(true){
        numRows++;
        if(numRows >=10){
            textArea.setText("");
            numRows = 0;
        }
        String buffer = in.readLine(); 
        textArea.setText(textArea.getText() + "\n" + buffer);
    }
    }

    public static void nameWindow(){
        JFrame nameFrame = new JFrame("Chat Application");
        nameFrame.setSize(500, 500);

        JButton nameButton = new JButton("Please Enter your Name");
        nameButton.setBounds(100, 224, 300, 30);
        nameFrame.add(nameButton);

        JTextField nameField = new JTextField();
        nameField.setBounds(100, 175, 300, 50);
        nameFrame.add(nameField);

        nameFrame.setLayout(null);
        nameFrame.setVisible(true);
        nameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        nameButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                username = nameField.getText();
                displayWindow();
                nameFrame.dispose();
            }
        });
    }

    public static void displayWindow(){

       
        textArea.setEditable(false);

        frame.setSize(500,500);
    
        frame.add(textField);
        textField.setBounds(100,325,300,50);
        
        frame.add(button);
        button.setBounds(100, 400, 300, 30);

        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
              
                String message = textField.getText();
                message = username + ": " + message;
                sendMessage(message);
            }
        });
        
frame.add(textArea);
textArea.setBounds(100, 100, 300, 200);
        

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void connectToServer(){
try{
     Socket clientSocket = new Socket(ip, port);
      out = new PrintWriter(clientSocket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));   
}
catch(Exception e){
    System.out.println("Error: Failed to Connect");
}
    }

    public static void sendMessage(String msg){

    out.println(msg);

    }

}


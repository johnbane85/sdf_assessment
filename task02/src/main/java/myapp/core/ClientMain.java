package myapp.core;

import java.io.IOException;
import java.net.Socket;

public class ClientMain {

    public static void main(String[] args) throws IOException {

        String host = "68.183.239.26";
        int port = 80;
        // if (args.length >= 2) {
        //     host = args[0];
        //     port = Integer.parseInt(args[1]);
        // } else if (args.length == 1) 
        //     port = Integer.parseInt(args[0]);

        //Connect to the server
        System.out.printf("Connect to server %s on port %d\n", host, port);
        Socket sock = new Socket(host, port);
        System.out.println("Connected...");

        NetworkIO netIO = new NetworkIO(sock);
        // String req = "";
        String resp = "";


        boolean disconet = false;

        while (!disconet) {
        
            resp = netIO.read();
            System.out.printf(">> %s\n", resp);

            String[] temp = resp.split("[, ]");
            String requestId = temp[0];
    
            
            int num =0;
            int sum = 0;
    
            for(int i =1; i <temp.length; i++){
            num = Integer.parseInt(temp[i]);
            sum += num;
            }
    
            float tempSum = sum;
            float average = tempSum / (temp.length -1);
    
            System.out.println("requestid:" + requestId);
            System.out.println("average:" + average);
    
            netIO.write(requestId);
            netIO.write("SIEW JUN YU");
            netIO.write("junyu.siew@gmail.com");
            netIO.writeAverage(average);
    
            boolean booleanresp;  
            booleanresp =netIO.booleanRead();
            
            if(booleanresp) {
              System.out.println("SUCCESS");
              disconet = true;
            } else {
              resp = netIO.read();
              System.out.println(resp);
              System.out.println("FAILED");
              disconet = true;
            }

        }

        netIO.close();
        sock.close();

        System.out.println("Terminating client...");
    }
}

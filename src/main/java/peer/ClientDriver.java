package main.java.peer;

import java.util.List;
import java.util.Scanner;

public class ClientDriver {

    public static void main(String[] args) {

        /*  create new client object
         *  args[0] is the unique ID of the client (i.e. the IP address)
         *  args[1] is the directory of the files to download to
         */
        String folder = args[1];

        System.out.println("INFO: Initializing Peer...");
        Client peerClient = new Client(args[0]);
        System.out.println("INFO: Client Process initialized...");

        System.out.println("INFO: Registering Files in: " + folder);
        peerClient.getFilesToRegister(folder);
        peerClient.registerAll();
        System.out.println("INFO: Files Sucessfully Registered... ");


        Scanner input = new Scanner(System.in);
        System.out.println("Input 'exit' to close the application at anytime");
        String query;
        while (true) {
            System.out.println("Input name of file you want to retrieve:");
            query = input.nextLine();
            if (query.equals("exit")){
                System.out.println("\nALERT: Process exiting... \n Goodbye.");
                System.exit(0);
            }
            List<String> peers = peerClient.lookup(query);
            if (peers == null){
                System.out.println("WARNING: No peers for that file\n\ttry a new filename");
                break;
            }
            peerClient.retrieve(query, peers.get(0));
        }
    }
}

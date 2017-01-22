package main.java.peer;

import java.util.List;
import java.util.Scanner;

public class ClientDriver {

    public static void main(String[] args) {
        Client peerClient = new Client(args[0]);

        Scanner input = new Scanner(System.in);
        System.out.println("Input exit to close the application at anytime");
        String query;
        while (true) {
            System.out.println("Input name of file you want to retrieve:");
            query = input.nextLine();
            if (query.equals("exit")){
                break;
            }
            List<String> peers = peerClient.lookup(query);
            if (peers == null){
                System.out.println("No peers for that file");
                break;
            }
            peerClient.retrieve(query, peers.get(0));
        }
    }
}

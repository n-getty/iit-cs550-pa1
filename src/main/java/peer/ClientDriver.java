package main.java.peer;

import java.io.*;
import java.util.*;//List;
//#import java.util.Scanner;
//#import main.java.peer.WatchDir;


public class ClientDriver{// implements PeerInt {

    public static void main(String[] args)
	throws IOException {
	
	System.setProperty( "java.rmi.server.hostname", args[0] ) ;
        /*  create new client object
         *  args[0] is the unique ID of the client (i.e. the IP address)
         *  args[1] is the directory of the files to download to
         */
        String folder = args[1];

        System.out.println("INFO: Initializing Peer...");
        Client peerClient = new Client(args[0]);
	PeerImpl peerServ = new PeerImpl(folder);
	
        System.out.println("INFO: Client Process initialized...");
	
        System.out.println("INFO: Registering Files in: ./" + folder + "/");
        peerClient.getFilesToRegister(folder);
        peerClient.registerAll();
        System.out.println("INFO: Files Sucessfully Registered... ");

	/* the watch file is blocking
	 *    TODO create thread to monitor directory
	 *    TODO modify WatchDir to call register / unregister 
	 *                 when (event kind) = ENTRY_CREATE | ENTRY_DELETE
	 */
	
	/*
	System.out.println("INFO: Setting up directory watcher ...");
	Path dir = Paths.get(folder);
	new WatchDir(dir, false).processEvents();
	System.out.println("INFO: Directory watcher Successfully set up.");
	*/
	
        Scanner input = new Scanner(System.in);
        System.out.println("\nInput 'exit' to close the application at anytime");
        String query;

	while (true) {
            System.out.println("\nInput name of file you want to retrieve:\n");
            query = input.nextLine();
            if (query.equals("exit")){
                System.out.println("\nALERT: Process exiting... \n Goodbye.");
                System.exit(0);
            }
            List<String> peers = peerClient.lookup(query);
            if (peers == null){
                System.out.println("WARNING: No peers for that file\n\ttry a new filename");
            }
	    else {
		String x = peerClient.retrieve(query, peers.get(0));
		try {
		    BufferedWriter out = new BufferedWriter(new FileWriter(folder + "/" + query));
		    out.write(x);
		    out.close();
		
		}
		catch (IOException e)
		    {
			System.out.println("Exception" + e);
		    }
		// save file in directory
		
	    }
	}
    }
}

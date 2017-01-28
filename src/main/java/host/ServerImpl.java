package main.java.host;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.Arrays.asList;
import java.util.Scanner;


public class ServerImpl implements IndexInt {

    // Maps file names to a list of peers that have that file
    Map<String, List<String>> peerMap;

    /**
     * Constructor for exporting remote object to rmi registry
     */
    public ServerImpl() {
	peerMap = new HashMap<String, List<String>>();

	try {
	        // Export this object
            IndexInt stub = (IndexInt) UnicastRemoteObject.exportObject(this, 0);
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("IndexInt", stub);

            System.err.println("INFO: hostServerImpl ready");
        } catch (Exception e) {
            System.err.println("ALERT: hostServerImpl exception: " + e.toString());
            e.printStackTrace();
        }

    }

    /**
     * Update the index map with the given peer id and the file they have
     */
    public void register(String peerId, String fileName){
	System.out.println("INFO: registering file: " + fileName + " to peer " + peerId);
	if(peerMap.containsValue(fileName)){
            peerMap.get(fileName).add(peerId);
        }
        else{
            peerMap.put(fileName, asList(peerId));
        }
    }

    /**
     * Update for when a peer removes a file from their directory
     */
    public void deregister(String peerId, String fileName){
        System.out.println("INFO: deregistering file: " + fileName + " for peer " + peerId);
        if(peerMap.containsValue(fileName)){
            peerMap.get(fileName).remove(peerId);
        }
    }

    /**
     * Return the list of peers who have a particular file
     */
    public List<String> lookup(String fileName) {
        return peerMap.get(fileName);
    }

    /**
     * Main method for starting the server
     */
    public static void main (String[] args) {
	
	System.setProperty( "java.rmi.server.hostname", "10.0.0.1" ) ;
        System.out.println("INFO: initiating server deamon");
        ServerImpl x = new ServerImpl();

	System.out.println("INFO: server initiated.");

        System.out.println("\nINFO: Press return Key to Exit.\n\n");
        Scanner scanner = new Scanner(System.in);
        String readString = scanner.nextLine();

	/*
        System.out.println("INFO: cleaning up...");
        // TODO exit and persistence ???
        System.out.println("INFO: Finished cleaning up\n");
        */

        System.out.println("\nALERT: Process exiting...\n Goodbye.");
        System.exit(0);
    }
}

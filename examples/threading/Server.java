package main.java.peer;
import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server implements PeerInt {
	ExecutorService fixedPool;

    public Server() {
		try {
			PeerInt stub = (PeerInt) UnicastRemoteObject.exportObject(this, 0);

			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("PeerInt", stub);
			int numThreads = 5;
			fixedPool = Executors.newFixedThreadPool(numThreads);

			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

    public File retrieve(String fileName) {
		File returnedFile = null;
		Future<File> callFuture = fixedPool.submit(new RetrieveThread(fileName));
		try {
			returnedFile = callFuture.get();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return returnedFile;
	}

}

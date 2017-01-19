package main.java.peer;

import main.java.host.IndexInt;

import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class Client {
	IndexInt indexStub;
	String id;
	List<String> fileList = new ArrayList<String>();

    private Client(String host) {
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			indexStub = (IndexInt) registry.lookup("IndexInt");
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

    public void register(String fileName){
		fileList.add(fileName);
		try {
			indexStub.register(id, fileName);
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

	public void registerAll(){
		try {
			for(String fileName : fileList)
				indexStub.register(id, fileName);
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

	public List<Integer> lookup(String fileName){
		List targetIds = null;
		try {
			targetIds = indexStub.lookup(fileName);
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		return targetIds;
	}

	public File retrieve(String fileName, String peerId){
		File returnedFile = null;
		try {
			Registry registry = LocateRegistry.getRegistry(peerId);
			PeerInt peerStub = (PeerInt) registry.lookup("PeerInt");
			returnedFile = peerStub.retrieve(fileName);
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		return returnedFile;
	}
}

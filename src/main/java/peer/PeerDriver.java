package main.java.peer;

public class PeerDriver {
    public static void main(String[] args) {
        ClientImpl peerClient = new ClientImpl(args[0]);
        ServerImpl peerServer = new ServerImpl();

    }
}

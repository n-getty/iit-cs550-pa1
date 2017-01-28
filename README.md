# iit-cs550-pa1
Advanced Operating Systems programming assignment 1


To get environment set up:

virtualbox with ubuntu 16.04

run:

sudo apt-get update -y && sudo apt-get upgrade -y  
sudo apt-get install git emacs default-jdk default-jre -y  
git clone https://github.com/mininet/mininet.git  
cd mininet/utils  
sudo ./install.sh -a  
cd  
git clone https://github.com/n-getty/iit-cs550-pa1.git  
cd iit-cs550-pa1/src  
make  


# Running  
sudo mn --topo=single,4  
mininet$ xterm h1 h2 h3 h4  

h1$ rmiregistry &  
h1$ java main.java.host.ServerImpl   

h2$ rmiregistry &   
h2$ java main.java.peer.WatchDir test1 10.0.0.2 &
h2$ java main.java.peer.ClientDriver 10.0.0.2 test1  

h3$ rmiregistry &  
h3$ java main.java.peer.WatchDir test2 10.0.0.3 &
h3$ java main.java.peer.ClientDriver 10.0.0.3 test2   

h4$ rmiregistry &   
h4$ java main.java.peer.WatchDir test3 10.0.0.4 &
h4$ java main.java.peer.ClientDriver 10.0.0.4 test3   


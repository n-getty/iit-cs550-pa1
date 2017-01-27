# iit-cs550-pa1
Advanced Operating Systems programming assignment 1


To get environment set up:

virtualbox with ubuntu 16.04

run:

sudo apt-get update -y && sudo apt-get upgrade -y\s\s
sudo apt-get install git emacs default-jdk default-jre -y\s\s
git clone https://github.com/mininet/mininet.git\s\s
cd mininet/utils\s\s
sudo ./install.sh -a\s\s
cd\s\s
git clone https://github.com/n-getty/iit-cs550-pa1.git\s\s
cd iit-cs550-pa1/src\s\s
make\s\s


# Running  
sudo mn --topo=single,4\s\s
mininet$ xterm h1 h2 h3 h4\s\s

h1$ rmiregistry &\s\s
h1$ java main.java.host.ServerImpl\s\s

h2$ rmiregistry &\s\s
h2$ java main.java.peer.ClientDriver 10.0.0.2 test1\s\s

h3$ rmiregistry &\s\s
h3$ java main.java.peer.ClientDriver 10.0.0.3 test2\s\s

h4$ rmiregistry &\s\s
h4$ java main.java.peer.ClientDriver 10.0.0.4 test3\s\s


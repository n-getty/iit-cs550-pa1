# iit-cs550-pa1
Advanced Operating Systems programming assignment 1
Neil Getty & Christopher Hannon

# Requried turn in materials:

1: Output file  -- ./docs/testcase/howto.pdf  
2: Design Doc -- ./docs/design_doc/design.pdf  
3: Source Code -- ./src/main/*  
4: Manual -- ./docs/manual/index.html  
5: Verification -- ./docs/testcase/howto.pdf  

# Setup
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


# Contents:

Documents are in the doc directory:  
Source Code is in the src directory:  
Utilities are in the util directory:  

## Manual

open ./docs/index.html in a browser to view the manual generated by javaDocs

## Design Document

open ./docs/design.pdf

## test case document

open ./docs/testcase/howto.pdf











# Running  
sudo mn --topo=single,5  
mininet$ xterm h1 h2 h3 h4 h5 

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

h5$ rmiregistry &   
h5$ java main.java.peer.WatchDir test3 10.0.0.5 &
h5$ java main.java.peer.ClientDriver 10.0.0.5 test4   


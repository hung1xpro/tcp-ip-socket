# Import the necessary libraries
import socket
import sys
import select

# Cloud da besttttt

# To store list of sockets of clients as well as server itself.
LIST = []        
# Common buffer for all purposes
buff = 1024

# Declaration of Server socket.
serv = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serv.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
serv.bind(("192.168.137.29", 8888))

# Listen for  6 clients.
serv.listen(20)

# Add server socket to the LIST
LIST.append(serv)
while 1:
    # Moniter clients all simultaneously
    reads, writes, err = select.select(LIST, [], [])
    
    for sock in reads:
        
        #  new client connected
        if sock == serv:
            sockfd, addr = serv.accept()
            LIST.append(sockfd)
            print("Got connect")
            a =" Hello Client"
            
            
        # just a new message!
        else:
             try:
            
                 # Get and check messsage ! 
                 data = (sock.recv(1024))
                 if data:
                      print(data)
                      sock.sendall(data)
                     # Send to all sock still connect to server .
                      for socket in LIST:
                         try:
                             if socket != serv and socket != sock:
                                 
                                 socket.sendall(data)                          
                         except:
            # Assume client has got disconnected and remove it.
                          socket.close()
                          LIST.remove(socket)
   
             except:
            
                 # Client kicked by server :3
                 sock.close()
                 LIST.remove(sock)
                 # Do this till the end of time.
                 continue
serv.close()

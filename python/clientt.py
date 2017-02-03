import socket

name=socket.gethostname()
try:
    
    while True:
      client=socket.socket()
      client.connect((socket.gethostbyname(name),11115))
      message=input('Please enter a message:')
      client.sendall(message.encode())
      rep=client.recv(1024).decode()
      client.close()
      print('server rep:',rep)
      if rep=="SHUTDOWN" : break
finally:  
    print ('closing socket')
   

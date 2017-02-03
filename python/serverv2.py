import socket
s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
ip=socket.gethostbyname(socket.gethostname())
s.bind((ip,11115))
s.listen(5)
while True:
        connect, address = s.accept()
        # Receive up to 1024 bytes
        resp = (connect.recv(1024).decode())
        if resp == "SHUTDOWN": break
        print('client said :',resp)
        connect.sendall(resp.encode())
connect.sendall('SHUTDOWN'.encode())
connect.close()
print ('\ndone ',address)

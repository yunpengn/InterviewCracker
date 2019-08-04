# Network

In this guide, we introduce some knowledge about computer networking. Specifically, we focus more on the higher layers, such as the application layer (HTTP) and transport layer (TCP & UDP).

## OSI (open systems interconnection) 7-layer model

- 7 layers: application layer, presentation layer, session layer, transport layer, network layer, data link layer, physical layer.
- Application layer: HTTP (hyper-text transmission protocol), SMTP (simple mail transfer protocol), etc.
- Transport layer: TCP (transmission control protocol, connection-oriented and reliable) and UDP (user datagram protocol, not connection-oriented and unreliable).
	- However, it turns out QUIC (quick UDP Internet connection) will be used to implement HTTP 3.0. Thus, UDP may not be that bad since it can help to reduce RTT (round-trip time).
- DNS helps to convert domain name to its IP address, while ARP helps to convert IP address to its corresponding MAC address.
- IP addresses are hierarchical, but MAC addresses are not.
	- That's the reason why we can have CIDR (class-less inter-domain routing) using IP addresses.

## What happens when we type an URL and press `Enter` in the browser?

_Below, let's say the entered URL is `https://drive.google.com/`._

- The browser checks in different caches for the DNS record to find the IP address for `https://drive.google.com/`;
	- Usually, it will check caches in the following sequence: browser level, OS level, router level, ISP level.
- If cache miss, ISP's DNS server initiates a DNS query to get the IP address for `https://drive.google.com/`;
	- In this case, the ISP's DNS server is called _DNS recursor_ while the other DNS servers are called _name servers_.
	- There are 3 levels of name servers: （global) root name server, `.com` top-level name server, `google.com` second-level name server, and `drive.google.com` third-level name server.
- The browser initiates a TCP connection with the server (identified by its IP address);
	- To establish such a connection, an TCP/IP 3-way handshaking process is performed.
	- 3-way handshake: client sends a SYN packet to server, server responds a SYN/ACK packet, client sends an ACK packet.
- The browser sends an HTTP request to the server with the given port.
- The server handles the incoming request and processes it with a server-side program.
- The server sends an HTTP response back to the client.
- The browser displays the HTML content based on the HTTP response received.

## TCP 3-way handshaking & 4-way handshaking

- 3-way handshaking is used to establish an TCP connection, while 4-way handshaking is used to close an TCP connection.
- TCP 3-way handshaking process:
	- Client to server: SYN = 1, seq = x
	- Server to client: SYN = 1, ACK = 1, seq = y, ack = x + 1
	- Client to server: ACK = 1, seq = x + 1, ack = y + 1
- Why cannot we use 2-way handshaking to establish TCP connection (i.e., without the 3rd step)?
	- Let's say the client sends out an SYN packet but the packet got stuck in the network.
	- After timeout, the client sends out a 2nd SYN packet and this one successfully reaches the server. The server then sends out an SYN/ACK packet and establishes an TCP connection. After a while, the client closes the connection and it is shut down.
	- After a long time, the 1st SYN packet finally reaches the server. The server then sends out an SYN/ACK packet and establishes another TCP connection. However, since the client is shut down already, this connection will never be closed. This is a waste of server resources.
- TCP 4-way handshaking process:
	- Client to server: FIN = 1, seq = u
	- Server to client: ACK = 1, seq = v, ack = u + 1
	- Server to client: FIN = 1, ACK = 1, seq = w, ack = u + 1

## Differences between TCP and UDP

- TCP is connection-oriented and provides reliable connection.
	- TCP does not provide broadcast and multicast.
- UDP is not connection-oriented and provides unreliable connection.
	- UDP is more efficient, thus it is used for DNS, real time media, etc.
- How TCP can guarantee reliable transmission:
	- Sequence number, checksum, duplicate detection, flow control, congestion control, ARQ (auto repeat request), timeout (redeliver).

## Differences between HTTP short and long connection

- In HTTP 1.0, short connection is used by default.
- In HTTP 1.1, however, long connection is used by specifying `Connection: keep-alive` in the header.
	- Different from web socket, long connection only eliminates the need for opening/closing TCP connection. The basic HTTP request/response model is still followed per communication.
- WebSocket creates a persistent, full-duplex connection. This allows the server to push data to the client at any time.
	- WebSocket is not part of the HTTP standard. It is a different protocol.

## Differences between cookie, session and JWT token

- Cookie is stored at the client side, and is used to solve the problem that HTTP is state-less.
- Session is stored at the server side, and has to be used together with cookie by storing a `SESSION_ID` in cookie.
- JWT is stored at the client side (using local storage or session storage), and can solve the scalability problem of the traditional session & cookie approach.
- How to store session when the server side is distributed:
	- Enable sticky session at the load balancer (or reverse proxy) layer;
	- Store the session in a centralized place, such as Redis cache;
	- Use JWT token instead.

## Differences between HTTP and HTTPS

- By default, HTTP is on port 80, while HTTPS is on port 443.
- Although HTTP and HTTPS both run on top of TCP, HTTPS is more secured with the help of TLS/SSL.
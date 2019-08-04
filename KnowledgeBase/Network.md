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

- 

## Differences between TCP and UDP

- 

## How TCP can guarantee reliable transmission

- 

## Differences between HTTP short and long connection

- 

## Differences between cookie, session and JWT token

- 

## Differences between HTTP and HTTPS

- 

## Differences between URI and URL

- 

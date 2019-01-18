package Server;

import java.io.*;
import java.net.*;

public class Server {
	// Initialize socket, input & output streams
	private Socket socket = null;
	private ServerSocket server = null;
	private DataInputStream in, input = null;
	private DataOutputStream out = null;
	// Constructor with Port
	public Server(int port)
	{
		// Starts server and waits for a connection
		try
		{
			server = new ServerSocket(port);
			System.out.println("Server started");
			System.out.println("Waiting for a client ...");
			socket = server.accept();
			System.out.println("Client accepted");
			// Takes input from the client socket
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			input = new DataInputStream(System.in);
			// Sends output to the socket
			out = new DataOutputStream(socket.getOutputStream());
			
			String line1 = "", line2="";
			// Reads message from client until "Over" is sent
			while (!line2.equals("Over"))
			{
				try
				{
					line1 = in.readUTF();
					System.out.println("Client: " + line1);
					line2 = input.readLine();
					out.writeUTF(line2);
					out.flush();
				}
				catch(IOException i)
				{
					System.out.println(i);
				}
			}
			System.out.println("Closing connection");
			// Close connection
			socket.close();
			in.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
	}

	public static void main(String[] args) {
		Server server = new Server(5000);
	}

}

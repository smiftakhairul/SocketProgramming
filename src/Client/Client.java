package Client;

import java.io.*;
import java.net.*;

public class Client {
	// Initialize socket, input & output streams
	private Socket socket = null;
	private DataInputStream input, in = null;
	private DataOutputStream out = null;
	// Constructor to put IP address and port
	public Client(String address, int port)
	{
		// Establish a connection
		try
		{
			socket = new Socket(address, port);
			System.out.println("Connected");
			// Takes input from terminal
			input = new DataInputStream(System.in);
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			// Sends output to the socket
			out = new DataOutputStream(socket.getOutputStream());
		}
		catch(UnknownHostException u)
		{
			System.out.println(u);
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
		// String to read message from input
		String line1 = "", line2 = "";
		// Keep reading until "Over" is input
		while (!line2.equals("Over"))
		{
			try
			{
				line1 = input.readLine();
				out.writeUTF(line1);
				out.flush();
				line2 = in.readUTF();
				System.out.println("Server: " + line2);
			}
			catch(IOException i)
			{
				System.out.println(i);
			}
		}
		// Close connection
		try
		{
			input.close();
			out.close();
			socket.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
	}
		
	public static void main(String[] args) {
		Client client = new Client("127.0.0.1", 5000);
	}

}

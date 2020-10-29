package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    boolean isStop = false;
                    String answer = "";
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.toLowerCase().contains("msg=")) {
                            if (str.toLowerCase().contains("exit")) {
                                isStop = true;
                            }
                            if (str.toLowerCase().contains("hello")) {
                                answer = "Hello, dear friend!";
                            } else {
                                answer = str.split("=")[1].split(" ")[0];
                            }
                        }
                    }
                    if (isStop) {
                        break;
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(answer.getBytes());
                }
            }
        }
    }
}

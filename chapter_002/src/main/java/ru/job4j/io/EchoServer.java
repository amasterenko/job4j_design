package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try {
            try (ServerSocket server = new ServerSocket(9000)) {
                while (true) {
                    Socket socket = server.accept();
                    try (OutputStream out = socket.getOutputStream();
                         BufferedReader in = new BufferedReader(
                                 new InputStreamReader(socket.getInputStream()))) {
                        boolean isStop = false;
                        String answer = "";
                        String str = in.readLine();
                        while (!str.isEmpty()) {
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
                            str = in.readLine();
                        }
                        if (isStop) {
                            break;
                        }
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write(answer.getBytes());
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("An exception has occurred: ", e);
        }
    }
}

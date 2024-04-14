package com.kyeeego.stepikwebserver;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.util.IO;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.concurrent.Future;

@Slf4j
public class SocketServer {

    private ServerSocketChannel socket;

    public SocketServer() {
        try {
            socket = ServerSocketChannel.open();

            socket.configureBlocking(false);
            log.info("Initialized & started server");
        } catch (IOException e) {
            log.error("Unable to initialize server: ", e);
        }
    }

    public void listen(int port) throws IOException {
        var addr = new InetSocketAddress("localhost", port);
        socket.bind(addr);

        log.info("Listening on port: " + port);

        while (true) {
            var client = socket.accept();
            if (client != null) {
                new Thread(() -> {
                    try {
                        var buffer = ByteBuffer.allocate(32);
                        while (true) {
                            if (client.read(buffer) < 0) {
                                client.close();
                                log.info("Closed connection");
                                return;
                            }

                            buffer.flip();
                            client.write(buffer);
                            buffer.clear();
                        }
                    } catch (IOException e) {
                        log.error("Error while working with the connection: " + e);
                    }
                }).start();

                log.info("Accepted connection");
            }
        }
    }
}

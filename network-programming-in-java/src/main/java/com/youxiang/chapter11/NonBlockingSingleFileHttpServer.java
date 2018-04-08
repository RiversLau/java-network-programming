package com.youxiang.chapter11;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

/**
 * @author: Rivers
 * @date: 2018/4/8
 */
public class NonBlockingSingleFileHttpServer {

    private ByteBuffer contentBuffer;
    private int port = 8080;

    public NonBlockingSingleFileHttpServer(ByteBuffer data, String encoding, String mimeType, int port) {
        this.port = port;
        String header = "HTTP/1.0 200 OK\r\n"
                + "Server: NonBlockingSingleFileHttpServer\r\n"
                + "Content-length: " + data.limit() + "\r\n"
                + "Content-type: " + mimeType + "\r\n";
        byte[] headerData = header.getBytes(Charset.forName("US-ASCII"));

        ByteBuffer buffer = ByteBuffer.allocate(data.limit() + headerData.length);
        buffer.put(headerData);
        buffer.put(data);
        buffer.flip();
        this.contentBuffer = buffer;
    }

    public void run() throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverChannel.socket();
        Selector selector = Selector.open();
        InetSocketAddress localPort = new InetSocketAddress(port);
        serverSocket.bind(localPort);
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel channel = server.accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isWritable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        if (buffer.hasRemaining()) {
                            channel.write(buffer);
                        } else {
                            channel.close();
                        }
                    } else if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(4096);
                        channel.read(buffer);
                        key.interestOps(SelectionKey.OP_WRITE);
                        key.attach(contentBuffer.duplicate());
                    }
                } catch (IOException e) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException e1) {
                        //do nothing
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String filePath = "/Users/Rivers/Rivers/test/index.html";
        String contentType = URLConnection.getFileNameMap().getContentTypeFor(filePath);
        try {
            Path file = FileSystems.getDefault().getPath(filePath);
            byte[] data = Files.readAllBytes(file);
            ByteBuffer input = ByteBuffer.wrap(data);

            NonBlockingSingleFileHttpServer server = new NonBlockingSingleFileHttpServer(input, "UTF-8", contentType, 8080);
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

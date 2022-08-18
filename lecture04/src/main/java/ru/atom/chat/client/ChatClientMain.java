package ru.atom.chat.client;

import okhttp3.Response;
import org.springframework.boot.SpringApplication;
import ru.atom.chat.server.ChatApplication;

import java.awt.image.RescaleOp;
import java.io.IOException;

public class ChatClientMain {
    public static void main(String[] args) throws IOException {
        Response resp = ChatClient.login("Alex");
        System.out.println(resp.headers().toString());
        System.out.println(resp.body().string());
    }
}

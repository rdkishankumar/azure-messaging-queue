package com.azure.demo.azure_queue_demo;

import org.springframework.stereotype.Service;
import com.azure.storage.queue.QueueClient;

@Service
public class QueueProducer {

    private final QueueClient queueClient;

    public QueueProducer(QueueClient queueClient) {
        this.queueClient = queueClient;
    }

    public void sendMessage(String message) {
        queueClient.sendMessage(message);
        System.out.println("Message sent: " + message);
        System.out.println("Message sent: " + message);
    }
}
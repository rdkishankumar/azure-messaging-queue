package com.azure.demo.azure_queue_demo;
import com.azure.core.util.Context;
import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.models.PeekedMessageItem;
import com.azure.storage.queue.models.QueueMessageItem;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class QueueConsumer {

    private final QueueClient queueClient;

    public QueueConsumer(QueueClient queueClient) {
        this.queueClient = queueClient;
    }

    public void receiveMessages() {
        List<QueueMessageItem> messages = queueClient.receiveMessages(10).stream().toList();

        for (QueueMessageItem msg : messages) {
            System.out.println("Received: " + msg.getMessageText());

            // Delete after processing
            queueClient.deleteMessage(msg.getMessageId(), msg.getPopReceipt());
        }
    }

    public List<String> peekMessages() {
        return queueClient.peekMessages(
                        10,                          // max messages
                        Duration.ofSeconds(5),       // request timeout
                        Context.NONE                 // required context
                )
                .stream()
                .map(PeekedMessageItem::getMessageText)
                .toList();
    }

}
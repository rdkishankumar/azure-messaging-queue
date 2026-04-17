package com.azure.demo.azure_queue_demo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queue")
public class QueueController {

    private final QueueProducer producer;
    private final QueueConsumer consumer;

    public QueueController(QueueProducer producer, QueueConsumer consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

    @PostMapping("/send")
    public String send(@RequestParam String msg) {
        producer.sendMessage(msg);
        return "Message sent!";
    }

    @GetMapping("/receive")
    public String receive() {
        consumer.receiveMessages();
        return "Messages processed!";
    }

    @GetMapping("/peek")
    public List<String> peekMessages() {
        return consumer.peekMessages();
    }

}
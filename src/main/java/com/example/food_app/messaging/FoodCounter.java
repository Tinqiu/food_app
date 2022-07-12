package com.example.food_app.messaging;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class FoodCounter {
    private final Channel channel;
    private final String queueName;
    private final AtomicLong foodCount = new AtomicLong(0);

    public FoodCounter() throws IOException, TimeoutException {
        Connection conn = getConnection();
        this.channel = conn.createChannel();
        this.queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, "branded-food-exchange", "");
    }

    @PostConstruct
    public void receive() throws IOException {
        DeliverCallback callback = (consumerTag, delivery) -> foodCount.getAndIncrement();
        channel.basicConsume(queueName, true, callback, consumerTag -> {
        });
    }

    public Long getCount() {
        return foodCount.get();
    }


    private Connection getConnection() throws IOException, TimeoutException {
        var connFactory = new ConnectionFactory();
        connFactory.setHost("localhost");
        return connFactory.newConnection("branded-food-conn");
    }
}

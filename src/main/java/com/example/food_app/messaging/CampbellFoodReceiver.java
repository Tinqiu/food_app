package com.example.food_app.messaging;

import com.example.food_app.model.CampbellFood;
import com.example.food_app.persistence.CampbellFoodRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Component
public class CampbellFoodReceiver {
    private final Channel channel;
    private final String queueName;
    private final CampbellFoodRepository foodRepository;

    private static final ObjectMapper mapper = new ObjectMapper();

    public CampbellFoodReceiver(CampbellFoodRepository foodRepository) throws IOException, TimeoutException {
        Connection conn = getConnection();
        this.channel = conn.createChannel();
        this.queueName = channel.queueDeclare().getQueue();
        this.foodRepository = foodRepository;
        channel.queueBind(queueName, "branded-food-exchange", "campbell");
    }

    @PostConstruct
    public void receive() throws IOException {
        DeliverCallback callback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            var brandedFood = mapper.readValue(message, CampbellFood.class);
            foodRepository.save(brandedFood);
        };

        channel.basicConsume(queueName, true, callback, consumerTag -> {
        });
    }


    private Connection getConnection() throws IOException, TimeoutException {
        var connFactory = new ConnectionFactory();
        connFactory.setHost("localhost");
        return connFactory.newConnection("branded-food-conn");
    }
}

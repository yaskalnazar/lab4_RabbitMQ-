package lab4.rabbit.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

public class Subscriber {

    public static void main(String[] args) throws Exception {
        Connection connection = new ConnectionFactory().newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("logs", "fanout");

        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, "logs", "");

        System.out.println("Waiting for messages");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("Received '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }

}

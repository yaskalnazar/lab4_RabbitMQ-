package lab4.rabbit.queue.producer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import static lab4.rabbit.Utils.sleep;

public class Producer {

    private String host;
    private Integer hostPort;
    private Boolean durable;
    private Map<String, Object> queueArgs;
    protected String queueName;

    public Producer(String host, Integer hostPort, String queueName, Boolean durable, Map<String, Object> queueArgs) {
        this.host = host;
        this.hostPort = hostPort;
        this.queueName = queueName;
        this.durable = durable;
        this.queueArgs = queueArgs;
    }

    public void produce(int numberOfMessages, AMQP.BasicProperties properties, Long latency) {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try(Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare(queueName, durable, false, false, queueArgs);

            channel.exchangeDeclare("my-dead-letter-exchange", "fanout");
            channel.queueDeclare("my-dead-letter-queue", durable, false, false, null);
            channel.queueBind("my-dead-letter-queue", "my-dead-letter-exchange", "bindingKey");


            channel.exchangeDeclare("test_exchange", "fanout");
            channel.queueBind(queueName, "test_exchange", "bindingKey");


            for (int i = 1; i < numberOfMessages; i++) {
                String message = "Message#" + i;
                channel.basicPublish("test_exchange", "bindingKey", properties, message.getBytes());
                System.out.println("Sent '" + message + "'");
                sleep(latency);
            }
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}

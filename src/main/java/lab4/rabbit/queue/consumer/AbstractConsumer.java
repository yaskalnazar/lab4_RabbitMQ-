package lab4.rabbit.queue.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Delivery;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public abstract class AbstractConsumer {

    private String host;
    private Integer hostPort;
    private String queueName;
    private Boolean autoAck;
    private Boolean durable;
    private Map<String, Object> queueArgs;

    protected Long latency;
    protected Channel channel;

    public AbstractConsumer(String host,
                            Integer hostPort,
                            String queueName,
                            Boolean autoAck,
                            Boolean durable,
                            Map<String, Object> queueArgs,
                            Long latency) {
        this.host = host;
        this.hostPort = hostPort;
        this.queueName = queueName;
        this.autoAck = autoAck;
        this.durable = durable;
        this.queueArgs = queueArgs;
        this.latency = latency;
    }

    public void start() throws IOException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        channel = createChannel(connectionFactory);

        channel.queueDeclare(queueName, durable, false, false, queueArgs);
        System.out.println("Waiting for messages.");

        channel.basicConsume(queueName, autoAck, this::handleMessage, consumerTag -> {});
    }

    protected abstract void handleMessage(String consumerTag, Delivery message) throws IOException;

    private Channel createChannel(ConnectionFactory connectionFactory) {
        try {
            Connection connection = connectionFactory.newConnection();
            return connection.createChannel();
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}

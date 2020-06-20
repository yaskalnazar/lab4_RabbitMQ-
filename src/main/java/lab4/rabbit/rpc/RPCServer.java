package lab4.rabbit.rpc;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

import static lab4.rabbit.rpc.Constants.REQUEST_QUEUE_NAME;

public class RPCServer {

    private static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(REQUEST_QUEUE_NAME, false, false, false, null);
        channel.queuePurge(REQUEST_QUEUE_NAME);

        channel.basicQos(1);

        System.out.println("Awaiting RPC requests");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                    .Builder()
                    .correlationId(delivery.getProperties().getCorrelationId())
                    .build();

            String response = "";

            try {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                int n = Integer.parseInt(message);

                System.out.println("fib(" + message + ")");
                response += fib(n);
            } catch (RuntimeException e) {
                System.out.println(e.toString());
            } finally {
                channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, response.getBytes(StandardCharsets.UTF_8));
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };

        channel.basicConsume(REQUEST_QUEUE_NAME, false, deliverCallback, (consumerTag -> {
        }));
    }
}

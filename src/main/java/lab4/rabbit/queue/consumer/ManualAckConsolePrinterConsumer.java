package lab4.rabbit.queue.consumer;

import com.rabbitmq.client.Delivery;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static lab4.rabbit.Utils.sleep;

public class ManualAckConsolePrinterConsumer extends AbstractConsumer{

    public ManualAckConsolePrinterConsumer(String host,
                                           Integer hostPort,
                                           String queueName,
                                           Boolean durable,
                                           Map<String, Object> queueArgs,
                                           Long latency) {
        super(host, hostPort, queueName, false, durable, queueArgs, latency);
    }

    @Override
    protected void handleMessage(String consumerTag, Delivery rawMessage) throws IOException {
        channel.basicQos(1);
        String message = new String(rawMessage.getBody(), StandardCharsets.UTF_8);
        System.out.println("Received '" + message + "'");
        try {
            sleep(latency);
        }
        finally {
            System.out.println("Done");
            channel.basicAck(rawMessage.getEnvelope().getDeliveryTag(), false);
        }
    }

}

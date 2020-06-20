package lab4.rabbit.queue.consumer;

import com.rabbitmq.client.Delivery;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static lab4.rabbit.Utils.sleep;

public class AutoAckConsolePrinterConsumer extends AbstractConsumer {

    public AutoAckConsolePrinterConsumer(String host,
                                         Integer hostPort,
                                         String queueName,
                                         Boolean durable,
                                         Map<String, Object> queueArgs,
                                         Long latency) {
        super(host, hostPort, queueName, true, durable, queueArgs, latency);
    }

    @Override
    protected void handleMessage(String consumerTag, Delivery delivery) throws IOException {
        String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
        System.out.println("Received '" + message + "'");
        sleep(latency);
        System.out.println("Done");
    }
}

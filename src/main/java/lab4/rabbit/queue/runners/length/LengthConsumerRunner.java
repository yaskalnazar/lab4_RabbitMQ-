package lab4.rabbit.queue.runners.length;

import lab4.rabbit.queue.consumer.AbstractConsumer;
import lab4.rabbit.queue.consumer.ManualAckConsolePrinterConsumer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static lab4.rabbit.NetworkConstants.HOST;
import static lab4.rabbit.NetworkConstants.PORT;
import static lab4.rabbit.queue.Constants.LENGTH_LIMIT_QUEUE;

public class LengthConsumerRunner {

    public static void main(String[] args) throws IOException {
        Map<String, Object> queueConfig = new HashMap<>();
        queueConfig.put("x-max-length", 5);
        queueConfig.put("x-overflow", "reject-publish");
        queueConfig.put("x-dead-letter-exchange", "my-dead-letter-exchange");
        queueConfig.put("x-dead-letter-routing-key", "bindingKey");


        AbstractConsumer consumer = new ManualAckConsolePrinterConsumer(HOST,
                PORT,
                LENGTH_LIMIT_QUEUE,
                false,
                queueConfig,
                1000L);

        consumer.start();
    }
}

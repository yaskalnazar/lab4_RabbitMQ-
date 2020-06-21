package lab4.rabbit.queue.runners.length;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lab4.rabbit.queue.producer.Producer;
import org.springframework.amqp.core.QueueBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import static lab4.rabbit.NetworkConstants.HOST;
import static lab4.rabbit.NetworkConstants.PORT;
import static lab4.rabbit.queue.Constants.LENGTH_LIMIT_QUEUE;

public class LengthProducerRunner {

    public static void main(String[] args) throws IOException {
        Map<String, Object> queueConfig = new HashMap<>();
        queueConfig.put("x-max-length", 5);
        queueConfig.put("x-overflow", "reject-publish");
        queueConfig.put("x-dead-letter-exchange", "my-dead-letter-exchange");
        queueConfig.put("x-dead-letter-routing-key", "bindingKey");

        Producer producer = new Producer(HOST,
                PORT,
                LENGTH_LIMIT_QUEUE,
                false,
                queueConfig);

        producer.produce(21, null, 1000L);
    }

}

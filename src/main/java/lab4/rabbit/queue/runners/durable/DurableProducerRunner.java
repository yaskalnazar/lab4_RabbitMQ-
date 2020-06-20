package lab4.rabbit.queue.runners.durable;

import com.rabbitmq.client.MessageProperties;
import lab4.rabbit.queue.producer.Producer;

import java.io.IOException;

import static lab4.rabbit.NetworkConstants.HOST;
import static lab4.rabbit.NetworkConstants.PORT;
import static lab4.rabbit.queue.Constants.DURABLE_QUEUE;

public class DurableProducerRunner {

    public static void main(String[] args) throws IOException {
        Producer producer = new Producer(HOST,
                PORT,
                DURABLE_QUEUE,
                true,
                null);

        producer.produce(1000, MessageProperties.PERSISTENT_TEXT_PLAIN, 10L);
    }

}

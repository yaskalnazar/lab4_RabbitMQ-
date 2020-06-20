package lab4.rabbit.queue.runners.ttl;

import com.rabbitmq.client.AMQP;
import lab4.rabbit.queue.producer.Producer;

import java.io.IOException;

import static lab4.rabbit.NetworkConstants.HOST;
import static lab4.rabbit.NetworkConstants.PORT;
import static lab4.rabbit.queue.Constants.TTL_QUEUE;

public class TtlProducerRunner {

    public static void main(String[] args) throws IOException {
        Producer producer = new Producer(HOST,
                PORT,
                TTL_QUEUE,
                false,
                null);

        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .contentType("text/plain")
                .expiration("10") // work only for one message at top of queue
                .build();

        producer.produce(100, properties, 10L);
    }

}

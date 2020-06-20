package lab4.rabbit.queue.runners.length;

import lab4.rabbit.queue.producer.Producer;

import java.io.IOException;
import java.util.Map;

import static lab4.rabbit.NetworkConstants.HOST;
import static lab4.rabbit.NetworkConstants.PORT;
import static lab4.rabbit.queue.Constants.LENGTH_LIMIT_QUEUE;

public class LengthProducerRunner {

    public static void main(String[] args) throws IOException {
        Map<String, Object> queueConfig = Map.of(
                "x-max-length", 10
        );

        Producer producer = new Producer(HOST,
                PORT,
                LENGTH_LIMIT_QUEUE,
                false,
                queueConfig);

        producer.produce(100, null, 10L);
    }

}

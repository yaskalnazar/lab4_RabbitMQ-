package lab4.rabbit.queue.runners.noack;

import lab4.rabbit.queue.producer.Producer;

import java.io.IOException;

import static lab4.rabbit.NetworkConstants.HOST;
import static lab4.rabbit.NetworkConstants.PORT;
import static lab4.rabbit.queue.Constants.NO_ACK_QUEUE;

public class NoackProducerRunner {

    public static void main(String[] args) throws IOException {
        Producer producer = new Producer(HOST,
                PORT,
                NO_ACK_QUEUE,
                false,
                null);

        producer.produce(10, null, 500L);
    }

}

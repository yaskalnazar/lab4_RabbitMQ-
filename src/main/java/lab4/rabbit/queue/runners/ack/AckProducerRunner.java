package lab4.rabbit.queue.runners.ack;

import lab4.rabbit.queue.producer.Producer;

import java.io.IOException;

import static lab4.rabbit.NetworkConstants.HOST;
import static lab4.rabbit.NetworkConstants.PORT;
import static lab4.rabbit.queue.Constants.ACK_QUEUE;

public class AckProducerRunner {

    public static void main(String[] args) throws IOException {
        Producer producer = new Producer(HOST,
                PORT,
                ACK_QUEUE,
                false,
                null);

        producer.produce(15, null, 2500L);
    }

}

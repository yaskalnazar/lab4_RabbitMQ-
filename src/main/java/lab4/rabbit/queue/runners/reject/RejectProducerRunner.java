package lab4.rabbit.queue.runners.reject;

import lab4.rabbit.queue.producer.Producer;

import java.io.IOException;

import static lab4.rabbit.NetworkConstants.HOST;
import static lab4.rabbit.NetworkConstants.PORT;
import static lab4.rabbit.queue.Constants.REJECT_QUEUE;

public class RejectProducerRunner {

    public static void main(String[] args) throws IOException {
        Producer producer = new Producer(HOST,
                PORT,
                REJECT_QUEUE,
                false,
                null);

        producer.produce(100, null, 1000L);
    }

}

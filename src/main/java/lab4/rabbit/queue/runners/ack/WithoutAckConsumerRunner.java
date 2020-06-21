package lab4.rabbit.queue.runners.ack;

import lab4.rabbit.queue.consumer.AbstractConsumer;
import lab4.rabbit.queue.consumer.WithoutAckConsolePrinterConsumer;

import java.io.IOException;

import static lab4.rabbit.NetworkConstants.HOST;
import static lab4.rabbit.NetworkConstants.PORT;
import static lab4.rabbit.queue.Constants.ACK_QUEUE;

public class WithoutAckConsumerRunner {

    public static void main(String[] args) throws IOException {
        AbstractConsumer consumer = new WithoutAckConsolePrinterConsumer(HOST,
                PORT,
                ACK_QUEUE,
                false,
                null,
                5000L);

        consumer.start();
    }
}

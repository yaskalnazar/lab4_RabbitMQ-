package lab4.rabbit.queue.runners.ttl;

import lab4.rabbit.queue.consumer.AbstractConsumer;
import lab4.rabbit.queue.consumer.ManualAckConsolePrinterConsumer;

import java.io.IOException;

import static lab4.rabbit.NetworkConstants.HOST;
import static lab4.rabbit.NetworkConstants.PORT;
import static lab4.rabbit.queue.Constants.TTL_QUEUE;

// power off and messages will be removed
public class TtlConsumerRunner {

    public static void main(String[] args) throws IOException {
        AbstractConsumer consumer = new ManualAckConsolePrinterConsumer(HOST,
                PORT,
                TTL_QUEUE,
                false,
                null,
                1000L);

        consumer.start();
    }
}

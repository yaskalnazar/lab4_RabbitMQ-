package lab4.rabbit.queue.runners.durable;

import lab4.rabbit.queue.consumer.AbstractConsumer;
import lab4.rabbit.queue.consumer.ManualAckConsolePrinterConsumer;

import java.io.IOException;

import static lab4.rabbit.NetworkConstants.HOST;
import static lab4.rabbit.NetworkConstants.PORT;
import static lab4.rabbit.queue.Constants.DURABLE_QUEUE;

public class DurableConsumerRunner {

    public static void main(String[] args) throws IOException {
        AbstractConsumer consumer = new ManualAckConsolePrinterConsumer(HOST,
                PORT,
                DURABLE_QUEUE,
                true,
                null,
                1000L);

        consumer.start();
    }
}

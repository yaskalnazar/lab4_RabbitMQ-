package lab4.rabbit.queue.runners.reject;

import lab4.rabbit.queue.consumer.AbstractConsumer;
import lab4.rabbit.queue.consumer.RejectConsolePrinterConsumer;

import java.io.IOException;

import static lab4.rabbit.NetworkConstants.HOST;
import static lab4.rabbit.NetworkConstants.PORT;
import static lab4.rabbit.queue.Constants.REJECT_QUEUE;

public class RejectConsumerRunner {

    public static void main(String[] args) throws IOException {
        AbstractConsumer consumer = new RejectConsolePrinterConsumer(HOST,
                PORT,
                REJECT_QUEUE,
                false,
                null,
                2000L);

        consumer.start();
    }
}

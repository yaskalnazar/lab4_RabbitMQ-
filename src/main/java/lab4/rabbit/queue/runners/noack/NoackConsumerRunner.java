package lab4.rabbit.queue.runners.noack;

import lab4.rabbit.queue.consumer.AbstractConsumer;
import lab4.rabbit.queue.consumer.AutoAckConsolePrinterConsumer;

import java.io.IOException;

import static lab4.rabbit.NetworkConstants.HOST;
import static lab4.rabbit.NetworkConstants.PORT;
import static lab4.rabbit.queue.Constants.NO_ACK_QUEUE;

public class NoackConsumerRunner {

    public static void main(String[] args) throws IOException {
        AbstractConsumer consumer = new AutoAckConsolePrinterConsumer(HOST,
                PORT,
                NO_ACK_QUEUE,
                false,
                null,
                1000L);

        consumer.start();
    }
}

package concurrency.chatgptexample;

public class Main {
    public static void main(String[] args) {
        Message message = new Message();
        Thread producerThread = new Thread(new Producer(message));
        Thread consumerThread = new Thread(new Consumer(message));

        producerThread.start();
        consumerThread.start();
    }
}

/**
 * In this example, we have a Message class that represents a shared message between a producer and a consumer. The setMessage() method is used by the producer to set a message, while the getMessage() method is used by the consumer to retrieve the message.
 *
 * The wait() method is called inside a synchronized block within both setMessage() and getMessage(). When a thread calls wait(), it releases the lock and waits until another thread calls notify() or notifyAll() on the same object to resume its execution.
 *
 * The producer thread sets three messages using the setMessage() method, and the consumer thread retrieves and prints those messages using the getMessage() method. The sleep() method is used to introduce delays for demonstration purposes.
 *
 * By using wait() and notify(), we ensure that the consumer thread waits for the producer to set a message before consuming it, and the producer thread waits for the consumer to consume the message before setting a new one, thereby achieving proper synchronization and inter-thread communication.
 */


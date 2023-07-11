package concurrency.chatgptexample;

class Message {
    private String content;
    private boolean isMessageReady;

    public synchronized void setMessage(String message) {
        while (isMessageReady) {
            try {
                wait(); // Wait until the message is consumed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        content = message;
        isMessageReady = true;
        System.out.println("Producer: Message set - " + message);
        notify(); // Notify the waiting consumer thread
    }

    public synchronized String getMessage() {
        while (!isMessageReady) {
            try {
                wait(); // Wait until a message is available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        String message = content;
        isMessageReady = false;
        System.out.println("Consumer: Message consumed - " + message);
        notify(); // Notify the waiting producer thread
        return message;
    }
}
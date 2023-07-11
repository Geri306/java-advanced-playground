package concurrency.chatgptexample;

class Consumer implements Runnable {
    private Message message;

    public Consumer(Message message) {
        this.message = message;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            String msg = message.getMessage();
            System.out.println("Consumer: Received message - " + msg);
            try {
                Thread.sleep(1000); // Simulate some processing time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
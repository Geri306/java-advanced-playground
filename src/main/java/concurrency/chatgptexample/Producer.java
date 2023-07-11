package concurrency.chatgptexample;

class Producer implements Runnable {
    private Message message;

    public Producer(Message message) {
        this.message = message;
    }

    public void run() {
        String[] messages = {"Hello", "World", "Bye"};
        for (String msg : messages) {
            message.setMessage(msg);
            try {
                Thread.sleep(1000); // Simulate some processing time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
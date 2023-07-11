package concurrency.mosh.threadsafety.synchronisation.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DownloadStatus {
    private int totalBytes;
    private Lock lock = new ReentrantLock(); // one of the implementations of the Lock interface

    public int getTotalBytes() {
        return totalBytes;
    }

    public void incrementTotalBytes() {
        // 3 steps:
        // 1. value is read from memory and store it in cpu (READ)
        // 2. cpu increments the value (INCREMENT)
        // 3. updated value is stored in memory (WRITE)
        // this is a non-atomic operation, because it involves multiple steps
        // in contrast, an atomic operation cannot be broken down into multiple steps
        lock.lock();
        try {
            totalBytes++;
        } finally { // if smth happens in the try-block we unlock the lock to let other threads access so that we avoid crashing the app
            lock.unlock();
        }
    }
}

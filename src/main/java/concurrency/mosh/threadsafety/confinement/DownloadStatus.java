package concurrency.mosh.threadsafety.confinement;

public class DownloadStatus {
    private int totalBytes;

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
        totalBytes++;
    }
}

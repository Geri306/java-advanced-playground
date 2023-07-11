package concurrency.mosh.threadsafety.synchronisation.synchronizedkeyword;

public class DownloadStatus {
    private int totalBytes;
    private int totalFiles;
    private Object totalBytesLock = new Object(); // dedicated monitor objects, plain Object instances, not Lock objects. It's just a common convention to use Object instances because we are not looking for any specific behavior, but technically we could use any type here.
    private Object totalFilesLock = new Object();

    public int getTotalBytes() {
        return totalBytes;
    }

    public void incrementTotalBytes() {
       /* synchronized (*//*monitor object*//*this) { // every object in java has a built-in lock, java is gonna get the built-in lock from that object and use it under the hood. Passing 'this' as a monitor object is a BAD PRACTICE! Using the same monitor object reduces the throughput of the class and thus causes bottlenecks. If a thread accesses this method to increment the field's value then another thread cannot access the incrementTotalFiles method to increment another (independent field's value) because both methods use 'this' object as a monitor object in the synchronized block. We should use dedicated monitor objects instead!
            totalBytes++;
        }*/

        synchronized (totalBytesLock) {
            totalBytes++;
        }
    }

    public synchronized void incrementTotalFiles() {
        // the 'synchronized' keyword in the method signature is equivalent to "synchronized (this) {}"

        /*synchronized (totalFilesLock) {
            totalFiles++;
        }*/

        totalFiles++;
    }

    public int getTotalFiles() {
        return totalFiles;
    }

}

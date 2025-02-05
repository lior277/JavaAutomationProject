package org.example.internals.utils;

public class SleepUtil {

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            // Re-interrupt the thread if it's interrupted
            Thread.currentThread().interrupt();
            System.err.println("Sleep was interrupted: " + e.getMessage());
        }
    }
}
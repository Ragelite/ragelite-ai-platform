package com.ragelite.util;

public class JobCounter {

    private static int activeJobs = 0;

    public static synchronized void incrementJobs() {
        activeJobs++;
    }

    public static synchronized void decrementJobs() {
        activeJobs--;
    }

    public static synchronized int getActiveJobs() {
        return activeJobs;
    }
}

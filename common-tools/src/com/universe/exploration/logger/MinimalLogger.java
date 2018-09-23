/**
 *
 */
package com.universe.exploration.logger;

import java.util.LinkedList;

/**
 * Simple for logger for on-screen events. Practically a queue, however as far as
 * I understand linkedlist is the most efficient way of implementing a queue.
 *
 * @author 22.10.2015 Teemu Puurunen
 */
public class MinimalLogger {
    /**
     * Maintains 10 log items
     */
    private LinkedList<String> log;

    public MinimalLogger() {
        log = new LinkedList<>();
    }

    private final int logSize = 10;

    public void add(String entry) {
        removeFirstIfFull();
        log.add(entry);
    }

    /**
     * Clean up log once full.
     */
    private void removeFirstIfFull() {
        if (log.size() == logSize) {
            log.removeFirst();
        }
    }

    /**
     * @return the log
     */
    public LinkedList<String> getLog() {
        return log;
    }
}

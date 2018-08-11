/**
 *
 */
package com.universe.exploration.logger;

import java.util.LinkedList;

/**
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

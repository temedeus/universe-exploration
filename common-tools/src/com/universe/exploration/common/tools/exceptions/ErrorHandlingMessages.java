/**
 *
 */
package com.universe.exploration.common.tools.exceptions;

/**
 * Error handling enum.
 *
 * @author 14.6.2015 Teemu Puurunen
 */
public enum ErrorHandlingMessages {
    RANDOMIZATION_FAILED(-1, "Randomization has failed!");

    /**
     * @return String errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    private final int errorCode;
    private final String errorMessage;

    ErrorHandlingMessages(int ec, String em) {
        this.errorCode = ec;
        this.errorMessage = em;
    }

    /**
     * @return the errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }
}

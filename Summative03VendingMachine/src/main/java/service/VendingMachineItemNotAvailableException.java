package service;

public class VendingMachineItemNotAvailableException extends Exception {
    public VendingMachineItemNotAvailableException(String message) {
        super(message);
    }

    public VendingMachineItemNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

}

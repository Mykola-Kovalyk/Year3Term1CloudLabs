package ua.iot.kovalyk.mysqlspringlab.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Object id) {
        super("Could not find resource with id: " + id);
    }
}

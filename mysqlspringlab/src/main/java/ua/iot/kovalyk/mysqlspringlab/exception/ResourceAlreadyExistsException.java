package ua.iot.kovalyk.mysqlspringlab.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(Object id) {
        super("Entry with this value already exists: " + id);
    }
}

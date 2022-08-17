package by.exlab.shire_test.exception;

public class EntityNotFoundException extends RuntimeException{

  public EntityNotFoundException() {
    super();
  }

  public EntityNotFoundException(String message) {
    super(message);
  }

}

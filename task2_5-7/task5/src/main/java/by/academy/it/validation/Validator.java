package by.academy.it.validation;

public interface Validator {

    boolean isValidDate(String Date);

    boolean isValidValue(double value);

    static Validator take(){
        return DataValidator.getInstance();
    }
}

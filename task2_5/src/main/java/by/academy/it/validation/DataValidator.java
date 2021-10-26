package by.academy.it.validation;

public class DataValidator implements Validator {

    private static DataValidator instance;

    String DATE_FORMAT_REG = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$";

    private DataValidator() {
    }

    public static DataValidator getInstance() {
        if (instance == null) {
            instance = new DataValidator();
        }
        return instance;
    }

    @Override
    public boolean isValidDate(String dateString) {
       return dateString.matches(DATE_FORMAT_REG);
    }

    @Override
    public boolean isValidValue(double value) {
        return value > 0;
    }
}

package by.epam.javatraining.restaurant.model.validator;

public class ValidatorFactory {
    private static final ValidatorFactory VALIDATOR = new ValidatorFactory();
    private final SignInValidator signInValidator = new SignInValidator();

    private ValidatorFactory() {}

    public static ValidatorFactory getValidator() {
        return VALIDATOR;
    }

    public SignInValidator getSignInValidator() {
        return signInValidator;
    }
}

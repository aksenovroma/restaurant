package by.epam.javatraining.restaurant.model.validator;

public class ValidatorFactory {
    private static final ValidatorFactory VALIDATOR = new ValidatorFactory();
    private final SignInValidator signInValidator = new SignInValidator();
    private final SignUpValidator signUpValidator = new SignUpValidator();
    private final AddDishValidator addDishValidator = new AddDishValidator();

    private ValidatorFactory() {}

    public static ValidatorFactory getValidator() {
        return VALIDATOR;
    }

    public SignInValidator getSignInValidator() {
        return signInValidator;
    }

    public SignUpValidator getSignUpValidator() {
        return signUpValidator;
    }

    public AddDishValidator getAddDishValidator() {
        return addDishValidator;
    }
}

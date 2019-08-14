package by.epam.javatraining.restaurant.model.validator;

import org.apache.struts.mock.MockHttpServletRequest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SignInValidatorTest {
    private MockHttpServletRequest mockHttpServletRequest;
    private Validator validator;

    @BeforeMethod
    public void setValue() {
        mockHttpServletRequest = new MockHttpServletRequest();
        validator = ValidatorFactory.getValidator().getSignInValidator();
    }

    @Test
    public void testValidate() {
        mockHttpServletRequest.addParameter("login", "hris@mail.ru");
        mockHttpServletRequest.addParameter("password", "123");

        assertTrue(validator.validate(mockHttpServletRequest));
    }

    @Test
    public void testValidateWrongPassword() {
        mockHttpServletRequest.addParameter("login", "hris@mail.ru");
        mockHttpServletRequest.addParameter("password", "123q");

        assertFalse(validator.validate(mockHttpServletRequest));
    }

    @Test
    public void testValidateNullLogin() {
        mockHttpServletRequest.addParameter("login", null);
        mockHttpServletRequest.addParameter("password", "123");

        assertFalse(validator.validate(mockHttpServletRequest));
    }

    @Test
    public void testValidateNullPassword() {
        mockHttpServletRequest.addParameter("login", "hris@mail.ru");
        mockHttpServletRequest.addParameter("password", null);

        assertFalse(validator.validate(mockHttpServletRequest));
    }

    @Test
    public void testValidateNullLoginAndPassword() {
        mockHttpServletRequest.addParameter("login", null);
        mockHttpServletRequest.addParameter("password", null);

        assertFalse(validator.validate(mockHttpServletRequest));
    }

}

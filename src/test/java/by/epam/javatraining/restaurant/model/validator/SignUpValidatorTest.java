package by.epam.javatraining.restaurant.model.validator;

import org.apache.struts.mock.MockHttpServletRequest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SignUpValidatorTest {
    private MockHttpServletRequest mockHttpServletRequest;
    private Validator validator;

    @BeforeMethod
    public void setValue() {
        mockHttpServletRequest = new MockHttpServletRequest();
        validator = ValidatorFactory.getValidator().getSignUpValidator();
    }

    @Test
    public void testValidator() {
        mockHttpServletRequest.addParameter("login", "pasha@gmail.com");
        mockHttpServletRequest.addParameter("password", "123");
        mockHttpServletRequest.addParameter("name", "Паша");

        assertTrue(validator.validate(mockHttpServletRequest));
    }

    @Test
    public void testValidatorExistLogin() {
        mockHttpServletRequest.addParameter("login", "hris@mail.ru");
        mockHttpServletRequest.addParameter("password", "123");
        mockHttpServletRequest.addParameter("name", "Паша");

        assertFalse(validator.validate(mockHttpServletRequest));
    }

    @Test
    public void testValidatorNullLogin() {
        mockHttpServletRequest.addParameter("login", null);
        mockHttpServletRequest.addParameter("password", "123");
        mockHttpServletRequest.addParameter("name", "Паша");

        assertFalse(validator.validate(mockHttpServletRequest));
    }

    @Test
    public void testValidatorNullName() {
        mockHttpServletRequest.addParameter("login", "pasha@gmail.com");
        mockHttpServletRequest.addParameter("password", "123");
        mockHttpServletRequest.addParameter("name", null);

        assertFalse(validator.validate(mockHttpServletRequest));
    }

    @Test
    public void testValidatorNullPassword() {
        mockHttpServletRequest.addParameter("login", "pasha@gmail.com");
        mockHttpServletRequest.addParameter("password", null);
        mockHttpServletRequest.addParameter("name", "Паша");

        assertFalse(validator.validate(mockHttpServletRequest));
    }

    @Test
    public void testValidatorNullAllParameters() {
        mockHttpServletRequest.addParameter("login", null);
        mockHttpServletRequest.addParameter("password", null);
        mockHttpServletRequest.addParameter("name", null);

        assertFalse(validator.validate(mockHttpServletRequest));
    }
}
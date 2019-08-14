package by.epam.javatraining.restaurant.model.validator;

import org.apache.struts.mock.MockHttpServletRequest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class AddDishValidatorTest {
    private MockHttpServletRequest mockHttpServletRequest;
    private Validator validator;

    @BeforeMethod
    public void setValue() {
        mockHttpServletRequest = new MockHttpServletRequest();
        validator = ValidatorFactory.getValidator().getAddDishValidator();
    }

    @Test
    public void testValidator() {
        mockHttpServletRequest.addParameter("name", "Печёный картофель");
        mockHttpServletRequest.addParameter("image-url", "http:/image.com/potato.png");
        mockHttpServletRequest.addParameter("description", "Приготовлен на углях");

        assertTrue(validator.validate(mockHttpServletRequest));
    }

    @Test
    public void testValidatorExistName() {
        mockHttpServletRequest.addParameter("name", "Окрошка");
        mockHttpServletRequest.addParameter("image-url", "http:/image.com/potato.png");
        mockHttpServletRequest.addParameter("description", "Приготовлен на углях");

        assertFalse(validator.validate(mockHttpServletRequest));
    }

    @Test
    public void testValidatorNullName() {
        mockHttpServletRequest.addParameter("name", null);
        mockHttpServletRequest.addParameter("image-url", "http:/image.com/potato.png");
        mockHttpServletRequest.addParameter("description", "Приготовлен на углях");

        assertFalse(validator.validate(mockHttpServletRequest));
    }

    @Test
    public void testValidatorNullUrl() {
        mockHttpServletRequest.addParameter("name", "Печёный картофель");
        mockHttpServletRequest.addParameter("image-url", null);
        mockHttpServletRequest.addParameter("description", "Приготовлен на углях");

        assertFalse(validator.validate(mockHttpServletRequest));
    }

    @Test
    public void testValidatorNullDescription() {
        mockHttpServletRequest.addParameter("name", "Печёный картофель");
        mockHttpServletRequest.addParameter("image-url", "http:/image.com/potato.png");
        mockHttpServletRequest.addParameter("description", null);

        assertFalse(validator.validate(mockHttpServletRequest));
    }

    @Test
    public void testValidatorNullAllParameters() {
        mockHttpServletRequest.addParameter("name", null);
        mockHttpServletRequest.addParameter("image-url", null);
        mockHttpServletRequest.addParameter("description", null);

        assertFalse(validator.validate(mockHttpServletRequest));
    }
}
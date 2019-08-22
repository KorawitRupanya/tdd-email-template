package th.ku.emailtemplate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TemplateTest {

    private Template template;

    @BeforeEach
    void setup() {
        template = new Template("${one}, ${two}, ${three}");
        template.set("one", "1");
        template.set("two", "2");
        template.set("three", "3");
    }


    @Test
    void testEvaluateOneVariable() {
        Template template = new Template("Hello, ${name}");
        template.set("name", "Reader");
        assertEquals("Hello, Reader", template.evaluate());
    }

    @Test
    void testEvaluateDifferentTemplate() {
        Template template = new Template("Hi, ${name}");
        template.set("name", "someone else");
        assertEquals("Hi, someone else", template.evaluate());
    }

    @Test
    void testEvalueMultipleVariables() {
        Template template = new Template("${greeting}, ${name}");
        template.set("greeting", "Hi");
        template.set("name", "Reader");
        assertEquals("Hi, Reader", template.evaluate());
    }

    @Test
    void testEvaluateUnknownVariablesAreIgnored() {
        Template template = new Template("Hello, ${name}");
        template.set("doesnotexist", "Hi");
        template.set("name", "Reader");
        assertEquals("Hello, Reader", template.evaluate());
    }

    @Test
    public void testEvaluateMissingValueRaisesException() {
        assertThrows(MissingValueException.class,

                () -> { new Template("${foo}").evaluate(); });

    }
}
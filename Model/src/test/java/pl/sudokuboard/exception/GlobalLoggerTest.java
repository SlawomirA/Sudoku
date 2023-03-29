package pl.sudokuboard.exception;

import ch.qos.cal10n.IMessageConveyor;
import ch.qos.cal10n.MessageConveyor;
import org.junit.jupiter.api.Test;
import pl.sudokuboard.logger.GlobalLogger;
import pl.sudokuboard.logger.I10N;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalLoggerTest {

    @Test
    void globalLoggerTest()
    {

    }

    @Test
    void changeLanguage() {
        Locale locPL = new Locale("pl", "PL");
        Locale locUS = new Locale("en", "US");
        IMessageConveyor mc = new MessageConveyor(locPL);

        GlobalLogger.changeLanguage(locPL);
        String message = GlobalLogger.message(I10N.CLONE_NOT_SUPPORTED, "Test");
        String message2 = mc.getMessage(I10N.CLONE_NOT_SUPPORTED, "Test");
        assertEquals(message2, message);

        GlobalLogger.changeLanguage(locUS);
        mc = new MessageConveyor(locUS);
        message = GlobalLogger.message(I10N.CLONE_NOT_SUPPORTED, "Test");
        message2 = mc.getMessage(I10N.CLONE_NOT_SUPPORTED, "Test");
        assertEquals(message2, message);
    }

    @Test
    void debug() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        GlobalLogger.debug(I10N.CLONE_NOT_SUPPORTED, "Test");
        String message = GlobalLogger.message(I10N.CLONE_NOT_SUPPORTED, "Test");

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String expected = formatter.format(date) + " DEBUG " + message +"\r\n";

        assertEquals(expected, outContent.toString());

        System.setOut(originalOut);
    }

    @Test
    void info() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        GlobalLogger.info(I10N.CLONE_NOT_SUPPORTED, "Test");
        String message = GlobalLogger.message(I10N.CLONE_NOT_SUPPORTED, "Test");

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String expected = formatter.format(date) + " INFO " + message +"\r\n";

        assertEquals(expected, outContent.toString());

        System.setOut(originalOut);
    }

    @Test
    void warn() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        GlobalLogger.warn(I10N.CLONE_NOT_SUPPORTED, "Test");
        String message = GlobalLogger.message(I10N.CLONE_NOT_SUPPORTED, "Test");

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String expected = formatter.format(date) + " WARN " + message +"\r\n";

        assertEquals(expected, outContent.toString());

        System.setOut(originalOut);
    }

    @Test
    void message() {
    }
}
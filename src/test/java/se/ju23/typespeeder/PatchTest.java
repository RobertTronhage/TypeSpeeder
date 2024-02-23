/**
 * Test class for the Patch class methods.
 * @Author: Oskar Ray-Frayssinet
 */

package se.ju23.typespeeder;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.*;

public class PatchTest {

    @Test
    public void testPatchClassExists() {
        try {
            Class.forName("se.ju23.typespeeder.entity.Patch");
        } catch (ClassNotFoundException e) {
            throw new AssertionError("Patch class should exist.", e);
        }
    }
//Corrected spelling for releaseDateTime.
    @Test
    public void testPatchProperties() {
        try {
            Class<?> someClass = Class.forName("se.ju23.typespeeder.entity.Patch");

            Field patchVersion = someClass.getDeclaredField("patchVersion");
            assertNotNull(patchVersion, "Field 'patchVersion' should exist in the Patch class.");
            assertTrue(patchVersion.getType().equals(String.class), "Field 'patchVersion' should be of type String.");

            Field releaseDateTime = someClass.getDeclaredField("releaseDateTime");
            assertNotNull(releaseDateTime, "Field 'releaseDateTime' should exist in Patch class.");

            patchVersion.setAccessible(true);
            releaseDateTime.setAccessible(true);

            assertTrue(releaseDateTime.getType().equals(LocalDateTime.class), "Field 'releaseDateTime' should be of type LocalDateTime.");

            Object instance = someClass.getDeclaredConstructor().newInstance();
            LocalDateTime dateTimeValue = (LocalDateTime) releaseDateTime.get(instance);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dateTimeValue.format(formatter);

            //Added currentDateTime to get actual DateTime
            String currentDateTime = LocalDateTime.now().format(formatter);
            assertEquals(currentDateTime, formattedDateTime, "'realeaseDateTime' field should have format 'yyyy-MM-dd HH:mm:ss'.");

            Method getterMethod = someClass.getDeclaredMethod("getReleaseDateTime");
            assertNotNull(getterMethod, "Getter method for field 'releaseDateTime' should exist.");

        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException e) {
            fail("Error occurred while testing properties of Patch.", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

package io.github.moudjames23.osmsconnect.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PhoneNumberUtilTest {

    @Test
    void itShouldValidPhoneNumber() {
        // Given
        String phoneNumber = "+224620000000";

        // When
        // Then
        boolean excepted = PhoneNumberUtil.isValid(phoneNumber);

        assertTrue(excepted);
    }

    @Test
    void itShouldNotValidPhoneNumber() {
        // Given
        String phoneNumber = "224620000";

        // When
        // Then
        boolean excepted = PhoneNumberUtil.isValid(phoneNumber);

        assertFalse(excepted);
    }

    @Test
    void itShouldNormalizePhoneNumber() {
        // Given
        String phoneNumber = "224620000000";

        // When
        String excepted = PhoneNumberUtil.normalize(phoneNumber);

        // Then
        assertNotEquals(excepted, phoneNumber);
        assertTrue(excepted.startsWith("+"));
    }
}

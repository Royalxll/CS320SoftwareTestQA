package contactTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import contact.Contact;

public class ContactTest {

    private static final String ID = "1001";
    private static final String FIRST_NAME = "Kaseem";
    private static final String LAST_NAME = "Wright";
    private static final String PHONE = "1234567890";
    private static final String ADDRESS = "107 Keer Ave";

    @Test
    void testConstructor_withValidValues_createsContact() {
        Contact contact = createContact();

        assertAll(
                () -> assertEquals(ID, contact.getContactId()),
                () -> assertEquals(FIRST_NAME, contact.getFirstName()),
                () -> assertEquals(LAST_NAME, contact.getLastName()),
                () -> assertEquals(PHONE, contact.getPhone()),
                () -> assertEquals(ADDRESS, contact.getAddress()));
    }

    @Test
    void testConstructor_withNullContactId_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(
                        null,
                        FIRST_NAME,
                        LAST_NAME,
                        PHONE,
                        ADDRESS));
    }

    @Test
    void testConstructor_withLongContactId_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(
                        "12345678901",
                        FIRST_NAME,
                        LAST_NAME,
                        PHONE,
                        ADDRESS));
    }

    @Test
    void testSetFirstName_withNullValue_throwsException() {
        Contact contact = createContact();

        assertThrows(IllegalArgumentException.class,
                () -> contact.setFirstName(null));
    }

    @Test
    void testSetFirstName_withLongValue_throwsException() {
        Contact contact = createContact();

        assertThrows(IllegalArgumentException.class,
                () -> contact.setFirstName("Christopher"));
    }

    @Test
    void testSetLastName_withNullValue_throwsException() {
        Contact contact = createContact();

        assertThrows(IllegalArgumentException.class,
                () -> contact.setLastName(null));
    }

    @Test
    void testSetLastName_withLongValue_throwsException() {
        Contact contact = createContact();

        assertThrows(IllegalArgumentException.class,
                () -> contact.setLastName("Washington"));
    }

    @Test
    void testSetPhone_withIncorrectLength_throwsException() {
        Contact contact = createContact();

        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> contact.setPhone("123456789")),

                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> contact.setPhone("12345678901")));
    }

    @Test
    void testSetPhone_withNonDigits_throwsException() {
        Contact contact = createContact();

        assertThrows(IllegalArgumentException.class,
                () -> contact.setPhone("12345abcde"));
    }

    @Test
    void testSetAddress_withNullValue_throwsException() {
        Contact contact = createContact();

        assertThrows(IllegalArgumentException.class,
                () -> contact.setAddress(null));
    }

    @Test
    void testSetAddress_withLongValue_throwsException() {
        Contact contact = createContact();

        assertThrows(IllegalArgumentException.class, () ->
                contact.setAddress(
                        "1234567890123456789012345678901"));
    }

    @Test
    void testEquals_withMatchingContacts_returnsTrue() {
        Contact firstContact = createContact();
        Contact secondContact = createContact();

        assertEquals(firstContact, secondContact);
    }

    private Contact createContact() {
        return new Contact(
                ID,
                FIRST_NAME,
                LAST_NAME,
                PHONE,
                ADDRESS);
    }
}
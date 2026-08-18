package contactTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import contact.Contact;
import contact.ContactService;

public class ContactServiceTest {

    private static final String ID = "1001";
    private static final String FIRST_NAME = "Kaseem";
    private static final String LAST_NAME = "Wright";
    private static final String PHONE = "1234567890";
    private static final String ADDRESS = "107 Keer Ave";

    private ContactService service;
    private Contact contact;

    @BeforeEach
    void setUp() {
        service = new ContactService();

        contact = new Contact(
                ID,
                FIRST_NAME,
                LAST_NAME,
                PHONE,
                ADDRESS);
    }

    @Test
    void testAddContact_withUniqueId_storesContact() {
        service.addContact(contact);

        Contact storedContact = service.getContact(ID);

        assertEquals(contact, storedContact);
    }

    @Test
    void testAddContact_withDuplicateId_throwsException() {
        service.addContact(contact);

        Contact duplicate = new Contact(
                ID,
                "James",
                "Brown",
                "9876543210",
                "456 Oak Street");

        assertThrows(IllegalArgumentException.class,
                () -> service.addContact(duplicate));
    }

    @Test
    void testDeleteContact_withExistingId_removesContact() {
        service.addContact(contact);

        service.deleteContact(ID);

        assertNull(service.getContact(ID));
    }

    @Test
    void testUpdateContact_withValidValues_updatesContact() {
        service.addContact(contact);

        Contact expected = new Contact(
                ID,
                "Mike",
                "Jones",
                "9876543210",
                "456 Oak Street");

        service.updateContact(
                ID,
                "Mike",
                "Jones",
                "9876543210",
                "456 Oak Street");

        Contact updatedContact = service.getContact(ID);

        assertEquals(expected, updatedContact);
    }

    @Test
    void testUpdateContact_withMissingId_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                service.updateContact(
                        "9999",
                        "Mike",
                        "Jones",
                        "9876543210",
                        "456 Oak Street"));
    }

    @Test
    void testGetContact_withExistingId_returnsNewInstance() {
        service.addContact(contact);

        Contact firstResult = service.getContact(ID);
        Contact secondResult = service.getContact(ID);

        assertAll(
                () -> assertEquals(firstResult, secondResult),
                () -> assertNotSame(firstResult, secondResult),
                () -> assertNotSame(contact, firstResult));
    }
}
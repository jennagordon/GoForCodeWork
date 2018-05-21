package com.sg.dao;

import com.sg.model.Author;
import com.sg.model.Book;
import com.sg.model.Publisher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LibraryDaoTest {
    LibraryDao dao;

    @Before
    public void setUp() throws Exception {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("libraryDao", LibraryDao.class);

        List<Book> books = dao.retrieveAllBooks();
        for(Book currentBook : books) {
            dao.deleteBook(currentBook.getBookId());
        }

        List<Author> authors = dao.retrieveAllAuthors();
        for(Author currentAuthor : authors) {
            dao.deleteAuthor(currentAuthor.getAuthorId());
        }

        List<Publisher> publishers = dao.retrieveAllPublishers();
        for(Publisher currentPublisher : publishers) {
            dao.deletePublisher(currentPublisher.getPublisherId());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addAuthor() {
        Author author = new Author();
        author.setFirstName("Author");
        author.setLastName("Test");
        author.setStreet("49 Oak Street");
        author.setCity("Authortown");
        author.setState("OH");
        author.setZip("44398");
        author.setPhone("555-1234");

        dao.addAuthor(author);
        assertEquals(author, dao.retrieveAuthorById(author.getAuthorId()));
    }

    @Test
    public void deleteAuthor() {
        Author author = new Author();
        author.setFirstName("Author");
        author.setLastName("Test");
        author.setStreet("49 Oak Street");
        author.setCity("Authortown");
        author.setState("OH");
        author.setZip("44398");
        author.setPhone("555-1234");

        dao.addAuthor(author);
        assertEquals(author, dao.retrieveAuthorById(author.getAuthorId()));
        dao.deleteAuthor(author.getAuthorId());
        assertNull(dao.retrieveAuthorById(author.getAuthorId()));
    }

    @Test
    public void updateAuthor() {
    }

    @Test
    public void retrieveAuthorById() {
    }

    @Test
    public void retrieveAllAuthors() {
    }

    @Test
    public void addBook() {
        Publisher publisher = new Publisher();
        publisher.setName("Pub One");
        publisher.setStreet("123 Main Street");
        publisher.setCity("Publisher City");
        publisher.setState("OH");
        publisher.setZip("44123");
        publisher.setPhone("555-1212");

        dao.addPublisher(publisher);

        Author author = new Author();
        author.setFirstName("Author");
        author.setLastName("One");
        author.setStreet("45 Elm Street");
        author.setCity("Author City");
        author.setState("CA");
        author.setZip("90210");
        author.setPhone("555-1212");

        dao.addAuthor(author);

        Book b = new Book();
        b.setTitle("Great Book");
        b.setIsbn("12345");
        b.setPrice(new BigDecimal("12.95"));
        b.setPublishDate(LocalDate.parse("2010-01-01", DateTimeFormatter.ISO_DATE));
        b.setPublisher(publisher);

        List<Author> authorList = new ArrayList<>();
        authorList.add(author);
        b.setAuthors(authorList);

        dao.addBook(b);

        assertEquals(b, dao.retrieveBookById(b.getBookId()));
    }

    @Test
    public void deleteBook() {

        Publisher publisher = new Publisher();
        publisher.setName("Pub One");
        publisher.setStreet("123 Main Street");
        publisher.setCity("Publisher City");
        publisher.setState("OH");
        publisher.setZip("44123");
        publisher.setPhone("555-1212");

        dao.addPublisher(publisher);

        Author author = new Author();
        author.setFirstName("Author");
        author.setLastName("One");
        author.setStreet("45 Elm Street");
        author.setCity("Author City");
        author.setState("CA");
        author.setZip("90210");
        author.setPhone("555-1212");

        dao.addAuthor(author);

        Book b = new Book();
        b.setTitle("Great Book");
        b.setIsbn("12345");
        b.setPrice(new BigDecimal("12.95"));
        b.setPublishDate(LocalDate.parse("2010-01-01", DateTimeFormatter.ISO_DATE));
        b.setPublisher(publisher);

        List<Author> authorList = new ArrayList<>();
        authorList.add(author);
        b.setAuthors(authorList);

        dao.addBook(b);

        assertEquals(b, dao.retrieveBookById(b.getBookId()));
        dao.deleteBook(b.getBookId());
        assertNull(dao.retrieveBookById(b.getBookId()));
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void retrieveBookById() {
    }

    @Test
    public void retrieveBooksByAuthorId() {
    }

    @Test
    public void retrieveBooksByPublisherId() {
    }

    @Test
    public void retrieveAllBooks() {
    }

    @Test
    public void addPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Pub One");
        publisher.setStreet("123 Main Street");
        publisher.setCity("Publisher City");
        publisher.setState("OH");
        publisher.setZip("44123");
        publisher.setPhone("555-1212");

        dao.addPublisher(publisher);

        assertEquals(publisher, dao.retrievePublisherById(publisher.getPublisherId()));
    }

    @Test
    public void deletePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Pub One");
        publisher.setStreet("123 Main Street");
        publisher.setCity("Publisher City");
        publisher.setState("OH");
        publisher.setZip("44123");
        publisher.setPhone("555-1212");

        dao.addPublisher(publisher);
        assertEquals(publisher, dao.retrievePublisherById(publisher.getPublisherId()));
        dao.deletePublisher(publisher.getPublisherId());
        assertNull(dao.retrievePublisherById(publisher.getPublisherId()));
    }

    @Test
    public void updatePublisher() {
    }

    @Test
    public void retrievePublisherById() {
    }

    @Test
    public void retrieveAllPublishers() {
    }
}
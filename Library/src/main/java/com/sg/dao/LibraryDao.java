package com.sg.dao;

import com.sg.model.Author;
import com.sg.model.Book;
import com.sg.model.Publisher;

import java.util.List;

public interface LibraryDao {

    public void addAuthor(Author author);

    public void deleteAuthor(int authorId);

    public void updateAuthor(Author author);

    public Author retrieveAuthorById(int authorId);

    public List<Author> retrieveAllAuthors();

    public void addBook(Book book);

    public void deleteBook(int bookId);

    public void updateBook(Book book);

    public Book retrieveBookById(int bookId);

    public List<Book> retrieveBooksByAuthorId(int authorId);

    public List<Book> retrieveBooksByPublisherId(int publisherId);

    public List<Book> retrieveAllBooks();

    public void addPublisher(Publisher publisher);

    public void deletePublisher(int publisherId);

    public void updatePublisher(Publisher publisher);

    public Publisher retrievePublisherById(int id);

    public List<Publisher> retrieveAllPublishers();
}

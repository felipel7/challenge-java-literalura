package com.alura.literalura.app;


import com.alura.literalura.dto.ApiResponseDto;
import com.alura.literalura.dto.AuthorDto;
import com.alura.literalura.dto.BookDto;
import com.alura.literalura.model.Author;
import com.alura.literalura.model.Book;
import com.alura.literalura.repository.AuthorRepository;
import com.alura.literalura.repository.BookRepository;
import com.alura.literalura.service.ApiClient;
import com.alura.literalura.service.ConvertData;

import java.util.Scanner;

public class BookApp {
    private final Scanner scanner = new Scanner(System.in);
    private final Menu menu = new Menu(scanner);
    private final ApiClient apiClient = new ApiClient();
    private final ConvertData convertData = new ConvertData();
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public BookApp(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void run() {
        menu.welcome();

        while (true) {
            menu.display();
            var option = menu.requestOption();
            if (option == 0) break;

            switch (option) {
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    listAllBooks();
                    break;
                case 3:
                    listAllAuthors();
                    break;
                case 4:
                    searchAuthorsByYear();
                    break;
                case 5:
                    searchBookByLanguage();
                    break;
                default:
                    menu.invalidInput();
                    break;
            }
        }
    }

    private void searchBookByTitle() {
        menu.requestBookName();

        var title = scanner.nextLine().replace(" ", "%20");
        var url = "https://gutendex.com/books" + "?search=" + title;
        var json = apiClient.getData(url);
        var data = convertData.parseJsonToObject(json, ApiResponseDto.class);

        if (data.results().isEmpty()) {
            menu.bookNotFound();
            return;
        }

        var bookDto = data.results().get(0);
        var authorDto = bookDto.authors().get(0);
        var book = createRelationshipAndSave(bookDto, authorDto);

        menu.printBook(book);
    }

    private void listAllBooks() {
        var books = bookRepository.findAll();
        books.forEach(menu::printBook);
    }

    private void listAllAuthors() {
        var authors = authorRepository.findAll();
        authors.forEach(menu::printAuthors);
    }

    private void searchAuthorsByYear() {
        var year = menu.requestYearInput();
        var authors = authorRepository.findAuthorsByYearOfExistence(year);
        authors.forEach(menu::printAuthors);
    }

    private void searchBookByLanguage() {
        var language = menu.requestLanguageInput();
        var books = bookRepository.findByLanguageIgnoreCase(language);
        if (books.isEmpty()) {
            menu.bookNotFound();
        } else {
            books.forEach(menu::printBook);
        }
    }

    private Book createRelationshipAndSave(BookDto bookDto, AuthorDto authorDto) {
        var existingAuthor = authorRepository.findByNameIgnoreCase(authorDto.name());
        Book book = new Book(bookDto);

        if (existingAuthor.isEmpty()) {
            Author author = new Author(authorDto);

            book.setAuthor(author);
            author.getBooks().add(book);

            authorRepository.save(author);
            bookRepository.save(book);
        } else {
            var author = existingAuthor.get();
            var bookExists = author.getBooks()
                    .stream()
                    .anyMatch(b -> b.getTitle().equalsIgnoreCase(book.getTitle()));

            if (!bookExists) {
                book.setAuthor(author);
                author.getBooks().add(book);
                bookRepository.save(book);
            }
        }

        return book;
    }
}

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
                    System.out.println(2);
                    break;
                case 3:
                    System.out.println(3);
                    break;
                case 4:
                    System.out.println(4);
                    break;
                case 5:
                    System.out.println(5);
                    break;
                default:
                    menu.invalidInput();
                    break;
            }
        }
    }

    private void searchBookByTitle() {
        System.out.println("Insira o nome do livro que deseja procurar: ");

        var title = scanner.nextLine().replace(" ", "%20");
        var url = "https://gutendex.com/books" + "?search=" + title;
        var json = apiClient.getData(url);
        var data = convertData.parseJsonToObject(json, ApiResponseDto.class);

        if(data.results().isEmpty()) {
            System.out.println("Nenhum livro encontrado");
            return;
        }

        var book = data.results().get(0);
        var author = book.authors().get(0);
        saveBookIfNotExist(book);
        saveAuthorIfNotExist(author);

        menu.printBook(book, author);
    }

    private void saveBookIfNotExist(BookDto bookDto) {
        var existingBook = bookRepository.findByTitleIgnoreCase(bookDto.title());
        if (existingBook.isEmpty()) {
            Book book = new Book(bookDto);
            bookRepository.save(book);
        }
    }

    private void saveAuthorIfNotExist(AuthorDto authorDto) {
        var existingAuthor = authorRepository.findByNameIgnoreCase(authorDto.name());
        if (existingAuthor.isEmpty()) {
            Author author = new Author(authorDto);
            authorRepository.save(author);
        }
    }
}

package com.alura.literalura.app;

import com.alura.literalura.model.Author;
import com.alura.literalura.model.Book;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void display() {
        System.out.println("""
                \n
                ----------------- 
                Escolha o número de sua opção:
                                
                1- Buscar livro pelo título
                2- Listar livros registrados
                3- Listar autores registrados
                4- Listar autores vivos em um determinado ano
                5- Listar livros em um determinado idioma
                                
                0- sair
                """);
    }

    public void welcome() {
        System.out.println("""
                \n \n \n
                Seja bem-vindo/a ao Literalura! :)
                """);
    }

    public Integer requestOption() {
        while (true) {
            try {
                var option = scanner.nextInt();
                scanner.nextLine();
                return option;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                invalidInput();
            }
        }
    }

    public void invalidInput() {
        System.out.println("Por favor, insira uma opção válida");
    }

    public void printBook(Book book) {
        System.out.printf("""
                        \n
                        ----- LIVRO -----
                         Título: %s
                         Autor: %s
                         Idioma: %s
                         Número de downloads: %d
                        -----------------
                        """,
                book.getTitle(),
                book.getAuthor().getName(),
                book.getLanguage(),
                book.getDownloadCount());
    }

    public void printAuthors(Author author) {
        List<Book> books = author.getBooks();
        List<String> bookTitles = new ArrayList<>();
        for (Book book : books)
            bookTitles.add(book.getTitle());


        System.out.printf("""
                        \n
                        ----- AUTOR -----
                         Nome: %s
                         Ano de nascimento: %s
                         Ano de falecimento: %s
                         Livros: %s
                        -----------------
                        """,
                author.getName(),
                author.getBirthYear(),
                author.getDeathYear(),
                bookTitles);
    }

    public void bookNotFound() {
        System.out.println("Nenhum livro encontrado");
    }


    public void requestBookName() {
        System.out.println("Insira o nome do livro que deseja procurar: ");
    }

    public Integer requestYearInput() {
        System.out.println("Insira o ano que deseja pesquisar: ");
        var year = scanner.nextInt();
        scanner.nextLine();

        return year;
    }
}

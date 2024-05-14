package com.alura.literalura.app;

import com.alura.literalura.dto.AuthorDto;
import com.alura.literalura.dto.BookDto;

import java.util.InputMismatchException;
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

    public void printBook(BookDto book, AuthorDto author) {
              System.out.printf("""
                \n
                ----- LIVRO -----
                 Título: %s
                 Autor: %s
                 Idioma: %s
                 Número de downloads: %d
                -----------------
                """,
                      book.title(),
                      author.name(),
                      book.languages().get(0),
                      book.download_count());
    }
}

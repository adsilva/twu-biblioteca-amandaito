package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.repository.BookRepositoryImpl;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.BibliotecaService;

import java.time.LocalDate;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
        BookRepositoryImpl bookRepository = new BookRepositoryImpl();
        BookService bookService = new BookService(bookRepository);
        BibliotecaService bibliotecaService = new BibliotecaService(bookService);

        List<Book> listOfBooks = bibliotecaService.getListOfBooks();
        Book bookTDD = new Book("Nora Roberts", "TDD", LocalDate.of(2019, 12, 27), false);
        Book bookDev = new Book("Jorge Amado", "Desenvolvimento", LocalDate.of(2018, 02, 17), false);
        listOfBooks.add(bookDev);
        listOfBooks.add(bookTDD);

        System.out.println(bibliotecaService.callWelcomeMessage());

        System.out.println(bibliotecaService.listMenuOptions());

        int option = 0;
        while (option != 4) {
            option = bibliotecaService.chooseMenuOption();
            if (option == 1) {
                bibliotecaService.listBooksWithColumns(listOfBooks).forEach(book -> System.out.println(book));
            } else if (option == 2) {
                bibliotecaService.interactionToCheckoutBook();
            } else if (option == 3) {
                bibliotecaService.interactionToReturnBook();
            } else {
                System.out.println("Please select valid option");
            }
        }
    }
}

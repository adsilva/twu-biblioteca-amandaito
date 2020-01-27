package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Book;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BibliotecaService {

    private final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore";
    private final String MENU_OPTIONS = "Menu \n1 - List of books\n2 - Checkout book \n3 - Return book \n4 - Quit";
    private final String CHOOSE_OPTION = "Please choose an option: ";
    private final String CHOOSE_BOOK = "Entry with book number: ";
    private final String SUCCESS_CHECKOUT = "Thank you! Enjoy the book";
    private final String UN_SUCCESSFUL_CHECKOUT = "Sorry, that book is not available";
    private final String SUCCESSFUL_RETURN = "Thank you for returning the book";
    private final String UN_SUCCESSFUL_RETURN = "That is not a valid book to return";
    private BookService bookService;

    public BibliotecaService(BookService bookService) {
        this.bookService = bookService;
    }

    public String callWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    public List<String> listBooksWithColumns(List<Book> listOfBooks) {
        return listOfBooks.stream().filter(book -> !book.isCheckout()).map(Book::toString).collect(Collectors.toList());
    }

    public String listMenuOptions() {
        return MENU_OPTIONS;
    }

    public int chooseMenuOption() {
        Scanner read = new Scanner(System.in);
        int option;
        System.out.printf(CHOOSE_OPTION);
        option = read.nextInt();
        return option;
    }

    public long chooseBook() {
        Scanner read = new Scanner(System.in);
        int identifierBook;
        System.out.printf(CHOOSE_BOOK);
        identifierBook = read.nextInt();
        return identifierBook;
    }

    public void interactionToCheckoutBook(){
        long bookNumber = this.chooseBook();
        Book bookFound = this.findBookWithIdentifier(bookNumber);
        if (bookFound != null && !bookFound.isCheckout()) {
            this.checkoutBook(bookFound);
            System.out.println(this.getSUCCESS_CHECKOUT());
        } else {
            System.out.println(this.getUN_SUCCESSFUL_CHECKOUT());
        }
    }

    public void interactionToReturnBook(){
        long bookNumber = this.chooseBook();
        Book bookFound = this.findBookWithIdentifier(bookNumber);
        if (bookFound != null && bookFound.isCheckout()) {
            this.returnBook(bookFound);
            System.out.println(this.getSUCCESSFUL_RETURN());
        }else {
            System.out.println(this.getUN_SUCCESSFUL_RETURN());
        }
    }

    public void checkoutBook(Book book) {
        this.bookService.checkoutBook(book);
    }

    public void returnBook(Book book) {
        this.bookService.returnBook(book);
    }

    public Book findBookWithIdentifier(Long numberOfBook) {
        return this.bookService.findBookWithIdentifier(numberOfBook);
    }

    public List<Book> getListOfBooks() {
        return this.bookService.getListOfBooks();
    }

    public String getSUCCESS_CHECKOUT() {
        return this.SUCCESS_CHECKOUT;
    }

    public String getUN_SUCCESSFUL_CHECKOUT() {
        return this.UN_SUCCESSFUL_CHECKOUT;
    }

    public String getSUCCESSFUL_RETURN() {
        return this.SUCCESSFUL_RETURN;
    }

    public String getUN_SUCCESSFUL_RETURN(){
        return this.UN_SUCCESSFUL_RETURN;
    }
}

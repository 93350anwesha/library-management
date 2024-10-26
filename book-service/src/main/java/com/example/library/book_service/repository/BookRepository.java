package com.example.library.book_service.repository;

import com.example.library.book_service.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();
    Book findById(String id);
}


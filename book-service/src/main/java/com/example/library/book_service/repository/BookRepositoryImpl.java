package com.example.library.book_service.repository;

import com.example.library.book_service.model.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private List<Book> books;

    public BookRepositoryImpl() {
        this.books = loadBooks();
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public Book findById(String id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
    }

    private List<Book> loadBooks() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getResourceAsStream("/books.json")) {
            return objectMapper.readValue(inputStream, new TypeReference<List<Book>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return List.of(); // Return an empty list in case of error
        }
    }
}

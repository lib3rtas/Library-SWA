package ch.fhnw.swa.library.book;

import lombok.Value;

import java.util.UUID;

@Value
public class Book {
    UUID    id;
    String  title;
}

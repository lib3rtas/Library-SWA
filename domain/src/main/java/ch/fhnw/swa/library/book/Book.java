package ch.fhnw.swa.library.book;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

// Can't just use @Value, because thymeleaf requires an non-immutable object
@Getter
@Setter
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Book {
    UUID    id;
    String  title;
    String  author;
    String  description;

    // Cstor for thymeleaf
    public Book(){
        this.id = UUID.randomUUID();
    }
}

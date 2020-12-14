package ch.fhnw.swa.library.book;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Random;

// Can't just use @Value, because thymeleaf requires an non-immutable object
@Getter
@Setter
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Book {
    @Id
    Long id;
    String  title;
    String  author;
    String  description;

    // Cstor for thymeleaf
    public Book(){
        this.id = new Random().nextLong();
    }
}

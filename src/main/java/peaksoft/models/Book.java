package peaksoft.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
Long id;
String name;
int publishedYear;
int price;
Long authorId;

    public Book(String name, int publishedYear, int price, Long authorId) {
        this.name = name;
        this.publishedYear = publishedYear;
        this.price = price;
        this.authorId = authorId;
    }
}

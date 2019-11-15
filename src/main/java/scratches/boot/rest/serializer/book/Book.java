package scratches.boot.rest.serializer.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @author Rashidi Zin
 */
@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class Book {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Transient
    private String author;

    @JsonIgnore
    private Long authorId;

    @NonNull
    private String title;

    @NonNull
    private String isbn;

}

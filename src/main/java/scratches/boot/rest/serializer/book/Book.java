package scratches.boot.rest.serializer.book;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Rashidi Zin
 */
@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private Long authorId;

    @NonNull
    private String title;

    @NonNull
    private String isbn;

}

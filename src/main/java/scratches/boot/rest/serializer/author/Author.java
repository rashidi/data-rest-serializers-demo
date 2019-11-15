package scratches.boot.rest.serializer.author;

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
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

}

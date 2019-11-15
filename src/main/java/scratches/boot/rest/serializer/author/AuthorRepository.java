package scratches.boot.rest.serializer.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Rashidi Zin
 */
@RepositoryRestResource(exported = false)
public interface AuthorRepository extends JpaRepository<Author, Long> {
}

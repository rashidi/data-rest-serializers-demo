package scratches.boot.rest.serializer.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import scratches.boot.rest.serializer.author.AuthorRepository;
import scratches.boot.rest.serializer.book.BookCustomModule;

/**
 * @author Rashidi Zin
 */
@Configuration
@AllArgsConstructor
public class CustomRepositoryRestConfiguration implements RepositoryRestConfigurer {

    private final AuthorRepository authorRepository;

    @Override
    public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
        objectMapper.registerModule(new BookCustomModule(authorRepository));
    }
}

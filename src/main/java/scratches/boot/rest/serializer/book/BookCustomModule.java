package scratches.boot.rest.serializer.book;

import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.AllArgsConstructor;
import scratches.boot.rest.serializer.author.AuthorRepository;

/**
 * @author Rashidi Zin
 */
@AllArgsConstructor
public class BookCustomModule extends SimpleModule {

    private final AuthorRepository authorRepository;

    @Override
    public void setupModule(SetupContext context) {
        SimpleDeserializers deserializers = new SimpleDeserializers();

        deserializers.addDeserializer(Book.class, new BookJsonDeserializer(authorRepository));

        context.addDeserializers(deserializers);
    }
}

package scratches.boot.rest.serializer.book;

import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
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
        SimpleSerializers serializers = new SimpleSerializers();
        SimpleDeserializers deserializers = new SimpleDeserializers();

        serializers.addSerializer(Book.class, new BookJsonSerializer(authorRepository));
        deserializers.addDeserializer(Book.class, new BookJsonDeserializer(authorRepository));

        context.addDeserializers(deserializers);
    }
}

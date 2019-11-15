package scratches.boot.rest.serializer.book;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.AllArgsConstructor;
import scratches.boot.rest.serializer.author.AuthorRepository;

import java.io.IOException;

/**
 * @author Rashidi Zin
 */
@AllArgsConstructor
public class BookJsonSerializer extends JsonSerializer<Book> {

    private final AuthorRepository authorRepository;

    @Override
    public void serialize(Book book, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        var author = authorRepository.getOne(book.getAuthorId());

        jsonGenerator.writeStartObject();

        jsonGenerator.writeStringField("author", author.getName());
        jsonGenerator.writeStringField("title", book.getTitle());
        jsonGenerator.writeStringField("isbn", book.getIsbn());

        jsonGenerator.writeEndObject();
    }
}

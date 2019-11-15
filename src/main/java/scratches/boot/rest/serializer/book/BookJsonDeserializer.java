package scratches.boot.rest.serializer.book;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import lombok.AllArgsConstructor;
import scratches.boot.rest.serializer.author.Author;
import scratches.boot.rest.serializer.author.AuthorRepository;

import java.io.IOException;

/**
 * @author Rashidi Zin
 */
@AllArgsConstructor
public class BookJsonDeserializer extends JsonDeserializer<Book> {

    private final AuthorRepository authorRepository;

    @Override
    public Book deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        var node = jsonParser.getCodec().readTree(jsonParser);
        var author = (TextNode) node.get("author");

        var persistedAuthor = authorRepository
                .findByNameIgnoreCase(author.asText())
                .orElseGet(() -> authorRepository.save(new Author(author.asText())));

        var title = ((TextNode) node.get("title")).asText();
        var isbn = ((TextNode) node.get("isbn")).asText();

        var book = new Book(persistedAuthor.getName(), title, isbn);

        book.setAuthorId(persistedAuthor.getId());

        return book;
    }
}

package scratches.boot.rest.serializer.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import scratches.boot.rest.serializer.author.Author;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Rashidi Zin
 */
@Transactional
@AutoConfigureTestEntityManager
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@SpringBootTest(webEnvironment = DEFINED_PORT)
class BookRestRepositoryTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TestEntityManager em;

    @Autowired
    private BookRepository repository;

    @Test
    void serializeRequest() throws Exception {
        var book = new Book("Rudyard Kipling", "The Jungle Book", "A1234567D");

        mvc.perform(
                post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(book))
        )
                .andExpect(status().isCreated());

        assertThat(repository.count()).isEqualTo(1);
    }

    @Test
    void deserializeEntity() throws Exception {
        var authorId = em.persistAndGetId(new Author("Rudyard Kipling"), Long.class);
        var book = new Book("Rudyard Kipling", "The Jungle Book", "A1234567D");

        book.setAuthorId(authorId);

        var id = em.persistAndGetId(book, Long.class);

        mvc.perform(
                get("/books/{id}", id)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author", is("Rudyard Kipling")));
    }
}
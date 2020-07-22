package io.zeddw.caching.simple;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author xingguan.wzt
 * @date 2020/07/22
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimpleCacheTestConfig.class)
class BookServiceTest {

    @Autowired
    private BookService bookService;

    private Author author;

    @BeforeEach
    void setUp() {
        author = mock(Author.class);
        when(author.getName()).thenReturn("Bob");
        when(author.getTelephone()).thenReturn("1");
        when(author.getAddress()).thenReturn("some place");
        when(author.toString()).thenCallRealMethod();
        doCallRealMethod().when(author).setName(any(String.class));
        doCallRealMethod().when(author).setTelephone(any(String.class));
        doCallRealMethod().when(author).setAddress(any(String.class));
    }

    @Test
    void testSimpleCache() {
        List<Book> firstCall = bookService.getByAuthor(author);
        List<Book> secondCall = bookService.getByAuthor(author);
        assertThat(firstCall).containsExactlyElementsOf(secondCall);
    }

    @Test
    void testCacheValueShouldBeSame() {
        Book book = bookService.publish(author, "new book");
        assertThat(book.getTitle()).isEqualTo("new book");
        // 缓存存取的类型需要保持一致
        assertThatThrownBy(() -> bookService.getByAuthor(author)).isInstanceOf(ClassCastException.class);
    }
}
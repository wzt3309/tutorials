package io.zeddw.caching.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author xingguan.wzt
 * @date 2020/07/22
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "book", keyGenerator = "ck_book_author")
public class SimpleBookServiceImpl implements BookService {

    /**
     * 缓存方法尽量是一个"单纯"的操作，而不是包含很多与取数据无关业务逻辑的"service"方法
     */
    @Cacheable(unless = "#result?.size() < 1")
    @Override
    public List<Book> getByAuthor(Author author) {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            books.add(genBook(author));
        }
        return books;
    }

    @CachePut(condition = "T(org.apache.commons.lang3.StringUtils).isNotBlank(#title) || #author != null")
    @Override
    public Book publish(Author author, String title) {
        return genBook(author, title);
    }

    private Book genBook(Author author) {
        return genBook(author, RandomStringUtils.randomAlphanumeric(5));
    }

    private Book genBook(Author author, String title) {
        simulateSlowProcessing();

        Book book = new Book();
        book.setAuthor(author);
        book.setIsbn(RandomStringUtils.randomNumeric(10));
        book.setTitle(title);
        log.info("Generate a new book: {}", book);
        return book;
    }

    private void simulateSlowProcessing() {
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    @CacheEvict
    @Override
    public void ceaseAll(Author author) {
        log.info("Cease publish author's all books");
    }
}

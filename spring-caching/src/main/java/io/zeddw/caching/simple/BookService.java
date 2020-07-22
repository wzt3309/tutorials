package io.zeddw.caching.simple;

import java.util.List;

/**
 * @author xingguan.wzt
 * @date 2020/07/22
 */
public interface BookService {

    /**
     * Get book by author
     *
     * @param author author
     * @return author's books
     */
    List<Book> getByAuthor(Author author);

    /**
     * Publish book for author
     *
     * @param author book's author
     * @param title  book's title
     * @return published book
     */
    Book publish(Author author, String title);

    /**
     * Cease publish all author's books
     *
     * @param author author
     */
    void ceaseAll(Author author);
}

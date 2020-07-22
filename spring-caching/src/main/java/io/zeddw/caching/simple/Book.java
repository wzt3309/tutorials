package io.zeddw.caching.simple;

import lombok.Data;

/**
 * @author xingguan.wzt
 * @date 2020/07/22
 */
@Data
public class Book {
    private String isbn;
    private String title;
    private Author author;
}

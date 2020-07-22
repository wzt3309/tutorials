package io.zeddw.caching.simple;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author xingguan.wzt
 * @date 2020/07/22
 */
@Component("ck_book_author")
public class AuthorKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        Assert.notEmpty(params, "author is null");
        Author author = (Author) params[0];
        return generateKey(author);
    }

    private String generateKey(Author author) {
        return StringUtils.joinWith("_", author.getTelephone(), author.getName());
    }
}

package io.zeddw.mockito;

import java.util.List;

import org.junit.Test;

import static org.mockito.Mockito.RETURNS_SELF;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Mockito基本使用方法
 *
 * @author xingguan.wzt
 * @date 2020/09/10
 */
public class CommonTest {

    @Test
    public void verifyBehaviour() {
        List<String> mockedList = mock(List.class);

        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void testReturnSelfAnswer() {
        HttpBuilder mock = mock(HttpBuilder.class, RETURNS_SELF);
        HttpBuilder mockReturn = mock.withUrl("test");
        HttpBuilder mockReturn2 = mockReturn.withHeader("header");
        when(mock.request()).thenReturn("mock request");

        assertEquals(mock, mockReturn);
        assertEquals(mock, mockReturn2);
        assertEquals(mockReturn, mockReturn2);
        assertEquals("mock request", mock.request());
        assertEquals("mock request", mockReturn.request());
        assertEquals("mock request", mockReturn2.request());

        HttpBuilder mock2 = mock(HttpBuilder.class);
        HttpBuilder mock2Return = mock.withHeader("test");
        assertNotEquals(mock2, mock2Return);
    }
}

class HttpBuilder {
    private String url;
    private String header;

    public HttpBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpBuilder withHeader(String header) {
        this.header = header;
        return this;
    }

    public String request() {
        return url + ": " + header;
    }
}

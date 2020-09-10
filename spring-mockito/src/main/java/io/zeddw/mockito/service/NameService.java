package io.zeddw.mockito.service;

/**
 * @author xingguan.wzt
 * @date 2020/09/08
 */
public interface NameService {

    /**
     * Get name by id
     * @param id id
     * @return name
     */
    String getName(Long id);
}

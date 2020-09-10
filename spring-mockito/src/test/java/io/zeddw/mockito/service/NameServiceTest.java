package io.zeddw.mockito.service;

import java.util.Optional;

import io.zeddw.mockito.repository.UserRepository;
import io.zeddw.mockito.service.impl.NameServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

/**
 * @author xingguan.wzt
 * @date 2020/09/10
 */
@RunWith(MockitoJUnitRunner.class)
public class NameServiceTest {

    @InjectMocks
    private NameServiceImpl nameService;

    @Mock(name = "userRepository2")
    private UserRepository userRepository;

    @Mock(name = "userRepository")
    private UserRepository userRepository2;

    @Before
    public void setUp() {
        when(userRepository.findById(1L)).thenReturn(Optional.of("test01"));
        when(userRepository2.findById(1L)).thenReturn(Optional.of("test02"));
    }

    @Test
    public void getName() {
        System.out.println(nameService.getName(1L));
    }
}
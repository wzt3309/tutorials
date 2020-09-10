package io.zeddw.mockito.service.impl;

import io.zeddw.mockito.repository.UserRepository;
import io.zeddw.mockito.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xingguan.wzt
 * @date 2020/09/08
 */
@Service
public class NameServiceImpl implements NameService {

    private UserRepository userRepository;

    @Override
    public String getName(Long id) {
        return userRepository.findById(id).orElse("");
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

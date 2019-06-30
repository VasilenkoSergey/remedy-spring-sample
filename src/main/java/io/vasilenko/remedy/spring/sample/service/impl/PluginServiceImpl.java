package io.vasilenko.remedy.spring.sample.service.impl;

import io.vasilenko.remedy.spring.sample.service.PluginService;
import org.springframework.stereotype.Service;

@Service
public class PluginServiceImpl implements PluginService {

    @Override
    public String greeting(String name) {
        return "Hello " + name;
    }
}

package io.vasilenko.remedy.spring.sample;

import com.bmc.arsys.api.Value;
import com.bmc.arsys.pluginsvr.plugins.ARFilterAPIPlugin;
import com.bmc.arsys.pluginsvr.plugins.ARPluginContext;
import com.bmc.thirdparty.org.slf4j.Logger;
import com.bmc.thirdparty.org.slf4j.LoggerFactory;
import io.vasilenko.remedy.spring.sample.service.PluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class SpringSamplePlugin extends ARFilterAPIPlugin {

    private static final int INPUT_NAME_VALUE_INDEX = 0;

    private final Logger log = LoggerFactory.getLogger(SpringSamplePlugin.class);

    private AnnotationConfigApplicationContext applicationContext;

    @Autowired
    private PluginService service;

    @Override
    public void initialize(ARPluginContext context) {
        applicationContext = new AnnotationConfigApplicationContext(SpringSamplePlugin.class.getPackage().getName());
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
        log.info("initialized");
    }

    @Override
    public List<Value> filterAPICall(ARPluginContext context, List<Value> inputValues) {
        String name = String.valueOf(inputValues.get(INPUT_NAME_VALUE_INDEX));
        log.info("name: {}", name);
        String greeting = service.greeting(name);
        log.info("greeting: {}", greeting);
        List<Value> outputList = new ArrayList<>();
        outputList.add(new Value(greeting));
        return outputList;
    }

    @Override
    public void terminate(ARPluginContext context) {
        applicationContext.close();
    }
}

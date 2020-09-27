package io.zeddw.ssm.config;

import io.zeddw.ssm.domain.SelectEvent;
import io.zeddw.ssm.domain.SelectState;
import io.zeddw.ssm.listener.SelectionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigBuilder;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineModelConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

/**
 * @author xingguan.wzt
 * @date 2020/09/25
 */
@Configuration
@EnableStateMachine
public class StatemachineConfig extends EnumStateMachineConfigurerAdapter<SelectState, SelectEvent> {

    @Autowired
    private SelectionListener selectionListener;

    @Override
    public void configure(StateMachineConfigBuilder<SelectState, SelectEvent> config) throws Exception {
    }

    @Override
    public void configure(StateMachineModelConfigurer<SelectState, SelectEvent> model) throws Exception {
        super.configure(model);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<SelectState, SelectEvent> config) throws Exception {
        config.withConfiguration()
            .autoStartup(true)
            .listener(selectionListener);
    }

    @Override
    public void configure(StateMachineStateConfigurer<SelectState, SelectEvent> states) throws Exception {
        super.configure(states);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<SelectState, SelectEvent> transitions) throws Exception {
        super.configure(transitions);
    }
}

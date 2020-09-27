package io.zeddw.ssm.listener;

import io.zeddw.ssm.domain.SelectEvent;
import io.zeddw.ssm.domain.SelectState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;

/**
 * 选品事件监听
 *
 * @author xingguan.wzt
 * @date 2020/09/25
 */
@Slf4j
@Component
public class SelectionListener extends StateMachineListenerAdapter<SelectState, SelectEvent> {

    @Override
    public void stateChanged(State<SelectState, SelectEvent> from, State<SelectState, SelectEvent> to) {
        log.info("State change from {} to {}", from.getId(), to.getId());
    }
}

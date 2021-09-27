package io.zeddw.gist.handler;

import io.zeddw.gist.Handler;

public interface ExecuteHandler<IN> extends Handler<IN> {

    boolean preProcess(IN context);

    boolean postProcess(IN context);
    
}

package io.zeddw.gist.processor;

import io.zeddw.gist.Config;
import io.zeddw.gist.handler.ExceptionHandler;
import io.zeddw.gist.handler.ExecuteHandler;

public abstract class AbstractStepProcessor<IN> extends AbstractProcessor<IN, Boolean> implements StepProcessor<IN>, ExecuteHandler<IN>, ExceptionHandler<IN> {
    
    public AbstractStepProcessor(Config config) {
        super(config);
    }

    @Override
    public Boolean process(IN context) {
        boolean rtn = true;
        if (isEnable()) {
            Exception ex = null;
            try {
                if (preProcess(context)) {
                    rtn &= doProcess(context);
                    rtn &= postProcess(context);
                }
            } catch (Exception e) {
                ex = e;
            } finally {
                if (ex != null) {
                    rtn &= handleException(context, ex);
                }
            }
        }
        return rtn;
    }
}

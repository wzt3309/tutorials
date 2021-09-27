package io.zeddw.gist.handler;

import io.zeddw.gist.Handler;

public interface ExceptionHandler<IN> extends Handler<IN> {

    boolean handleException(IN context, Exception ex);

}

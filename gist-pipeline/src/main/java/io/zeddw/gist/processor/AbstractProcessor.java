package io.zeddw.gist.processor;

import io.zeddw.gist.Config;
import io.zeddw.gist.Processor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class AbstractProcessor<IN, OUT> implements Processor<IN, OUT> {
    protected final Config config;

    @Override
    public boolean isEnable() {
        if (config != null) {
            return (Boolean) config.getOrDefault(this.getClass().getName(), true);
        }
        return true;
    }

    @Override
    public OUT process(IN context) throws Exception {
        if (isEnable()) {
            return doProcess(context);
        }
        return null;
    }

    protected abstract OUT doProcess(IN context) throws Exception;
}

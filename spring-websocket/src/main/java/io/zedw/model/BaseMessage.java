package io.zedw.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xingguan.wzt
 * @date 2020/12/03
 */
public abstract class BaseMessage implements Serializable {
    private LocalDateTime dateTime;
}

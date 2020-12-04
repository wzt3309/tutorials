package io.zedw.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xingguan.wzt
 * @date 2020/12/03
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class InputMessage extends BaseMessage {
    private static final long serialVersionUID = -3015556403931910388L;

    private String from;
    private String text;
}

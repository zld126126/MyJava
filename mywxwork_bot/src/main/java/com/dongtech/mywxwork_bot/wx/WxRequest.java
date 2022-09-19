package com.dongtech.mywxwork_bot.wx;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
public class WxRequest implements Serializable {
    public String message;
}

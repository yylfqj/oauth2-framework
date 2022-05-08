package org.oauth.core.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CheckToken
 * @Author yyl
 * @Date 2022-05-07 00:15:57
 * @Description CheckToken
 * @Version 1.0
 */
@Data
public class CheckToken implements Serializable {

    private static final long serialVersionUID = -5040601749012905398L;

    private Boolean active;

    private Long userId;
}

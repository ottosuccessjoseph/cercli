package com.cercli.common.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Getter
@SuperBuilder
@NoArgsConstructor
public class Holiday implements Serializable {
    private String name;
    private String description;
    private String date;

    private String countryCode;
    private String country;
}

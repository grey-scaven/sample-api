package com.grey.sample.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
public class TestRequestIF {

    @NotNull
    private Long code;

    @NotNull
    private String userId;

}

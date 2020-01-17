package com.grey.sample.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
public class SampleItemDto {

    private String itemName;

}

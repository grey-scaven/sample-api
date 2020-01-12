package controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
public class TestRequestIF {

    private Long code;

    private String userId;

}

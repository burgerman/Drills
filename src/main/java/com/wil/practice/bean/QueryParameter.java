package com.wil.practice.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryParameter {
    @ApiModelProperty("query param name")
    @JsonProperty("name")
    @Valid
    @NotNull(message = "Required Param Missing")
    @Size(min = 1, max = 50, message = "INVALID STRING LENGTH")
    private String name;

    @ApiModelProperty("query param value")
    @JsonProperty("value")
    @Valid
    private Object value = null;

    public QueryParameter() {
    }

    public QueryParameter name(String name) {
        this.name = name;
        return this;
    }

    public QueryParameter value(Object value) {
        this.value = value;
        return this;
    }


}

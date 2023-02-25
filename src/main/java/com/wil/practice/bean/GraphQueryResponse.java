package com.wil.practice.bean;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "queryResultType",
        defaultImpl = GraphQueryResponse.class,
        visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = GraphQueryResult.class, name = "Graph")})
public class GraphQueryResponse {
    @JsonProperty
    @NotNull (message = "Required Param Missing")
    @Size(min = 1, max = 20, message = "INVALID STRING LENGTH")
    private String status;

    @JsonProperty("results")
    @Valid
    @NotNull(message = "Required Param Missing")
    @Size(min = 1, max = 100, message = "INVALID ARRAY LENGTH")
    private List<GraphQueryResult> results = new ArrayList();

    @JsonProperty("category")
    @NotNull (message = "Required Param Missing")
    @Size(min = 1, max = 100, message = "INVALID STRING LENGTH")
    private String category;

    @JsonProperty("sub_category")
    @NotNull (message = "Required Param Missing")
    @Size(min = 1, max = 100, message = "INVALID STRING LENGTH")
    private String subCategory;

    @JsonProperty("invocation_time")
    @NotNull (message = "Required Param Missing")
    @Pattern(regexp = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])[T,t]([0-1][0-9]|2[0-3]):[0-5][0-9]:([0-5][0-9]|60)([.][0-9]+)?([Zz]|[+-][0-9]{2}:[0-9]{2})$",
            message = "INVALID PARAM SYNTAX")
    @Size(min = 20, max = 64, message = "INVALID STRING LENGTH")
    private String invocationTime;
}

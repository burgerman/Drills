package com.wil.practice.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GraphQueryRequest {

    @JsonProperty("timeout")
    @NotNull(message = "Required Param Missing")
    @Min(value = 0L, message = "INVALID LONG MIN VALUE")
    @Max(value = 20000L, message = "INVALID LONG Max VALUE")
    private Long timeout;

    @JsonProperty("parameters")
    @Valid
    @NotNull(message = "Required Param Missing")
    @Size(min = 1, max = 100, message = "INVALID ARRAY LENGTH")
    private List<QueryParameter> parameters = new ArrayList<>();

    @JsonProperty("queries")
    @Valid
    @NotNull(message = "Required Param Missing")
    @Size(min = 1, max = 100, message = "INVALID ARRAY LENGTH")
    private List<GraphQuery> queries = new ArrayList<>();

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


    public GraphQueryRequest addQueryItem(GraphQuery queryItem) {
        this.queries.add(queryItem);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphQueryRequest that = (GraphQueryRequest) o;
        return timeout.equals(that.timeout) &&
                parameters.equals(that.parameters) &&
                queries.equals(that.queries) &&
                category.equals(that.category) &&
                subCategory.equals(that.subCategory) &&
                invocationTime.equals(that.invocationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Object[]{timeout, parameters, queries, category, subCategory, invocationTime});
    }
}

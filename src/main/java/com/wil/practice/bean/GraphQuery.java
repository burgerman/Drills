package com.wil.practice.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GraphQuery {
    @ApiModelProperty("query id")
    @JsonProperty("id")
    @NotNull(message = "Required Param Missing")
    @Size(min = 1, max = 100, message = "INVALID STRING LENGTH")
    private String id;

    @ApiModelProperty("is audit")
    @JsonProperty("audit")
    private Boolean audit = false;

    public GraphQuery(String id) {
        this.id = id;
    }

    public GraphQuery id (String idName) {
        this.id = idName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphQuery that = (GraphQuery) o;
        return id.equals(that.id) &&
                audit.equals(that.audit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Object[]{id, audit});
    }
}

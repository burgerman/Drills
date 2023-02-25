package com.wil.practice.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GraphQueryResult extends BaseBean{

    private static final long serialVersionUUID = 1L;

    @JsonProperty("id")
    @NotNull(message = "Required Param Missing")
    @Size(min = 1, max = 100, message = "INVALID STRING LENGTH")
    private String id;

    @JsonProperty("execution_time")
    @NotNull(message = "Required Param Missing")
    @Min(value = 0L, message = "INVALID LONG MIN VALUE")
    @Max(value = 20000L, message = "INVALID LONG Max VALUE")
    private Long executeTime;

    @JsonProperty("results")
    @Valid
    @NotNull(message = "Required Param Missing")
    private Object results = null;

    @JsonProperty("status_message")
    @NotNull (message = "Required Param Missing")
    @Size(min = 1, max = 200, message = "INVALID STRING LENGTH")
    private String statusMessage;

    @JsonProperty("status_code")
    @NotNull (message = "Required Param Missing")
    @Size(min = 1, max = 20, message = "INVALID STRING LENGTH")
    private String statusCode;

    public GraphQueryResult(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphQueryResult that = (GraphQueryResult) o;
        return id.equals(that.id) &&
                executeTime.equals(that.executeTime) &&
                results.equals(that.results) &&
                statusMessage.equals(that.statusMessage) &&
                statusCode.equals(that.statusCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Object[]{id, executeTime, results, statusMessage, statusCode});
    }
}

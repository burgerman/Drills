package com.wil.practice.bean;

import com.wil.practice.exceptions.GraphVarException;
import com.workflow.GraphClient;
import lombok.Data;
import org.apache.commons.lang3.time.FastDateFormat;
import org.glassfish.jersey.internal.guava.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Data
public class GraphComputeVar {
    private static final Logger logger = LoggerFactory.getLogger(GraphComputeVar.class);

    private static final long DEFAULT_SLA = 75L;

    private static final String SUCCESS_CODE = "SUCCESS";

    protected String name;
    protected boolean isSkip;

    public GraphComputeVar(String name) {
        this.name = null;
        this.isSkip = false;
        if (name == null) {
            this.name = this.getClass().getSimpleName();
        } else {
            this.name = name;
        }
    }

    public boolean isThreadSafe() {
        return false;
    }

    protected Object calculate(Map<String, Object> wfContext) throws Exception{
        try{
            if(!this.isSkip) {
                String corrId = (String) wfContext.get("corr_id_");
                Object queryResult = executeQuery(getUGSRequest(wfContext), corrId);
                return queryResult;
            }
        } catch (Exception e) {
            logger.warn("Exception happens when calculating var" + this.getName());
        }
        return Maps.newHashMapWithExpectedSize(wfContext.size());
    }

    private Object executeQuery(GraphQueryRequest request, String corrId) {
        GraphQueryResponse response = GraphClient.getInstance().query(request, corrId);
        List<GraphQueryResult> results = response.getResults();
        if(results!=null && results.size()>0) {
            GraphQueryResult result = results.get(0);
            if(SUCCESS_CODE.equals(result.getStatusCode())) {
                return result.getResults();
            }
        }
        return new HashMap<String, Object>();
    }

    private GraphQueryRequest getUGSRequest(Map<String, Object> context) {
        String checkpointName = (String) context.get("checkpoint");
        String flowName = (String) context.get("flow_name");
        Date pit = (Date) context.get("pit_timestamp");
        Date invocationTime = pit != null? pit : new Date();
        List<GraphQuery> batchQuery = new ArrayList<>();
//        batchQuery.add(new GraphQuery().id(getName()));
        GraphQueryRequest request = new GraphQueryRequest();
        request.setTimeout(DEFAULT_SLA);
        request.setParameters(getParameters(context));
        request.setQueries(batchQuery);
        request.setCategory(checkpointName);
        request.setSubCategory(flowName);
        request.setInvocationTime(FastDateFormat.getInstance("yyyy-MM-dd", TimeZone.getTimeZone("GMT-8:00")).format(invocationTime));
        return request;
    }

    private List<QueryParameter> getParameters(Map<String, Object> args) {
        Map<String, Object> queryParams = new HashMap<>(32);
        List<QueryParameter> queryParameters = new LinkedList<>();
        String source = "";
        String dest = "";
        queryParams.put("_vertexName", source);
        queryParams.put("_idName", "emailAddress");
        queryParams.put("h1ExtEdgeDirection", "inE");
        String edgeName = "EmailPPAccountUsedBy";
        if(edgeName == null) {
            throw new GraphVarException("Edge can only be: (Asset, PP), (PP, Asset)");
        } else {
            queryParams.put("_edgeType", edgeName);
            queryParams.put("_preloadBarrier", 100);
            queryParams.put("_limit", 100);
            queryParams.entrySet().forEach(e-> queryParameters.add(new QueryParameter().name(e.getKey()).value(e.getValue())));
        }
        return queryParameters;
    }

}

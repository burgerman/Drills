package com.workflow;

import com.wil.practice.annotations.EndPoint;
import com.wil.practice.bean.GraphQueryRequest;
import com.wil.practice.bean.GraphQueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class GraphClient {
    private static final Logger logger = LoggerFactory.getLogger(GraphClient.class);
    private static final String QUERY_PATH = "/v1/risk/compute-graph-query";

    @Autowired
    @EndPoint
    private WebTarget graphServ;

    private GraphClient() {
        logger.info("initialize graph client");
    }

    public static GraphClient getInstance() {
        return GraphClientHolder.instance;
    }

    public GraphQueryResponse query(GraphQueryRequest request, String corrId) {
        Builder requestBuilder = graphServ.path(QUERY_PATH).request(MediaType.APPLICATION_JSON);
        requestBuilder.header("Content-type", MediaType.APPLICATION_JSON);
        requestBuilder.header("CORRELATION-ID", corrId);

        try(Response response = requestBuilder.post(Entity.json(request))) {
            if(!Response.Status.BAD_REQUEST.equals(response.getStatusInfo())) {
                GraphQueryResponse gqr = response.readEntity(GraphQueryResponse.class);
                return gqr;
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return null;
    }

    private static class GraphClientHolder {
        private static final GraphClient instance = new GraphClient();
    }
}

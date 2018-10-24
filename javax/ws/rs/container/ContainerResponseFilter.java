package com.iglottery.admin.filter;

import java.io.IOException;
import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import org.slf4j.MDC;

//@Priority(123) Filter的順序,1是最先
@Provider
public class LoggerFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        MDC.put("start-time", String.valueOf(System.currentTimeMillis()));
//        System.out.println(requestContext.getUriInfo().getPath());
//        System.out.println(resourceInfo.getResourceMethod().getName());
//        System.out.println(resourceInfo.getResourceClass().getCanonicalName());
//        System.out.println(requestContext.getUriInfo().getPath()+"("+requestContext.getMethod()+")");
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext containerResponseContext) throws IOException {
        String stTime = MDC.get("start-time");
        System.out.println(requestContext.getUriInfo().getPath() + "(" + requestContext.getMethod() + "), ");
        System.out.print(containerResponseContext.getStatusInfo() + "(" + containerResponseContext.getStatus() + ")");
        if (null == stTime || stTime.length() == 0) {
            System.out.println("↖失敗");
            System.out.println("==========");
            return;
        }
        long startTime = Long.parseLong(stTime);
        long executionTime = System.currentTimeMillis() - startTime;
        System.out.println("↖executionTime: " + executionTime);
        System.out.println("==========");
        MDC.clear();
    }
}

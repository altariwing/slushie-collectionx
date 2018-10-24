
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

//@Priority(123) Filter
@Provider
public class RedirectFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext ctx) {

        if (shouldRedirect(ctx)) {
            ctx.setMethod("GET"); //然而並不需要

            UriInfo uriInfo = ctx.getUriInfo();
            UriBuilder hostUriBuilder = uriInfo.getRequestUriBuilder();
//            throw new NotAuthorizedException("You Havn't Login");
            hostUriBuilder.replacePath("/base-api/login");
            ctx.abortWith(Response.seeOther(hostUriBuilder.build()).build());
        }
    }

    private boolean shouldRedirect(ContainerRequestContext reqContext) {
        if (reqContext.getUriInfo().getRequestUri().getPath().toString().equals("/base-api/login")) {
            return false;
        }
        return true;
    }
}
/*
In case the filter should be applied at the pre-match extension point, i.e.
before any request matching has been performed by JAX-RS runtime, the filter MUST be annotated with a @PreMatching annotation.

Use a pre-match request filter to update the input to the JAX-RS matching algorithm,
e.g., the HTTP method, Accept header, return cached responses etc.

例如以下這個, 需要在Class前面加@PreMatching
hostUriBuilder.queryParam("name", "你好");
*/

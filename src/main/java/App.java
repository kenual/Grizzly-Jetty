import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Collections;
import java.util.Set;

public class App  extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resourceProviderFeatures = Collections.emptySet();
        resourceProviderFeatures.add(JacksonFeature.class);

        return resourceProviderFeatures;
    }

    public static void main(String[] args) {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(8080).build();
        ResourceConfig config = new ResourceConfig(ResourceConfig.forApplication(new App()));
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        server.getListeners()
                .forEach(networkListener -> System.out.println(networkListener.toString()));
    }
}

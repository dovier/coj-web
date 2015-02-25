
package cu.uci.coj.websocket;

import org.atmosphere.config.service.Disconnect;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Message;
import org.atmosphere.config.service.Ready;
import org.atmosphere.cpr.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

@ManagedService(path = "{category: [a-zA-Z][a-zA-Z_0-9]*}")
public class DictumDelivery {
    private final Logger logger = LoggerFactory.getLogger(DictumDelivery.class);

    public static ConcurrentHashMap<String, String> resources = new ConcurrentHashMap<>();
    private String categoryName;
    private String mappedPath;
    public static BroadcasterFactory factory;

    @Ready(value = Ready.DELIVER_TO.ALL, encoders = {JacksonEncoder.class})
    public Category onReady(final AtmosphereResource r) {
        logger.info("Browser {} connected.", r.uuid());
        if (categoryName == null) {
            mappedPath = r.getBroadcaster().getID();
            categoryName = mappedPath.split("/")[2];
            factory = r.getAtmosphereConfig().getBroadcasterFactory();
        }

        return new Category(resources.keySet(), getCategories(factory.lookupAll()));
    }

    private static Collection<String> getCategories(Collection<Broadcaster> broadcasters) {
        Collection<String> result = new ArrayList<String>();
        for (Broadcaster broadcaster : broadcasters) {
            if (!("/*".equals(broadcaster.getID()))) {
                result.add(broadcaster.getID().split("/")[2]);
            }
        };
        return result;
    }

    @Disconnect
    public void onDisconnect(AtmosphereResourceEvent event) {
        if (event.isCancelled()) {            
            resources.values().remove(event.getResource().uuid());
            logger.info("Browser {} unexpectedly disconnected", event.getResource().uuid());
        } else if (event.isClosedByClient()) {
            logger.info("Browser {} closed the connection", event.getResource().uuid());            
            
            try {
                event.getResource().close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(DictumDelivery.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
    }

    @Message(encoders = {JacksonEncoder.class}, decoders = {CategoryDecoder.class})
    public Category onCategoryMessage(Category message) throws IOException {

        if (!resources.containsKey(message.getClient())) {
            resources.put(message.getClient(), message.getUuid());
            return new Category(message.getClient(), " subscribe to category " + categoryName, resources.keySet(), getCategories(factory.lookupAll()));
        }

        if (message.getMessage().contains("disconnecting")) {
            resources.remove(message.getClient());
            
            return new Category(message.getClient(), " disconnected from category " + categoryName, resources.keySet(), getCategories(factory.lookupAll()));
        }

        message.setUsers(resources.keySet());
        logger.info("{} just send {}", message.getClient(), message.getMessage());
        return new Category(message.getClient(), message.getMessage(), resources.keySet(), getCategories(factory.lookupAll()));
    }

    @Message(decoders = {ResourceDecoder.class})
    public void onResourceMessage(ResourceMessage resource) throws IOException {
        String userUUID = resources.get(resource.getClient());
        if (userUUID != null) {
            AtmosphereResource r = AtmosphereResourceFactory.getDefault().find(userUUID);

            if (r != null) {
                Category m = new Category(resource.getClient(), " sent message: " + resource.getMessage().split(":")[1], resources.keySet(), getCategories(factory.lookupAll()));
                if (!resource.getClient().equalsIgnoreCase("all")) {
                    factory.lookup(mappedPath).broadcast(m, r);
                }
            }
        } else {
            Category m = new Category(resource.getClient(), " sent a message to all categories: " + resource.getMessage().split(":")[1], resources.keySet(), getCategories(factory.lookupAll()));
            MetaBroadcaster.getDefault().broadcastTo("/*", m);
        }
    }

}
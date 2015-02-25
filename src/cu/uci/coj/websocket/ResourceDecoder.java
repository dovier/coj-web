package cu.uci.coj.websocket;

import org.atmosphere.config.managed.Decoder;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Decode a String into a {@link cu.uci.websocket.ResourceMessage}.
 */
public class ResourceDecoder implements Decoder<String, ResourceMessage> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public ResourceMessage decode(String s) {
        try {
            return mapper.readValue(s, ResourceMessage.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

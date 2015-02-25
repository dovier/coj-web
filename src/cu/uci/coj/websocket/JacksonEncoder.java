package cu.uci.coj.websocket;

import org.atmosphere.config.managed.Encoder;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Encode a {@link Category} into a String
 */
public class JacksonEncoder implements Encoder<JacksonEncoder.Encodable, String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String encode(Encodable m) {
        try {
            return mapper.writeValueAsString(m);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Marker interface for Jackson.
     */
    public static interface Encodable {
    }
}

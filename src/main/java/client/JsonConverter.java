package client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.core.JsonParser.Feature.*;

public class JsonConverter {

    private final TypeFactory typeFactory = TypeFactory.defaultInstance();
    private final CollectionType repoListType = typeFactory.constructCollectionType(List.class, Repo.class);
    private final ObjectMapper mapper = new ObjectMapper();

    public JsonConverter() {
        mapper.configure(ALLOW_UNQUOTED_FIELD_NAMES, true);
    }

    public User toUser(String json) {
        try {
            return mapper.readValue(json, User.class);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public List<Repo> toRepos(String json) {
        try {
            return mapper.readValue(json, repoListType);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public Map<String, String> toMap(String json) {
        try {
            return mapper.readValue(json, new TypeReference<Map<String, String>>(){});
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}

package client;

import uk.co.tpplc.http.Response;
import uk.co.tpplc.http.SimpleHttpClient;

import java.util.*;

public class GithubClient {

    private final JsonConverter jsonConverter = new JsonConverter();

    private final SimpleHttpClient http = new SimpleHttpClient();

    public List<String> getUserLanguages(String username) {
        User user = getUser(username);
        List<Repo> repos = getRepos(user.getReposUrl());
        Set<String> languages = new HashSet<>();
        for (Repo repo : repos)
            languages.addAll(getLanguages(repo.getLanguagesUrl()));
        return new ArrayList<>(languages);
    }

    public User getUser(String username) {
        String url = "http://localhost:8099/users/" + username;
        Response response = http.get(url);
        return jsonConverter.toUser(response.getBody());
    }

    public List<Repo> getRepos(String reposUrl) {
        Response response = http.get(reposUrl);
        return jsonConverter.toRepos(response.getBody());
    }

    public List<String> getLanguages(String languagesUrl) {
        Response response = http.get(languagesUrl);
        Map<String, String> languageMap = jsonConverter.toMap(response.getBody());
        return new ArrayList<>(languageMap.keySet());
    }

}

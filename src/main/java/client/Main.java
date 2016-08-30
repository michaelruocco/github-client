package client;

import client.GithubClient.GithubClientBuilder;
import uk.co.tpplc.http.SimpleHttpClient;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        GithubClient githubClient = new GithubClientBuilder().build();
        List<String> languages = githubClient.getUserLanguages("michaelruocco");
        languages.stream().forEach(e -> System.out.println(e));
    }

}

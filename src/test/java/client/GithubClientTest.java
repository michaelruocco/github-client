package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import uk.co.tpplc.http.Response;
import uk.co.tpplc.http.SimpleHttpClient;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GithubClientTest {

    private final GithubClient client = new GithubClient(new FakeGithub());

    @Test
    public void shouldReturnUser() {
        User user = client.getUser("hackeryou");

        assertThat(user).isEqualToComparingFieldByField(new TestUser());
    }

    @Test
    public void shouldReturnUserRepos() {
        String reposUrl = "https://api.github.com/users/HackerYou/repos";

        List<Repo> repos = client.getRepos(reposUrl);

        assertThat(repos.size()).isEqualTo(2);
        assertThat(repos.get(0)).isEqualToComparingFieldByField(new TestRepo1());
        assertThat(repos.get(1)).isEqualToComparingFieldByField(new TestRepo2());
    }

    @Test
    public void shouldReturnRepoLanguages() {
        String languagesUrl = "https://api.github.com/repos/HackerYou/amazon/languages";

        List<String> languages = client.getLanguages(languagesUrl);

        assertThat(languages.size()).isEqualTo(4);
        assertThat(languages.get(0)).isEqualTo("Ruby");
        assertThat(languages.get(1)).isEqualTo("JavaScript");
        assertThat(languages.get(2)).isEqualTo("CoffeeScript");
        assertThat(languages.get(3)).isEqualTo("CSS");
    }

    public static class FakeGithub extends SimpleHttpClient {

        @Override
        public Response get(String url) {
            if (url.endsWith("/users/hackeryou"))
                return getUserResponse();
            else if(url.endsWith("/users/HackerYou/repos"))
                return getUserReposResponse();
            return repoLangaguesResponse();

        }

        private Response repoLangaguesResponse() {
            String languagesJson = "{\n" +
                    "Ruby: 21246,\n" +
                    "JavaScript: 664,\n" +
                    "CoffeeScript: 422,\n" +
                    "CSS: 1924\n" +
                    "}";
            return createSuccessResponse(languagesJson);
        }

        private Response getUserResponse() {
            try {
                User user = new TestUser();
                ObjectMapper mapper = new ObjectMapper();
                String userJson = mapper.writeValueAsString(user);
                return createSuccessResponse(userJson);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        private Response getUserReposResponse() {
            try {
                List<Repo> repos = createStubUserRepos();
                ObjectMapper mapper = new ObjectMapper();
                String userRepoJson = mapper.writeValueAsString(repos);
                return createSuccessResponse(userRepoJson);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        private List<Repo> createStubUserRepos() {
            List<Repo> repos = new ArrayList<Repo>();
            repos.add(new TestRepo1());
            repos.add(new TestRepo2());
            return repos;
        }

        private Response createSuccessResponse(String json) {
            return new Response(200, json);
        }

    }

    private static class TestUser extends User {

        public TestUser() {
            setOwner("hackeryou");
            setPublicRepos(35);
            setReposUrl("https://api.github.com/users/HackerYou/repos");
        }

    }

    private static class TestRepo1 extends Repo {

        public TestRepo1() {
            setOwner("hackeryou");
            setName("amazon");
            setLanguagesUrl("https://api.github.com/repos/HackerYou/amazon/languages");
        }

    }

    private static class TestRepo2 extends Repo {

        public TestRepo2() {
            setOwner("hackeryou");
            setName("assigner");
            setLanguagesUrl("https://api.github.com/repos/HackerYou/assigner/languages");
        }

    }

}

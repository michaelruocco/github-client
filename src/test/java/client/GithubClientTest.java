package client;

import client.GithubClient.GithubClientBuilder;
import org.junit.Rule;
import org.junit.Test;
import uk.co.mruoc.fake.github.FakeGithubRule;
import uk.co.mruoc.fake.github.FakeGithubRule.FakeGithubRuleBuilder;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GithubClientTest {

    private static final int PORT = 8099;
    private static final String URL = "http://localhost:" + PORT;

    @Rule
    public final FakeGithubRule githubRule = new FakeGithubRuleBuilder()
            .setPort(PORT)
            .setResponseHostUrl(URL)
            .build();

    private final GithubClient client = new GithubClientBuilder()
            .setHostUrl(URL)
            .build();

    @Test
    public void shouldReturnUser() {
        User user = client.getUser("hackeryou");

        assertThat(user).isEqualToComparingFieldByField(new TestUser());
    }

    @Test
    public void shouldReturnUserRepos() {
        String reposUrl = URL + "/users/HackerYou/repos";

        List<Repo> repos = client.getRepos(reposUrl);

        assertThat(repos.size()).isEqualTo(30);
        assertThat(repos.get(0)).isEqualToComparingFieldByFieldRecursively(new TestRepo1());
        assertThat(repos.get(1)).isEqualToComparingFieldByFieldRecursively(new TestRepo2());
    }

    @Test
    public void shouldReturnRepoLanguages() {
        String languagesUrl = URL + "/repos/HackerYou/amazon/languages";

        List<String> languages = client.getLanguages(languagesUrl);

        assertThat(languages.size()).isEqualTo(4);
        assertThat(languages.get(0)).isEqualTo("Ruby");
        assertThat(languages.get(1)).isEqualTo("CSS");
        assertThat(languages.get(2)).isEqualTo("JavaScript");
        assertThat(languages.get(3)).isEqualTo("CoffeeScript");
    }

    private static class TestUser extends User {

        private TestUser() {
            setLogin("hackeryou");
            setPublicRepos(35);
            setReposUrl(URL + "/users/HackerYou/repos");
        }

    }

    private static class TestRepo1 extends Repo {

        private TestRepo1() {
            User user = new User();
            user.setLogin("hackeryou");
            user.setReposUrl(URL + "/users/HackerYou/repos");
            setOwner(user);
            setName("amazon");
            setLanguagesUrl(URL + "/repos/HackerYou/amazon/languages");
        }

    }

    private static class TestRepo2 extends Repo {

        private TestRepo2() {
            User user = new User();
            user.setLogin("hackeryou");
            user.setReposUrl(URL + "/users/HackerYou/repos");
            setOwner(user);
            setName("assigner");
            setLanguagesUrl(URL + "/repos/HackerYou/assigner/languages");
        }

    }

}

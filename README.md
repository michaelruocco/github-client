# GitHub Client

[![Build Status](https://travis-ci.org/michaelruocco/github-client.svg?branch=master)](https://travis-ci.org/michaelruocco/github-client)
[![Dependency Status](https://www.versioneye.com/user/projects/57c6f439939fc600508e8ec7/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/57c6f439939fc600508e8ec7)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/c09a98e2387b4e648485969db742c251)](https://www.codacy.com/app/michael-ruocco/github-client?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=michaelruocco/github-client&amp;utm_campaign=Badge_Grade)

This project is a simple demonstration of how to use the [fake-github](https://github.com/michaelruocco/fake-github)
to test client code. This is by no means a complete example but demonstrates the use
of a separate hand coded fake. This can be preferrable to using wiremock to spin up
a mock in test code as it is reusable across multiple projects, it also simplifies the
amount of test and setup code required.

The client provides the ability to read user details, user repos, repo
languages and user languages via the use of the GitHub API. When the main
method of the Main class is run it will print out the list of languages for
the username that is passed into the client as a hardcoded parameter.

The tests test each of the separate calls individually, but make use of the
[fake-github](https://github.com/michaelruocco/fake-github) project to
keep test code simple.

## Running the Tests

You can run the tests for this project by running the following command:

```
gradlew clean build
```

## Running the Application

You can run the application by running the following command:

```
gradlew run
```

When the program runs it will print a list of languages for whichever
user has been hardcoded into the main method, this is currently "michaelruocco"
but you can obviously change it if you wish to see your own languages listed.

## Note

GitHub has the ability to be able to throttle the number of requests you make
so if you run the application too many times it may start to fail if you are being
limited by GitHub, this is one of the reasons why it is good that the tests use
the fake GitHub since you can get responses that mirror the real API without actually
hitting it.


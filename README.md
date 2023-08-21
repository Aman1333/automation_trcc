# Project for running and scaling silenium tests

## Local development

You would need to start docker compose containers to launch Silenium hub and related browsers

```shell
sudo docker-compose up --build
```

After that you can run the maven tests viat his command

```shell
mvn test
```

Or run only specific test files

```shell
mvn test -Dtest=AsapTicketsLandingPageTests
```

## Automation

GitLab CI covers automated test running. All the tests that could be run in an isolated environment can be found in .gitlab-ci.yaml


## Gitlab Runner with Docker executioner

A template for settings on how to set-up docker gitlab runner for this project

### Option 1. Register the runner
```shell
docker run --rm -it -v $(pwd)/config:/etc/gitlab-runner \
    gitlab/gitlab-runner register
```

and then change the configurations according to ```./config``` folder toml files 

### Options 2. Register the runner - quick script
```shell
./register.sh <your registration token> <runner name> <runner tag list>
```

### Start gitlab runner

```shell
docker run -d --name gitlab-runner\
     -v $(pwd)/config:/etc/gitlab-runner \
     -v /var/run/docker.sock:/var/run/docker.sock \
     gitlab/gitlab-runner:latest
```
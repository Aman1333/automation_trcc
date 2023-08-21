cp $(pwd)/config/config.base.toml $(pwd)/config/config.toml

docker run --rm -it -v $(pwd)/config:/etc/gitlab-runner \
    gitlab/gitlab-runner register \
    --url https://gitlab.dyninno.net/ --registration-token "$1" \
    --non-interactive \
    --name "$2" \
    --tag-list "$3" \
    --executor docker \
    --docker-image node:latest \
    --template-config /etc/gitlab-runner/config.template.toml


FROM josehilera/ubuntu-ci:latest

WORKDIR /actions-runner
RUN curl -o actions-runner-linux-x64-2.313.0.tar.gz -L https://github.com/actions/runner/releases/download/v2.313.0/actions-runner-linux-x64-2.313.0.tar.gz
RUN tar xzf ./actions-runner-linux-x64-2.313.0.tar.gz
ENV RUNNER_ALLOW_RUNASROOT="1"
EXPOSE 8080
EXPOSE 3306

CMD ["/bin/sh", "-c", "sudo usermod -d /var/lib/mysql/ mysql && sudo service mysql start && sh /usr/local/tomcat/bin/catalina.sh start && /bin/bash"]

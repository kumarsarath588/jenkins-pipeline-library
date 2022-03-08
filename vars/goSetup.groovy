#!groovy
/* do the common things we do everywhere */

def call(repo) {
  env.GOVERSION = "1.17"
  GoVersion = sh(script: "go version | awk '{split(\$0, a, \" \"); print a[3]}' | sed 's/go//g'", returnStdout: true).trim()
  sh "echo Installed Go Version is ${GoVersion}"
  sh "sudo rm -rf /usr/local/go"
  sh "curl -LO https://storage.googleapis.com/golang/go${env.GOVERSION}.linux-amd64.tar.gz"
  sh "sudo tar -C /usr/local/ -xzf go${env.GOVERSION}.linux-amd64.tar.gz"
  sh "sudo ln -sf /usr/local/go/bin/go /usr/local/bin/go"
  sh "rm -rf /root/.go"
  sh "mkdir -p /root/.go/{bin,src,pkg}"
  env.GOPATH = "/root/.go"
  env.GOBIN = "/root/.go/bin"
  sh "go get golang.org/x/lint/golint"
  env.PATH = "${env.GOPATH}/bin:${env.PATH}"
}

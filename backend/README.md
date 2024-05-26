# Backend(Web Application Server)

## DB Docker 실행방법

### MySQL(RDB) 설치하기
```Shell
$ docker pull mysql:8.3.0
$ docker run -it --name mysql_test -p 3307:3307 -e MYSQL_DATABASE=sse_server_test_db -e MYSQL_ROOT_PASSWORD=sseservertest -e MYSQL_USER=sse_server_test -e MYSQL_PASSWORD=sseservertest -d mysql:8.3.0 --port=3307 --default_authentication_plugin=mysql_native_password
```

### 2. Redis(Session) 설치하기
```Shell
$ docker pull redis:alpine3.19
docker run -it --name redis_test -p 6379:6379 -d redis:alpine3.19 --requirepass sseservertest
```

## WAS Docker 실행방법

### 1. 환경변수 설정하기 
```Shell
$ cd {현재 프로젝트 경로}/backend
$ vi ./env/backend/.env
# DB_DOMAIN 경로 Backend에서 실행한 MySQL Docker 경로로 설정(docker inspect로 확인)
# SESSION_REDIS_HOST 경로 Backend에서 실행한 Redis Docker 경로로 설정(docker inspect로 확인)
```

### 2. Gradle 빌드하기
```Shell
$ ./gradlew build
```

### 3. Docker Image 빌드하기
```Shell
$ docker build -t was .
```

### 4. Docker Image 실행하기
```Shell
$ docker run -it -d -p 8080:8080 --name was was
```

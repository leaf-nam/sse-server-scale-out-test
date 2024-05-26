# SSE Server

## Docker 실행방법
### 1. 환경변수 설정하기
```Shell
cd {현재 프로젝트 경로}/sse-server
vi ./env/backend/.env
# DB_DOMAIN 경로 Backend에서 실행한 MySQL Docker 경로로 설정(docker inspect로 확인)
docker inspect mysql_test
# SESSION_REDIS_HOST 경로 Backend에서 실행한 Redis Docker 경로로 설정(docker inspect로 확인)
docker inspect redis_test
```

### 2. Gradle 빌드하기
```Shell
./gradlew build
```

### 3. Docker Image 빌드하기
```Shell
docker build -t sse .
```

### 4. Docker Image 실행하기
```Shell
docker run -it -d -p 8082:8082 --name sse sse
```

# A502 SSE Server

# Docker 실행방법
1. 환경변수 설정하기
```python
$cd {현재 프로젝트 경로}/sse-server
$vi ./env/backend/.env
# DB_DOMAIN 경로 Backend에서 실행한 MySQL Docker 경로로 설정(docker inspect로 확인)
# SESSION_REDIS_HOST 경로 Backend에서 실행한 Redis Docker 경로로 설정(docker inspect로 확인)
```

2. Docker Image 빌드하기
```python
$docker build -t sse .
```

3. Docker Image 실행하기
```python
$docker run -it -d -p 8082:8080 --name sse sse
```

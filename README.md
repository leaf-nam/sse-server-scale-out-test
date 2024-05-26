# SSE Server Scale out Test

## 알림서버 분리 테스트

### 테스트 목적 : SSE서버 분리 전후 메인 서버의 Connection 연결개수 확인

#### SSE 분리 후 테스트 구성
  1. Docker로 SSE server, WAS 2개 서버 동시 실행
  2. DB 및 세션 캐싱을 위해 Docker로 Redis, MySQL 실행
  3. JMeter로 최대 로그인 연결(TCP Connection)개수 측정
     
#### SSE 분리 전 테스트 구성
  1. 메인서버에 SSE 연결 관련 로직 추가
  2. Docker로 WAS, Redis, MySQL 실행
  3. JMeter로 최대 로그인 연결(TCP Connection)개수 측정

#### 테스트 시나리오 
  1. 테스트 전 로그인에 필요한 회원가입(API)
  2. JMeter로 로그인 요청, DB 조회를 통해 로그인 요청 검증
  3. 로그인 성공 시 JMeter로 SSE 연결요청
  4. SSE 연결 지속(60초)

# ENV 관리용 프로젝트

## 사용방법

### env 경로가 아직 추가되지 않은 프로젝트

1. env 브랜치 현재 프로젝트에 가져오기

- env 파일이 필요한 프로젝트의 루트 디렉토리에서 아래 명령어를 입력합니다.
  ```git
  git submodule add https://lab.ssafy.com/spearoad15/env.git ./env 
  ```
  > private 프로젝트이기 때문에 git 에 로그인하지 않으면 오류가 발생합니다!

2. env 브랜치 업데이트

- 동일하게 루트 디렉토리에서 아래 명령어를 입력합니다.
  ```git
  git submodule update --init --recursive
  ```

3. /env 경로에 현재 프로젝트가 잘 가져와지는지 확인합니다.

### env 경로가 추가된 프로젝트

1. 프로젝트 받아오기

- env 경로가 있는 프로젝트를 받아옵니다.
    ```git
  git clone {프로젝트 경로} 
  ```

2. env 브랜치 업데이트

- env 경로 내부를 업데이트합니다.
  ```git
  git submodule init
  git submodule update
  ```

### 본인이 env파일을 업데이트 할 경우

1. env 프로젝트 업데이트

- env(현재) 프로젝트를 업데이트합니다.
  ```git
  cd ./env
  git add .
  git commit -m "env update"
  git push origin master
  ```

2. 프로젝트 업데이트

- env 프로젝트를 포함하고 있는 루트 디렉터리에서 업데이트합니다.
  ```git
  git add .
  git commit -m {커밋 메시지 작성}
  git push origin {브랜치명}
  ```

### 다른 사람이 env파일을 업데이트 했을 경우

- 루트 디렉터리에서 아래 명령어를 사용합니다.
  ```git
  git submodule update --remote
  ```

### Commit 후 Push가 되지 않을 경우 && master파일이 pull되지 않을 경우

- 현재 브랜치가 master인지 확인하고, master가 아니면 변경합니다.
  ```git
  # master 브랜치인지 확인
  git branch
  
  # 브랜치 변경 후 pull
  git switch master
  git pull origin master
  ```

## 프로젝트 구조

```java
env(root)
  ├─backend // 백엔드 env파일 저장
  └─frontend // 프론트엔드 env파일 저장
```

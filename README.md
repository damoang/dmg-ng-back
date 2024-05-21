# DAMOANG NG Backend

## 기여방법 안내
1. 깃허브 이슈를 확인합니다.
2. 깃 저장소를 fork 합니다.
3. 이슈에 번호가 포함된 feature 브랜치를 생성합니다.
4. feature 브랜치는 dev브랜치의 변경사항을 반영하여 개발 후 dev merge합니다. 

예시)  
a. 깃허브 이슈변호 3번이라면 feature-3 브랜치에서 작업.  
b. origin dev 브랜치 변경사항 pull하고 작업중인 feature-3에 반영.  
c. 개발완료한 feature-3 브랜치를 dev에 merge하고 push 후 PR 보냄.  

## 컨벤션(진행중)
아래 링크의 컨벤션을 참고하여 조정할 예정입니다.
https://naver.github.io/hackday-conventions-java/#naming 

## Local Swagger 주소
http://localhost:8080/swagger-ui/index.html
 
## 설정
1. `.env.local` 파일을 `.env`로 복사합니다.
2. `.env`의 DB 정보를 설정합니다.

## 배포
이미지를 build 하거나 docker compose 후 실행할 수 있습니다.

### Docker build
```bash
docker build -t damoang-api .
docker run -d -p 8080:8080 --name damoang-api --env-file .env --name damoang-api damoang-api
```

### Docker compose
```bash
docker compose -f compose_app.yaml up -d
```

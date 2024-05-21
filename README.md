# DAMOANG NG Backend
## 기여방법
1. 이슈를 확인합니다.
2. 깃 저장소를 fork 합니다.
3.

## 컨벤션
1. IDE는 intellij, vscode, eclipse(sts) 중에 하나를 사용하세요
2. Tab은 Space 4개로 합니다.  
   (IDE의 indent를 4개로 수정해 주세요)
3. 포맷터는 prettier 설치하세요
 
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

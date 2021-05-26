[![Generic badge](https://img.shields.io/badge/python-3.6.8-<COLOR>.svg)](https://shields.io/)[![GitHub release](https://img.shields.io/github/release/Naereen/StrapDown.js.svg)](https://GitHub.com/Naereen/StrapDown.js/releases/)



![KOMU 로고](documents/KOMU 로고.png)



# SSAFY 4th 특화 프로젝트 4팀

> KPOP 추천 및 커뮤니티 웹사이트







## 1. Getting Started





### 1) Requirements

- Java
- npm
- STS
- Python





### 2) Install



#### (1) Frontend

- Frontend에서 package들을 설치합니다.

```bash
$ cd frontend
$ npm install
```

#### (2) Python

- 프로젝트 실행에 필요한 라이브러리들이 **requirements.txt**에 있습니다.
- **pymysql**도 설치해줘야합니다.

```bash
$ cd python/Algorithm
$ pip install -r requirements.txt
$ pip install pymysql
```





### 3) Usage



#### (1) Frontend

- **VSCode**를 이용하여 Frontend 서버를 실행합니다.

```bash
$ cd frontend
$ npm run serve
```

#### (2) Backend

```
1. STS 실행
2. File - Import Projects from File System... 클릭
3. backend폴더를 Directory로 선택
4. Package Explorer에 있는 backend를 마우스 우클릭 후 Run As - Spring Boot App 클릭
```







## 2. ERD



![ERD](documents/erd.JPG)







## 3. 시스템 아키텍쳐



![시스템 아키텍쳐](documents/시스템 아키텍쳐.JPG)







## 4. 메인 기능





### 1) 노래 추천



#### (1) 장르별 노래 추천

- 노래의 장르를 벡터화하여 가장 유사도가 높은 노래를 추천해줍니다.

#### (2) 가사별 노래 추천

- 노래의 가사들을 전처리하고 본인이 좋아요 누른 단어기반으로 TF-IDF 알고리즘을 실행해 노래를 추천해줍니다.





### 2) 음악 단어장

- 가사에 있는 단어중 본인이 맘에 드는 단어를 음악 단어장에 추가할 수 있습니다.
- 음악 단어장에 추가할 때, 코뮤위키에 등록되어 있다면 음악 단어장에만 추가가 되고, 코뮤위키에 등록되어 있지 않다면 코뮤위키에 등록이 되고 음악 단어장에도 추가가 됩니다.





### 3) 코뮤위키

- 한국의 문화를 알려주는 단어 설며 위키
- 로그인 시 직접 등록하고 수정할 수 있습니다.





### 4) 커뮤니티

- KPOP에 관하여 서로 소통할 수 있는 커뮤니티입니다.
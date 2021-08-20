### 1. 프로젝트 개발 환경

- JDK 1.8
- nginx 1.18.0(Ubuntu)
- spring Boot 2.4.5
- Vue 2
- Intellij IDEA Ultimate 2020.3.1
- VsCode 1.59.0
- MariaDB 10.3.31-MariaDB-0ubuntu0.20.04.1

### 2. 빌드 Setting

- gradle 6.7
  1. sudo apt-install wget unzip # wget,unzip 설치
  2. cd /tmp
  3. wget https://services.gradle.org/distributions/gradle.6.7-bin.zip
  4. sudo mkdir /opt/gradle
  5. sudo unzip -d /opt/gradle gradle+-6.7-bin.zip
  6. export PATH=$PATH:/opt/gradle/gradle-6.7/bin
- openVidu 2.19.0
  1. sudo su
  2. cd /opt
  3. cd openvidu
  4. nano .env
     ![](2021-08-20-10-25-11.png)
  5. 해당 내용으로 .env파일 수정
  6. ./openvidu start
- enginx
  1. sudo vi /etc/nginx/sites-available/default
     ![](2021-08-20-11-27-37.png)
  2. 해당 내용으로 수정

### 3. 사용하는 외부 서비스

- 외부서비스.md

### 4. 데이터베이스

- DATABASE.md

### 5. 시나리오

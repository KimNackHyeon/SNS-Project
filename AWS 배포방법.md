## < AWS 배포방법 >

- ### Vue + Nginx ( Front End )

  1. aws 서버 접속 후 nginx 설치

     ```
     sudo apt-get install nginx
     ```

  2. vscode에서 vue 프로젝트 빌드

     ```
     npm run build
     ```

  3. FileZila를 사용해서 dist 폴더 aws로 옮기기

  4. sites-available 파일 수정
        - vi /etc/nginx/sites-available/default

     ```
     root [dist폴더 경로]
     
     index index.html
     
     location /
     	try_files $uri $uri /index.html;
     ```

  5. nginx 재실행

     ```
     sudo service nginx restart
     ```

  6. 끝

* ### Spring ( Back End )

  1. jar 파일 빌드

     - target 폴더에 jar 파일 생성

     ```
     Run as > Maven install
     ```

  2. FileZila를 사용하여 jar 파일 aws로 옮기기

  3. jar 파일 실행

     ```
     java -jar [jar파일명]
     ```

  4. 끝

* ### MariaDB

  1. aws에서 mariadb 설치

     ```
     sudo apt-get install mariadb
     ```

  2. workbench에서 외부접속 허용 설정


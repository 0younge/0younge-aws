## Lv.0 AWS Budgets 설정

![Budgets Config.png](img/Budgets%20Config.png)

---

## Lv.1 네트워크 구축 및 핵심 기능 배포

EC2 Public IP : 52.78.181.69

health check : http://52.78.181.69:8080/actuator/health

---

## Lv.2 DB 분리 및 보안 연결하기

/actuator/info : http://52.78.181.69:8080/actuator/info

보안 그룹 체이닝 :
![security group inbound.png](img/security%20group%20inbound.png)
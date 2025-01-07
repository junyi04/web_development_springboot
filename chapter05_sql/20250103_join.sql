1. JOIN
2. UNION 
3. subquery

1-1. SQL에서 JOIN은 여러 테이블에서 데이터를 가져와 결합하는 기능
RDBS에서는 데이터릐 중복을 피하고 쉽게 관리하기 위해 데이터를 여러 곳에 나누어 보관
	ex) 어떤 주문을 담당한 직원을 확인하고 싶은데, 주문 아이디 orders 테이블에 있고
		직원 이름은 staff 테이블에 있으면 어떻게 쿼리를 입력해야 하는지 고민
		
	이상에서 이루어지는 데이터 분산을 '데이터 정규화(data normalization)'이라고 하며,
	데이터베이스에서 중복을 최소화하고 데이터의 일관성을 유지하기 위함.
	
-- 	1) JOIN 
-- 		지금까지는 테이블 하나의 데이터를 필터링 등 다루는 연습을 함
-- 		이제는 둘 이상의 테이블을 함께 다루면서 해당 키워드를 통해 합쳐서 보는 연습
-- 		
-- 		실습 데이터를 기준으로
-- 		ex) 국가별로 주문 건수 알아보고 싶다면 -> user와 orders가 필요
-- 		즉, 하나의 테이블만으로 원하는 정보 출력이 불가능
-- 		
-- 		두 개의 데이터 집합을 하나로 결합하는 기능을 지님.
-- 		즉, 기본적으로눈 '서로 다른 두 테이블 간의 공통 부분인 키를 활용'하여 테이블을 합집합
	
-- user와 orders를 하나로 결합하여 출력 (단, 주문 정보가 있는 회원의 정보만 출력)
	
SELECT *
	FROM users u INNER JOIN orders o ON u.id = o.user_id
	;

-- 이상의 SQL문에 대한 해석
-- 기존에 from 다음에는 테이블 명 하나만 작성되었지만, 이제는 join 연산을 위한
-- 추가 문법이 적용
-- 회원 정보와 무문 정보를 하나로 결합하기 위해 users와 orders를 INNER JOIN(추후 설명)으로
-- 묶고, '후속 조건'으로 "주문 정보가 있는 회원의 정보만 출력하기 위해" u.id = o.user_id를 사용

-- users PK인 id는 회원 id에 해당
-- orders에 PK인 id에 해당하고, 2번째 컬럼인 user_id는
-- orders에서는 PK는 아니지만 JOIN을 수행할 때 users와 합치는 조건이 됨
run-mariadb:
	echo Starting MariaDB container...
	docker run -d --rm \
		--name mariadb \
		-e MARIADB_ROOT_PASSWORD=Thakur@7 \
		-e MARIADB_DATABASE=scm \
		-p 3306:3306 \
		mariadb:latest

wait-for-db:
	echo Waiting for MariaDB to be ready...
	docker run --rm --link mariadb:db busybox sh -c 'until nc -z -v -w30 db 3306; do echo "Waiting for DB"; sleep 1; done; echo "MariaDB is up"'

run-app: wait-for-db
	./mvnw spring-boot:run
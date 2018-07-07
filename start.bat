cd ./back/business
call mvn clean package
start /min java -jar ./target/business-1.0-SNAPSHOT.jar
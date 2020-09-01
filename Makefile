.PHONY: all
all: clean build

.PHONY: clean
clean:
	mvn clean
	rm -f ./*.jar

.PHONY: build
build:
	mvn install
	cp ./target/Battleships-1.0-SNAPSHOT.jar ./battleships.jar



create-packages:
	mvn -B package --file ./pom1.15.1.xml
	mvn -B package --file ./pom1.15.2.xml
	mvn -B package --file ./pom1.16.1.xml
	mvn -B package --file ./pom1.16.2.xml
	mvn -B package --file ./pom1.16.3.xml

	make move-package

move-package:
	mv -f ./target/Raijin-1.15.1*.jar ./packagefiles/Raijin-1.15.1-latest.jar
	mv -f ./target/Raijin-1.15.2*.jar ./packagefiles/Raijin-1.15.2-latest.jar
	mv -f ./target/Raijin-1.16.1*.jar ./packagefiles/Raijin-1.16.1-latest.jar
	mv -f ./target/Raijin-1.16.2*.jar ./packagefiles/Raijin-1.16.2-latest.jar
	mv -f ./target/Raijin-1.16.3*.jar ./packagefiles/Raijin-1.16.3-latest.jar

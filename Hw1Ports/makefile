connect:
	find -name "*.java" > sources.txt

build:
	javac -d build @sources.txt

run:
	java -cp build Main.Main

delete:
	rm -rf build sources.txt
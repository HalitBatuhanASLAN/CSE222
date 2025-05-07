# Makefile for Java Project
# This Makefile automates the build and run process for the Java application

# connect: Creates a list of all Java source files in the project
collect:
	find -name "*.java" > sources.txt

# build: Compiles all Java source files listed in sources.txt into the build directory
build:
	javac -d build @sources.txt

# run: Executes the compiled Java program with the configuration file as input
run:
	java -cp build SpellChecker.SpellChecker $(ARGS) 2>errors.log

# javadoc: Generates JavaDoc documentation for all Java source files in the project
javadoc:
	mkdir -p docs
	javadoc -d docs @sources.txt -encoding UTF-8 -charset UTF-8 -docencoding UTF-8

# delete: Cleans up build artifacts and generated files
delete:
	rm -rf build sources.txt docs errors.log
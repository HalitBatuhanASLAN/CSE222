# Makefile for Java Project
# This Makefile automates the build and run process for the Java application

# connect: Creates a list of all Java source files in the project
connect:
	find -name "*.java" > sources.txt

# build: Compiles all Java source files listed in sources.txt into the build directory
build:
	javac -d build @sources.txt

# run: Executes the compiled Java program with the configuration file as input
run:
	java -cp build Main.Main configuration.txt 2> errors.log


# delete: Cleans up build artifacts and generated files
delete:
	rm -rf build sources.txt errors.log
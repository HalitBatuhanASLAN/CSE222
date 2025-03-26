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
#	mkdir -p logs
#	java -cp build Main.Main configuration.txt 2> errors.log


#	java -cp build Main.Main $(ARGS) < scenraio1.txt > output1.txt 2>errors.log
	java -cp build Main.Main $(ARGS) 2>errors.log

# delete: Cleans up build artifacts and generated files
delete:
	rm -rf build sources.txt errors.log logs
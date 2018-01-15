#Makefile

compile: bin
	javac -cp src -d bin src/*.java

run:
	java -cp bin Run

bin:
	mkdir bin
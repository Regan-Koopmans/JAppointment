main:
	javac -Werror *.java 

clean:
	rm *.class

run: main 
	java Diary

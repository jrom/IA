JavaPackages = IA
JavaLibraries = AIMA.jar
JavaMainClass = IA.Main

all:
	javac -cp $(JavaLibraries) $(JavaPackages)/*.java
	
run:
	java -cp $(JavaLibraries):. $(JavaMainClass)
	
clean:
	rm $(JavaPackages)/*.class

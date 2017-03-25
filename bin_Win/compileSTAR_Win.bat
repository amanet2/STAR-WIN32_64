path="c:\Program Files\Java\jdk1.8.0_45\bin"
cd ..\src
g++ *.h *.cpp -o star
move star.exe ..\bin
cd ..\starJavaGUI_Windows\src
javac *.java
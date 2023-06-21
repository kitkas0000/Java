echo expected results > outs1.txt
javac *.java
echo test case 1 >>outs1.txt
java Main 1 1 1
more  f1.txt >> outs1.txt
echo test case 2 >>outs1.txt
java Main 2 5 1
more  f2.txt >> outs1.txt

echo test case 3 >>outs1.txt
java Main 3 9 1
more  f3.txt >> outs1.txt

echo test case 4 >>outs1.txt
java Main 4 13 1
more  f4.txt >> outs1.txt

echo test case 5 >>outs1.txt
java Main 5 17 1
more  f5.txt >> outs1.txt

echo test case 6 >>outs1.txt
java Main 6 21 1
more  f6.txt >> outs1.txt

echo test case 7 >>outs1.txt
java Main 7 25 1
more  f7.txt >> outs1.txt




# Coursera-Duke-Java

These are projects from the "Java Programming: Arrays, Lists, and Structured Data" course by Duke University on Coursera. The code uses `edu.duke` package, a package specially created for the course. Its documentation can be found [here](http://www.dukelearntoprogram.com/course2/doc/javadoc/overview-summary.html).

* CaesarCipher: a program that encrypts a string by shifting all the letters in the string by certain number of places along the alphabet. The folder also contains a Caesar cipher breaker.
* CommonWords: count common words in Shakespeare's works.
* GladLib: random story generator.
* VigenereCipher: encrypts a string by using a series of Caesar ciphers based on the positions of the letters. For example, a Vigenere cipher with key `[1, 2, 3]` will shift string `"abc def"` to `"bdf egi"`. The folder also contains a Vigenere breaker that breaks the cipher when key length is known. The breaker cracks the Caesar ciphers by matching the most common character appeared in the message to the most common character used in a certain language. Therefore this breaker only works on large strings.

huffman-encoding
================

A java program to compress files using huffman encoding.

Implementation of variants of Huffman encoding, a lossless data compression algo-rithm that is used in encoding schemes such as JPEG and MP3 (MPEG-1).

<p align="center">
  <img src="https://github.com/m2omou/huffman-encoding/raw/master/samples/hmencoding.png" />
</p>

###Encode###

Takes in a file name, compresses the file, and outputs the compressed file with a codemap header.

####Usage:####
```java
java Zipper zipper [target] [destination]
```

- Target: The name of the file, folder to be compressed
- Destination: The name of the output file

###Decode###

Decoding reverse the `encode` operation. Given any valid output file from encode, decode
should reproduce the original file.

####Usage:####
```java
java Zipper unzipper [target] [destination]
```

- Target: The name of the file, folder to be decompressed
- Destination: The name of the decompressed file (to be created)

 

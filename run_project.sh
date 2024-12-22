#!/bin/bash

SRC_DIR="src/main/java"
OUT_DIR="out"

mkdir -p $OUT_DIR

echo "Kompilowanie plików Java..."
javac -d $OUT_DIR $SRC_DIR/*.java
if [ $? -ne 0 ]; then
    echo "Błąd podczas kompilacji!"
    exit 1
fi
echo "Kompilacja zakończona pomyślnie."

echo "Uruchamianie programu..."
java -cp $OUT_DIR Main
if [ $? -ne 0 ]; then
    echo "Błąd podczas uruchamiania programu!"
    exit 1
fi
echo "Program zakończył działanie."
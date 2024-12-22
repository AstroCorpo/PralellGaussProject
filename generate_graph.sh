#!/bin/bash

DOT_FILE="src/main/resources/dot/graph.dot"
PNG_FILE="src/main/resources/png/graph.png"

if [ -f "$DOT_FILE" ]; then
    echo "Generowanie grafu PNG z pliku DOT..."
    mkdir -p "$(dirname $PNG_FILE)"
    dot -Tpng "$DOT_FILE" -o "$PNG_FILE"
    if [ $? -ne 0 ]; then
        echo "Błąd podczas generowania pliku PNG!"
        exit 1
    fi
    echo "Graf został wygenerowany: $PNG_FILE"
else
    echo "Plik DOT nie istnieje: $DOT_FILE"
    exit 1
fi
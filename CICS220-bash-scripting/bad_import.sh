#!/bin/sh

echo "Script got the following command line arguments"
echo "$@"

n=1
for arg in "$@"; do
    echo "\$${n} : ${arg}"
    (( n++ ))
done
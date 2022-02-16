#!/bin/sh

if [ $# == 0 ] ; then
    echo "Script got the following command line arguments"
    exit 1
fi

echo "$0" "$@"

echo Files that import a class from java.lang:
for arg in "$@"; do
    name=$(grep -E "^[^//]*java.lang[^.]*.[^.]*$" "${arg}")
    if [ -n "$name" ] ; then
        echo $arg
    fi
done 

echo \\nFiles that do not import a class from java.lang:
for arg in "$@"; do
    name=$(grep -E "^[^//]*java.lang[^.]*.[^.]*$" "${arg}")
    if [ ! -n "$name" ] ; then
        echo $arg
    fi
done 


# i=0
# n=0
# import=()
# noimport=()

# for arg in "$@"; do
#     name=$(grep -E "^[^//]*java.lang[^.]*.[^.]*$" "${arg}")
#     if [ -n "$name" ] ; then
#         (( i++ ))
#         import[i]=$arg

#     else
#         (( n++ ))
#         noimport[n]=$arg
#     fi

# done

# echo Files that import a class from java.lang:

# for file in ${import[@]}
# do
#     echo $file
# done

# echo Files that do not import a class from java.lang:

# for file in ${noimport[@]}
# do
#     echo $file
# done

#!/bin/sh

echoerr() { echo "$@" 1>&2; }


if [ $# != 1 ] ; then
    exit 1
fi

input=$*


IFS='.'
read -ra ADDR <<<"$input"

for c in "${ADDR[@]}"
do
    ./is_valid_pkg.sh $c
    if [ $? != 0 ] ; then
    echoerr $c is not a valid package name
        exit 2
    fi
done


for c in "${ADDR[@]}"
do
    mkdir $c
    cd $c
done
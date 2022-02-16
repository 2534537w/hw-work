#!/bin/sh

echoerr() { echo "$@" 1>&2; }

if [ $# == 0 ] ; then
    echo $RANDOM

elif [ $# == 1 ] ; then
    echo $(($RANDOM % $1))

elif [ $# == 2 ] ; then
    l=$1
    r=$2
    m=$(($r - $l))
    echo $(($l + $RANDOM % $m))
else
    echoerr "$0 [0] [1] [2] numbers"
    exit 1;
fi
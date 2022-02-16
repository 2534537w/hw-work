#!/bin/sh
echoerr() { echo "$@" 1>&2; }

trash_dir=${HOME}"/.trashcan"

# regular file
if [ -f $trash_dir ];then
    echoerr $trash_dir already exists and is a regular file
    exit 1
fi

# directory 
if [ -d $trash_dir ];then
    echoerr $trash_dir already exists and is a directory
    exit 2
fi

mkdir $trash_dir
chmod 700 $trash_dir

time=$(date "+%a %b %H:%M:%S %Z %Y : ")
echo $time  $trash_dir "created successfully" > "$trash_dir/log"

filename=`mktemp`
mv $filename $trash_dir

time=$(date "+%a %b %H:%M:%S %Z %Y : ")
echo $time $filename "added to trash" >> "$trash_dir/log"

#!/bin/sh

# checkFirst(){
#     c=$1
#     if [[ $c = [[:lower:]] ]] ; then
#         # echo "first" $c is lower
#         return 1
#     fi
#     return 0
# }

# checkSecond(){
#     c=$1
#     flag=0
#     if [[ $c = [[:lower:]] ]] ; then
#         # echo $c is lower
#         flag=1
#     fi

#     if [[ $c = [[:digit:]] ]] ; then
#         # echo $c is digit
#         flag=1
#     fi

#     if [ "$c" == "_" ];then
#         # echo $c is _
#         flag=1
#     fi

#     if [ $flag != 1 ] ; then
#         return 0
#     fi
#     return 1
# }

# checkAll(){
#     input=$*
#     first=${input:0:1}
#     second=${input:1}

#     checkFirst $first
#     if [ $? != 1 ] ; then
#         # echo check first failed $first
#         return 0
#     fi

#     for i in `seq ${#second}`
#     do
#         c=${second:i-1:1}
#         checkSecond $c
#         if [ $? != 1 ] ; then
#             return 0
#         fi
#     done

#     return 1
# }

if [ $# != 1 ] ; then
    exit 2
fi

str=$*

if echo "${str}" | grep -q '^[a-z][a-z0-9_]*$'; then
    exit 0
else
    exit 1
fi


# checkAll $*

# if [ $? != 1 ] ; then
#     exit 1
# fi

#!/bin/sh

# run it in terminal

f=0x4B
r=$[ $f*2+1 ]
m=$[ $r*0x2f+0x8F ]
e=$[ $m-0x4D7]

echo $r-$m-$e

input="SSBhbSAidGVhY2hlciIsIHRoaXMgaXMgbXkgd2VjaGF0LCB5b3UgY2FuIGFkZCBtZSwgdGhlbiB3
ZSBjb21taW5pY2F0ZSBkaXJlY3RseSwgc2tpcCBhZ2VudC4gc28geW91IGNhbiBzYXZlIG1vbmV5
LiBiZWNhdXNlIGFnZW50IG1ha2UgYSBsb3Qgb2YgbW9uZXkuIGRvIG5vdCB0byBzZW50IGl0IHRv
IGFnZW50Cg==" 

if [ "$(uname)"=="Darwin" ] ; then
    echo $input | base64 -D
elif ["$(expr substr $(uname -s) 1 5)"=="Linux"] ; then
    echo $input | base64 -d
fi
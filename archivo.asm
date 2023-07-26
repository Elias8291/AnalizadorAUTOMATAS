.model small
.data
a dw 2
b dw 1
temp1 dw ?
r dw temp1
.code
mov ax,@data
mov ds,ax
mov ax, a
sub ax, b
mov temp1, ax
mov r, ax
mov dl, 0Dh
mov ah, 02h
int 21h
mov dl, 0Ah
mov ah, 02h
int 21h
mov ax, r
add ax, 30h
mov dl, al
mov ah, 02h
int 21h
mov ah,4ch
int 21H
end

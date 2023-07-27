.model small
.data
a dw 11
b dw 2
s dw temp1
temp1 dw ?
.code
mov ax,@data
mov ds,ax
mov cx, 10 ; divisor
mov ax, a
add ax, b
mov temp1,ax
mov s, ax
print_num macro num
mov ax, num
xor dx, dx ; limpia dx para la operación div
mov cx, 10
div cx ; ax = ax / cx, dx = ax % cx
add dx, 30h ; convierte el dígito a ASCII
push dx ; guarda el dígito en la pila
add ax, 30h ; convierte el dígito a ASCII
mov dl, al
mov ah, 02h
int 21h
pop dx ; recupera el dígito de la pila
mov dl, dl
mov ah, 02h
int 21h
endm
print_num s
mov dl, 0Dh
mov ah, 02h
int 21h
mov dl, 0Ah
mov ah, 02h
int 21h
mov s, ax
mov ah,4ch
int 21H
end

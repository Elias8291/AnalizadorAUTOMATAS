.model small
.data
a dw 10
b dw 2
temp1 dw ?
d dw temp1
.code
mov ax,@data
mov ds,ax
mov cx, 10 ; divisor
mov ax, a
sub ax, b
mov temp1, ax
mov d, ax
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
print_num d
mov dl, 0Dh
mov ah, 02h
int 21h
mov dl, 0Ah
mov ah, 02h
int 21h
mov ah,4ch
int 21H
end

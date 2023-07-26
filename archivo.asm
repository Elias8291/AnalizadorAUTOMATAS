.model small
.data
a dw ?
b dw 5
c dw temp1
temp1 dw ?
mensaje0 db "Ejemplo de suma",10,13, '$'
mensaje1 db "a=",10,13, '$'
mensaje2 db "b=",10,13, '$'
mensaje3 db "c=a+b",10,13, '$'
.code
mov ax,@data
mov ds,ax
mov cx, 10 ; divisor
mov ax, a
add ax, b
mov temp1, ax
mov d, ax
lea dx, mensaje0
mov ah, 09h
int 21h
lea dx, mensaje1
mov ah, 09h
int 21h
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
print_num a
mov dl, 0Dh
mov ah, 02h
int 21h
mov dl, 0Ah
mov ah, 02h
int 21h
lea dx, mensaje2
mov ah, 09h
int 21h
print_num b
mov dl, 0Dh
mov ah, 02h
int 21h
mov dl, 0Ah
mov ah, 02h
int 21h
lea dx, mensaje3
mov ah, 09h
int 21h
print_num c
mov dl, 0Dh
mov ah, 02h
int 21h
mov dl, 0Ah
mov ah, 02h
int 21h
mov ah,4ch
int 21H
end

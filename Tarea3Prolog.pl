sub_conjunto(_,[]).
sub_conjunto([],_).
sub_conjunto([X|Xs], Ys):-
    member(X,Ys).
    sub_conjunto(Xs,Ys).



aplanar([], []).
aplanar([Cabeza|Cola], Lista) :-
    aplanar(Cabeza, Z),
    aplanar(Cola, Zs),
    append(Z, Zs, Lista).

aplanar([X|Xs], [X|Ys]) :-
    aplanar(Xs, Ys).

convertirCadenaALista(Cadena,Lista) :-




% Predicado para calcular la distancia de Hamming entre dos cadenas
distanciaH(Cadena1, Cadena2, Distancia) :-
    atom_chars(Cadena1, Lista1),    % Convertir la cadena1 en una lista de caracteres
    atom_chars(Cadena2, Lista2),    % Convertir la cadena2 en una lista de caracteres
    calcularDistancia(Lista1, Lista2, Distancia). % Llamar al predicado auxiliar

% Caso base: si ambas listas están vacías, la distancia es 0
calcularDistancia([], [], 0).
% Si la primera lista está vacía, la distancia es la longitud de la segunda lista
calcularDistancia([], [_|Ys], Distancia) :-
    length(Ys, Distancia).
% Si la segunda lista está vacía, la distancia es la longitud de la primera lista
calcularDistancia([_|Xs], [], Distancia) :-
    length(Xs, Distancia).
% Si las listas tienen al menos un elemento, comparar el primer elemento y continuar recursivamente
calcularDistancia([X|Xs], [Y|Ys], Distancia) :-
    (X \= Y ->  % Si los elementos son diferentes
        calcularDistancia(Xs, Ys, DistanciaRestante), % Continuar recursivamente
        Distancia is DistanciaRestante + 1; % Incrementar la distancia
        calcularDistancia(Xs, Ys, Distancia) % Si los elementos son iguales, continuar sin incrementar la distancia
    ).

# 7507-AlgoChess  &middot; [![Build Status](https://travis-ci.org/moreover22/N5_AlgoChess.svg?branch=master)](https://travis-ci.org/moreover22/N5_AlgoChess) [![codecov](https://codecov.io/gh/moreover22/N5_AlgoChess/branch/master/graph/badge.svg)](https://codecov.io/gh/moreover22/N5_AlgoChess) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/5309252d73654dfbb4421de8c9c62c39)](https://www.codacy.com/manual/moreover22/AlgoChess?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=moreover22/AlgoChess&amp;utm_campaign=Badge_Grade)  


Trabajo práctico número 2 de Algoritmos y programación 3  
FIUBA - 2° Cuatrimestre 2019  
[Enunciado](https://docs.google.com/document/d/185YqJdFQC_HE0C7EJxEAoM8utWJHCoblqlk21OnDSVs/edit#heading=h.b1xca0tuq21p)

### Integrantes:  

|Padrón|Nombre|Email|
|------|------|-------|
|102223|Franco ...|f@...|
|102914|Agustín More|amore@fi.uba.ar|
|102692|Franco ...|f@...|
|102165|Franco ...|f@...|

## Pasos para trabajar:
### Unica vez
`git clone https://github.com/moreover22/N5_AlgoChess.git`  
`cd N5_AlgoChess`  
`apt install ant`  
`ant`  

### Cuando se hace un cambio
1. `ant test`
1. `git pull`
1. `git checkout -b NOMBRE-BRANCH`
1. `git status`
1. `git add .`
1. `git commit -m "Comentario"`
1. `git push` 
1. Confirmar que el build paso las pruebas de [Travis](https://travis-ci.org/moreover22/N5_AlgoChess) y la calidad de código en [Codacy](https://app.codacy.com/manual/moreover22/N5_AlgoChess).
1. Solicitar merge desde github.

### Para JavaFX:
`apt install openjdk-8-jdk`  
`export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64`  

## Progreso entregas
### Entrega 1 (jueves 7 de Noviembre)
#### Pruebas entidades:
- [ ] 1. Una Unidad movible se puede mover en todas las direcciones.
- [ ] 2. Una Unidad movible no puede moverse a un casillero ocupado.
- [ ] 3. Un Soldado de infantería aliado ataca a una pieza enemiga y se verifica que se resta la vida correspondiente.
- [ ] 4. Un Jinete aliado ataca a una pieza enemiga y se verifica que se resta la vida correspondiente.
- [ ] 5. Una catapulta aliada ataca a una pieza enemiga y se verifica que se resta la vida correspondiente.
- [ ] 6. Un Curandero aliado cura a una pieza aliada y se verifica que se suma la vida correspondiente.
#### Pruebas tablero:
- [ ] 7. Se coloca una pieza aliada en un casillero del sector aliado vacío con éxito.
- [ ] 8. Se verifica que no se puede colocar una pieza aliada en un casillero del sector aliado ocupado.
- [ ] 9. Se verifica que no se puede colocar una pieza aliada en un casillero del sector enemigo.
- [ ] 10. Correcta creación e inicialización del tablero.
#### Pruebas Jugador:
- [ ] 11. Se verifica que no puede tomar más entidades de lo que sus puntos le permiten.
- [ ] 12. Se verifica que el jugador que se queda sin entidades, es el perdedor.

### Entrega 2 (jueves 21 de noviembre)
#### Soldado:
- [ ] 13. Se verifica que 3 soldados contiguos pueden moverse al mismo tiempo en la misma dirección con una sola acción.
- [ ] 14. Teniendo 3 soldados contiguos, y un obstáculo (una entidad distinta a los otros dos soldados) obstruyendo a uno de los 3, se verifica que al mover el Batallón, se mueven 2 soldados y uno se queda quieto.
- [ ] 15. (En la situación anterior) Se verifica que el Batallón se disuelve, al quedar separados los 3 soldados.
- [ ] 16. Habiendo 4 soldados contiguos, se verifica que al mover un Batallón se mueven únicamente 3.
#### Jinete:
- [ ] 17. Un jinete sin aliados en distancia corta y un enemigo en distancia corta, ataca con su espada al enemigo y se verifica que se realiza correctamente el ataque.
- [ ] 18. Un jinete sin aliados en distancia corta y un enemigo en distancia corta y otro enemigo en distancia media, trata de atacar al enemigo en distancia media y se verifica que no se puede realizar el ataque.

### Entrega 3 (jueves 28 de noviembre)
Interfaz gráfica inicial básica: comienzo del juego y visualización del tablero e interfaz de usuario básica.
Modelo del manejo de turnos en el juego.

### Entrega 4 - Final: (jueves 5 de diciembre)
Trabajo Práctico completo funcionando, con interfaz gráfica final, sonidos e informe completo.


🐍 Classic Snake Game (Java Desktop)
Este proyecto es una recreación del clásico juego de la serpiente, desarrollado íntegramente en Java. Más allá de ser un juego, este desarrollo fue un desafío de lógica algorítmica y manejo de eventos en tiempo real, donde la precisión del código define la experiencia del usuario.

📋 ¿Qué hace este proyecto?
El sistema gestiona el ciclo de vida completo de un juego dinámico:

Motor de Movimiento: Algoritmo que calcula la posición de la serpiente en una grilla de coordenadas, actualizando el estado de cada segmento en milisegundos.

Detección de Colisiones: Sistema de validación lógica para detectar impactos contra las paredes, contra el propio cuerpo de la serpiente o el contacto con los "alimentos".

Generación Aleatoria: Lógica para posicionar items en el mapa asegurando que no se superpongan con el cuerpo del jugador.

Gestión de Puntuación: Registro y actualización del score en tiempo real a medida que la dificultad (velocidad) aumenta.

Control por Teclado: Implementación de Listeners para una respuesta inmediata a los comandos del usuario.

🛠️ Tecnologías y Conceptos Aplicados
Lenguaje: Java (Core).

Interfaz Gráfica: Java Swing / AWT (Manejo de gráficos y ventanas).

Programación Orientada a Objetos (POO): Uso de clases para modelar la serpiente, el mapa y la lógica del juego.

Estructuras de Datos: Manejo de listas y arreglos para representar el cuerpo dinámico de la serpiente.

Manejo de Hilos (Threads): Control del Timer para el renderizado y la actualización de la lógica de manera fluida.

--------------------------------------------------------

¿Cómo jugarlo?
1. Clonar el repositorio.

2. Ejecutar la clase principal desde cualquier IDE de Java.

3. Usar las flechas del teclado para controlar a la serpiente.

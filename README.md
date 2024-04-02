# AREP-PARCIAL2

## Correrlo en EC2.

1. Clona el repositorio en tres o más intancias de EC2:
    ```
    git clone https://github.com/AlexisGR117/AREP-PARCIAL2.git
    ```
2. En cada instancia de EC2 navega a la carpeta del proyecto:
    ```
    cd AREP-PARCIAL2
    ```
3. En cada instancia de EC2 construye el proyecto usando Maven:
    ```
    mvn clean install
    ```
4. En la instancia que se encargará del proxy configura las variables de entorno con el siguiente comando, ten en cuenta que \<services> son las URLs de las demás intancias que se encargarán de los servicios MathServices separados por comas:
    ```
    export MATH_SERVICES=<services>
    ```
   ejemplo del uso del comando:
    ```
    export MATH_SERVICES=http://ec2-44-202-48-248.compute-1.amazonaws.com:4567,http://ec2-44-211-250-174.compute-1.amazonaws.com:4567
    ```
5. Ejecuta ServiceProxy en la instancia que se encargará del proxy:
    ```
    java -cp target/AREP-PARCIAL2-1.0-SNAPSHOT.jar edu.eci.arep.ServiceProxy
    ```
6. Ejecuta MathServices en las demás intancias que creaste:
    ```
    java -cp target/AREP-PARCIAL2-1.0-SNAPSHOT.jar edu.eci.arep.MathServices
    ```
7. Abre un navegador web y accede a la aplicación con la url de la instancia que se encarga del proxy por ejemplo, debe usarse el puerto 4568 y se la ruta será /math.html:

    http://ec2-34-230-71-66.compute-1.amazonaws.com:4568/math.html.

## Pruebas de funcionalidad

Se accede a la aplicación por medio de la url de la instancia que se encarga del proxy, teniendo en cuenta que el puerto es el 4568 y la ruta /math.html.

![1.PNG](img/1.PNG)

Se ingresa el número que se quiere calcular los factores, en este caso 112 y se oprime el botón "Submit". La respuesta a esta petición debe ser un JSON con la información de la operación.

![2.PNG](img/2.PNG)

Se revisa la instancia del proxy para ver que se haya hecho correctamente la petición a un MathService y que la respuesta de este sea un JSON con la información de la operación.

![4.PNG](img/4.PNG)

Ahora calculamos los números primos que hay desde 1 hasta 100. La respuesta a esta petición debe ser un JSON con la información de la operación.

![5.PNG](img/5.PNG)

Se vuelve a revisar la instancia del proxy, esta vez la petición se debe realizar a otro servicio totalmente diferente al de la anterior petición y de igual forma se debe obtener como respuesta un JSON con la información de la operación.

![6.PNG](img/6.PNG)

Para verificar el algoritmo de round-robin volvemos a calcular los números primos que hay hasta el número 15.

![8.PNG](img/8.PNG)

En la instancia de proxy se podrá ver que la petición cambio de servicio, esta vez al primero que se había usado para calcular los factores de 112.

![9.PNG](img/9.PNG)

## Video

https://pruebacorreoescuelaingeduco.sharepoint.com/:v:/s/PruebaC/EQoUhspB-qZNpDqhbbCMveAB8DKeIrZ5ub17x9EYWfoXUQ?e=3pIW2I&nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZy1MaW5rIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXcifX0%3D
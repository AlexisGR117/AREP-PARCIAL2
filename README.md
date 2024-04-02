# AREP-PARCIAL2

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
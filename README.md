[COJ](http://coj.uci.cu/) - Caribbean Online Judge
==================================================

El Juez en Línea Caribeño...

## Manual de Instalación

The COJ is configured as a multimodule git/maven project, each maven module is in a different respository. Use --recursive parameter to download the entire COJ project.

git clone --recursive https://github.com/vallejolan/COJ.git

This is going to download main project and all submodules like:
https://github.com/dovier/coj-web.git
https://github.com/dovier/coj-engine.git
https://github.com/vallejolan/PlagiCOJ.git

Or look for include submodules option in your git client.

We use a postgresql to store our relational data, please get postgresql installed and create an empty database named coj.

go to /COJ/coj-web/src/main/resources/cu/uci/coj/config/config.properties
and set this values according to your system configuration:
db.url=jdbc:postgresql://localhost:5432/coj
db.user=postgres
db.password=postgres

Necessarily tables and data are going to be automatically created.

...
### Hacer....

    asdfasd
	asdgas
	asdga
	sdg
	as
	dg
	asdg

### Configuraciones extras

* Recordar configurar...
* También configurar....


### Compruebe...

	/usr/bin/gcc

## Programación de ...

Un COJ

### Lista de parámetros

* 1-Fichero de entrada.
* 2-Solución prototipo.
* 3-Solución a evaluar.

### Configuración

La configuración

	coj.coj/confo.ejemplo
    
El archivo de configuración...

	algo.ejemplo

En este caso ....

### Evaluación(Código)

* Accepted(200).
* Wrong Answer(201).

### Evaluación e....

La evaluación debe emitirse...

	return 200;//Accepted

### Evaluación a la salida estándar de error 

ejemplo

El COJ

	fprintf(stderr,"Wrong Answer")

## Contáctenos

Preguntas o comentarios a través del mecanismo establecido en el Juez en Línea Caribeño, [Contáctenos](http://coj.uci.cu/general/contact.xhtml)
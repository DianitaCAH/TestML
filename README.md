# TestML

#ML Android Candidate

Desarrollar una app que utilice las APIs de Mercadolibre, con dos secciones: 
● Search: buscador de productos y listado de respuestas. 
● Product Page: detalle de un producto (al cual se debería poder acceder al tappear uno 
de los items en el resultado del search). 
Los endpoints necesarios los encontrarás en: 
http://developers.mercadolibre.com/items-and-searches/ 


#Justificación 

Al momento de desarrollar el ejercicio planteado, una app Android en la que se pueda buscar/ consultar productos y acceder al detalle de los mismos, me propuse desarrollarlo implementando la nueva arquitectura propuesta por Google para el desarrollo de aplicaciones para la plataforma Android MVVM, por sus siglas en inglés Model - View - ViewModel (Modelo - Vista - Modelo Vista). 

Esto debido a que en este modelo de arquitectura o patrón, se propone el manejo por separado de la vista al de la lógica de negocio así como también plantea la declaración de observables.

Básicamente decidí aplicar esta estructura en esta prueba debido a la manera relativamente fácil en que puedes manejar la data en general sin tener que hacer grandes cambios en la vista o en la lógica en su defecto y por sobretodo porque no había tenido la oportunidad de aplicarlo anteriormente por lo que fue una especie de reto personal.

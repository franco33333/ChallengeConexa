# Aplicacion para proceso de seleccion de Conexa
## Descripcion
La aplicacion consiste en un Home donde se muestran dos Fragments. El primero muestra un listado de Posts con un buscador que filtra los Posts a mostrar por Titulo y Contenido de los mismos.
El segundo fragment muestra el listado de Users, cada uno con algunos datos de los mismos y con un boton para abrir la localizacion de los mismos en el SDK de Maps.
Luego existe la pantalla del detalle de un Post que se accede haciendo click en alguno. En esta se muestra la imagen del mismo, el titulo, contenido y la ultima fecha de actualizacion.
La ultima pantalla que existe es la pantalla del mapa donde se muestra la localizacion de un usuario con el SDK de Maps.

## Decisiones resolucion del challenge
* Si bien el challenge pedia 4 pantallas, termine creando 3 pantallas, donde en la pantalla Home muestro dos fragments, una para cada listado. Preferi usar una sola pantalla para ambas porque me parecia que quedaba mejor con la estetica.
* No oculte la api_key del SDK de Maps para facilitar la revision del challenge.
* Se utilizo el patron de arquitectura MVVM para la resolucion del challenge, teniendo un apartado de modelos, donde estan los objetos/datos; Un apartado de la vista con las activities y los fragments; y un apartado para los ViewModel de cada vista, donde se hacen los procesamientos o comunicacion con las Apis.
* Se utilizo ViewBinding para facilitar el acceso a los elementos de las vistas.
* Se utilizo Glide para el manejo de imagenes y su insercion en elementos de vista.
* Para los viewModel se utilizaron LiveData con Observers del lado de la vista para la comunicacion de datos entre ambas.
* Para el manejo de Apis se utilizo Retrofit2.
* La app esta diseñada para que soporte rotacion de pantalla.
* La app no soporta el cambio de modo Claro u Oscuro. Fue diseñada pensada como un Modo oscuro por defecto.

## Consideraciones
Se utilizo la version de Android Studio Koala | 2024.1.1 - June 10, 2024.

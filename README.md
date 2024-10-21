<h1 align="center">Eventos del Ciclo de Vida en Hibernate</h1>
<p>Los eventos del ciclo de vida en Hibernate son métodos especiales que se ejecutan automáticamente en respuesta a ciertas acciones realizadas en las entidades. Permiten ejecutar lógica personalizada en diferentes momentos del ciclo de vida de una entidad (como antes o después de guardarla, actualizarla o eliminarla). Estos eventos son útiles para gestionar tareas automáticas, como la validación de datos, el registro de auditoría o la inicialización de propiedades.</p>
<p>Anotaciones:</p>

- `@PrePersist`: Se ejecuta antes de que la entidad se guarde en la base de datos por primera vez. Se usa para inicializar o validar datos antes de que se inserte.
- `@PostPersist`: Se ejecuta después de que la entidad ha sido guardada en la base de datos. Útil para acciones que deben ocurrir justo después de la inserción, como la actualización de datos relacionados.
- `@PreUpdate`: Se ejecuta antes de que se realice una actualización en la entidad. Permite modificar o validar campos antes de que se aplique el cambio.
- `@PostUpdate`: Se ejecuta después de que la entidad ha sido actualizada. Se usa para acciones posteriores al cambio, como el registro de auditoría.
- `@PreRemove`: Se ejecuta antes de que la entidad se elimine de la base de datos. Ideal para verificar condiciones antes de la eliminación.
- `@PostRemove`: Se ejecuta después de que la entidad ha sido eliminada. Sirve para tareas de limpieza o actualización de registros asociados.

<h2>Anotaciones @Embeddable y @Embedded</h2>
<p>Las anotaciones @Embeddable y @Embedded en Hibernate (y JPA en general) se utilizan para trabajar con objetos embebidos en entidades, lo que permite modelar clases que no representan entidades independientes, sino que forman parte de otra entidad principal. Esto es útil para evitar la repetición de atributos y para organizar mejor el código.</p>

- `@Embeddable`
La anotación `@Embeddable` se aplica a una clase que se va a utilizar como un componente embebido en una entidad. Indica que la clase puede ser "embebida" en otra entidad y que sus atributos se mapearán en la misma tabla que la entidad contenedora. Es una forma de agrupar atributos relacionados en una sola clase.

- `@Embedded`
La anotación `@Embedded` se usa en la clase entidad para indicar que un atributo es un objeto de una clase `@Embeddable`. Los atributos de la clase embebida se mapearán como columnas de la misma tabla que la entidad principal.

<h1 align="center">Eventos del Ciclo de Vida en Hibernate</h1>
<p>Los eventos del ciclo de vida en Hibernate son métodos especiales que se ejecutan automáticamente en respuesta a ciertas acciones realizadas en las entidades. Permiten ejecutar lógica personalizada en diferentes momentos del ciclo de vida de una entidad (como antes o después de guardarla, actualizarla o eliminarla). Estos eventos son útiles para gestionar tareas automáticas, como la validación de datos, el registro de auditoría o la inicialización de propiedades.</p>
<p>Anotaciones:</p>

- `@PrePersist`: Se ejecuta antes de que la entidad se guarde en la base de datos por primera vez. Se usa para inicializar o validar datos antes de que se inserte.
- `@PostPersist`: Se ejecuta después de que la entidad ha sido guardada en la base de datos. Útil para acciones que deben ocurrir justo después de la inserción, como la actualización de datos relacionados.
- `@PreUpdate`: Se ejecuta antes de que se realice una actualización en la entidad. Permite modificar o validar campos antes de que se aplique el cambio.
- `@PostUpdate`: Se ejecuta después de que la entidad ha sido actualizada. Se usa para acciones posteriores al cambio, como el registro de auditoría.
- `@PreRemove`: Se ejecuta antes de que la entidad se elimine de la base de datos. Ideal para verificar condiciones antes de la eliminación.
- `@PostRemove`: Se ejecuta después de que la entidad ha sido eliminada. Sirve para tareas de limpieza o actualización de registros asociados.

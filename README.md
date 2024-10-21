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
se aplica a una clase que se va a utilizar como un componente embebido en una entidad. Indica que la clase puede ser "embebida" en otra entidad y que sus atributos se mapearán en la misma tabla que la entidad contenedora. Es una forma de agrupar atributos relacionados en una sola clase.

```java
@Embeddable
public class Direccion {
    private String calle;
    private String ciudad;
    private String codigoPostal;

    // Getters y setters
}
```

- `@Embedded`
se usa en la clase entidad para indicar que un atributo es un objeto de una clase `@Embeddable`. Los atributos de la clase embebida se mapearán como columnas de la misma tabla que la entidad principal.

```java
@Entity
public class Persona {
    @Id
    private Long id;
    private String nombre;

    @Embedded
    private Direccion direccion; // La clase Direccion es @Embeddable

    // Getters y setters
}
```

<h1 align="center">Generación automática de tablas </h1>
<p>La propiedad jakarta.persistence.schema-generation.database.action con el valor drop-and-create indica que, al iniciar la aplicación, Hibernate eliminará las tablas existentes y las recreará automáticamente a partir de las entidades definidas.</p>
<p>La configuración de drop-and-create en entornos de prueba y desarrollo tiene varias utilidades importantes que facilitan el trabajo de los desarrolladores y el proceso de prueba de las aplicaciones:</p>

- Reinicialización Rápida del Entorno de Pruebas
- Facilita el Desarrollo Ágil
- Automatización de la Carga de Datos Iniciales
- Pruebas de Integración con Datos Conocidos
- Simplificación del Ciclo de Vida del Desarrollo

<h2>persistence.xml</h2>

```xml
<?xml version="1.0" encoding="utf-8" ?>
<persistence xmlns="https://jakarta.ee/xml/ns/persistence" version="3.0">
    <persistence-unit name="ejemploJPA" transaction-type="RESOURCE_LOCAL">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <class>org.CCristian.hibernateapp.entity.Cliente</class>
        <exclude-unlisted-classes>true</exclude-unlisted-classes>
        <properties>

            ...

            <property name="jakarta.persistence.schema-generation.database.action" value="drop-and-create"/>
        </properties>
    </persistence-unit>
</persistence>
```

Para configurar la generación automática de tablas con `drop-and-create` en JPA, el archivo `persistence.xml` está configurado para este propósito. La propiedad `jakarta.persistence.schema-generation.database.action` con el valor `drop-and-create` indica que, al iniciar la aplicación, Hibernate eliminará las tablas existentes y las recreará automáticamente a partir de las entidades definidas.

<h3>Explicación de la Configuración: persistence.xml</h3>

- La propiedad `<property name="jakarta.persistence.schema-generation.database.action" value="drop-and-create"/>` indica a Hibernate que elimine las tablas (si existen) y las recree cada vez que la aplicación se ejecute. Esto es útil en entornos de desarrollo o pruebas donde los cambios en la estructura de la base de datos son frecuentes.
- `<class>org.CCristian.hibernateapp.entity.Cliente</class>` especifica la entidad `Cliente` como una clase mapeada. Es importante que todas las clases de entidad que se deseen incluir estén listadas o que `exclude-unlisted-classes` se configure como false.


<h2>import.sql</h2>

```sql
INSERT INTO clientes (id, nombre, apellido, forma_pago, creado_en, editado_en) VALUES (1,'Cristian','Cristaldo','debito',NULL,NULL), (2,'Andres','Guzman','debito',NULL,NULL), (3,'John','Doe','credito',NULL,NULL), (5,'Pepa','Doe','credito',NULL,NULL), (6,'Nilson','Orrego','paypal',NULL,NULL), (7,'Luna','Garcia','debito',NULL,NULL), (10,'John','Roe','paypal',NULL,NULL), (11,'Lou','Loe','paypal',NULL,NULL), (12,'Lalo','Mena','webpay','2024-10-21 15:42:01','2024-10-21 15:43:24'), (13,'Pia','Perez','paypal plus','2024-10-21 15:57:42','2024-10-21 15:59:02');
```

<h3>Explicación de la Configuración: import.sql</h3>

- El archivo `import.sql` se utiliza para insertar datos iniciales en la base de datos. Al utilizar `drop-and-create`, Hibernate ejecutará este archivo automáticamente después de crear las tablas.
- El contenido del archivo incluye sentencias `INSERT INTO` para agregar registros a la tabla `clientes`.

<h2>Consideraciones</h2>

- La configuración de `drop-and-create` es adecuada para desarrollo, pero no se recomienda para entornos de producción, ya que elimina los datos existentes cada vez que se reinicia la aplicación.
- Para evitar la pérdida de datos en producción, se podría utilizar `create`, `update`, o `validate` como valores de `jakarta.persistence.schema-generation.database.action.`

Con esta configuración, al iniciar la aplicación, Hibernate generará las tablas a partir de la entidad `Cliente`, eliminará las tablas existentes (si las hay), y ejecutará el archivo `import.sql` para insertar los datos especificados.

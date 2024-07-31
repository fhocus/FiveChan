# FiveChan
___

## Integrantes
- Fabricio Jesús Huaquisto Quispe
- Erick Malcoaccha Diaz
- Marko Sumire Ramos
- Christian Taipe Saraza
- Alonso Chullunquia Rosas
- Sergio Castillo

### Marko
#### Principios SOLID
##### Single Responsibility Principle (SRP)
Cada clase y método tiene una única responsabilidad y realiza una única función, lo que facilita el mantenimiento y la comprensión del código. Ejemplo:
```java
public class TopicService {
private final TopicRepository topicRepository;

    @Autowired  
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public void createTopic(UUID id, UUID userId, String title, String content) {
        this.topicRepository.save(new Topic(id, userId, title, content));
    }

    public void updateTopic(UUID id, String title, String content) {
        Topic topic = this.topicRepository.findById(id);
        topic.setTitle(title);
        topic.setContent(content);
        this.topicRepository.update(topic);
    }

    public void deleteTopic(UUID id) {
        Topic topic = this.topicRepository.findById(id);
        this.topicRepository.delete(topic);
    }
}
```
La clase TopicService tiene la responsabilidad única de manejar las operaciones de negocio relacionadas con los temas.
##### Dependency Inversion Principle (DIP)
Los módulos de alto nivel no deben depender de módulos de bajo nivel. Ambos deben depender de abstracciones. Ejemplo:
```java
public interface TopicRepository {
void save(Topic topic);
List<Topic> findAll();
Topic findById(UUID id);
void update(Topic topic);
void delete(Topic topic);
List<Topic> findByUserId(UUID userId);
}
```
```java
@Repository
public class JpaTopicRepository implements TopicRepository {
@PersistenceContext
protected EntityManager entityManager;

    // Implementación de métodos...
}
```
La clase TopicService depende de la abstracción TopicRepository, no de su implementación concreta, promoviendo así la flexibilidad y extensibilidad del código.
##### Open/Closed Principle (OCP)
Las clases deben estar abiertas para extensión pero cerradas para modificación, permitiendo que el comportamiento del sistema se amplíe sin modificar el código fuente existente. Ejemplo:
```java
public class JpaTopicRepository implements TopicRepository {
@PersistenceContext
protected EntityManager entityManager;

    @Override
    @Transactional
    public void save(Topic topic) {
        TopicEntity topicEntity = TopicEntity.fromDomain(topic);
        entityManager.persist(topicEntity);
    }

    // Otros métodos...
}
```
La implementación de TopicRepository se puede extender con nuevas funcionalidades sin necesidad de modificar las clases existentes que usan esta interfaz.

#### Estilos de Programación
##### Cookbook
Se utilizan instrucciones claras y paso a paso para realizar tareas específicas, facilitando la lectura y el entendimiento del código. Ejemplo:
```java
public void createTopic(UUID id, UUID userId, String title, String content) {
// Crear un nuevo objeto Topic
Topic topic = new Topic(id, userId, title, content);
// Guardar el nuevo objeto Topic en el repositorio
this.topicRepository.save(topic);
}
```
##### Pipeline
El estilo de programación pipeline se basa en la transformación secuencial de datos a través de una serie de operaciones. Ejemplo:
```java
public List<Topic> findAll() {
TypedQuery<TopicEntity> query = entityManager.createQuery("FROM TopicEntity", TopicEntity.class);
return query.getResultList().stream()
.map(TopicEntity::toDomain)
.toList();
}
```
Los datos se transforman de TopicEntity a Topic utilizando una secuencia de operaciones de flujo.
##### Uso de Interfaces
Se utilizan interfaces para definir contratos que deben cumplir las implementaciones, promoviendo la separación de responsabilidades y facilitando la prueba y el mantenimiento del código. Ejemplo:
```java
public interface TopicRepository {
void save(Topic topic);
List<Topic> findAll();
Topic findById(UUID id);
void update(Topic topic);
void delete(Topic topic);
List<Topic> findByUserId(UUID userId);
}
```
La interfaz TopicRepository define los métodos que cualquier implementación del repositorio de temas debe proporcionar, permitiendo cambiar la implementación sin afectar a las partes del código que dependen de esta interfaz.
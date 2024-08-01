# FiveChan Documentation
___

## Integrantes
- Fabricio Jesús Huaquisto Quispe
- Erick Malcoaccha Diaz
- Marko Sumire Ramos
- Christian Taipe Saraza
- Alonso Chullunquia Rosas
- Sergio Castillo

### Alonso
### Principios SOLID en `TopicController`

#### Single Responsibility Principle (SRP)
Cada método en `TopicController` tiene una única responsabilidad:
- `createTopic` para crear un tema.
- `updateTopic` para actualizar un tema.
- `deleteTopic` para eliminar un tema.

```java
@RestController
@RequestMapping("/topic")
public class TopicController {
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<String> createTopic(@RequestBody TopicDTO topic) {
        try {
            UUID id = UUID.randomUUID();
            this.topicService.createTopic(id, topic.getUserId(), topic.getTitle(), topic.getContent());
            return new ResponseEntity<>("Topic created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating topic: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateTopic(@RequestBody TopicDTO topic) {
        try {
            this.topicService.updateTopic(topic.getUserId(), topic.getTitle(), topic.getContent());
            return new ResponseEntity<>("Topic updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating topic: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTopic(@PathVariable UUID id) {
        try {
            this.topicService.deleteTopic(id);
            return new ResponseEntity<>("Topic deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting topic: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
```

#### Dependency Inversion Principle (DIP)
`TopicController` depende de la abstracción `TopicService` en lugar de implementaciones concretas, logrando esto mediante la inyección de dependencias.

```java
@Service
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

#### Open/Closed Principle (OCP)
Las clases deben estar abiertas para extensión pero cerradas para modificación, permitiendo que el comportamiento del sistema se amplíe sin modificar el código fuente existente.

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

#### Interface Segregation Principle (ISP)
`TopicController` tiene métodos específicos y separados para cada operación, evitando interfaces grandes y monolíticas.

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

#### Liskov Substitution Principle (LSP)
Los métodos del controlador pueden ser utilizados de manera intercambiable sin alterar el comportamiento esperado del sistema.

```java
public class Topic {
    private UUID id;
    private UUID userId;
    private String title;
    private String content;

    // Getters y setters
}
```

---
### Estilos de Programación en `TopicController`

#### Cookbook
Se utilizan instrucciones claras y paso a paso para realizar tareas específicas, facilitando la lectura y el entendimiento del código.

```java
@PostMapping
public ResponseEntity<String> createTopic(@RequestBody TopicDTO topic) {
    try {
        UUID id = UUID.randomUUID();
        this.topicService.createTopic(id, topic.getUserId(), topic.getTitle(), topic.getContent());
        return new ResponseEntity<>("Topic created successfully", HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>("Error creating topic: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

#### Pipeline
El estilo de programación pipeline se basa en la transformación secuencial de datos a través de una serie de operaciones.

```java
public List<Topic> findAll() {
    TypedQuery<TopicEntity> query = entityManager.createQuery("FROM TopicEntity", TopicEntity.class);
    return query.getResultList().stream()
        .map(TopicEntity::toDomain)
        .toList();
}
```

#### Uso de Interfaces
Se utilizan interfaces para definir contratos que deben cumplir las implementaciones, promoviendo la separación de responsabilidades y facilitando la prueba y el mantenimiento del código.

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
#### Error/Exception Handling en `TopicController`
El manejo de errores y excepciones se implementa utilizando bloques `try-catch` para capturar excepciones específicas y generales, devolviendo respuestas HTTP adecuadas en caso de error utilizando `ResponseEntity`.

```java
@PostMapping
public ResponseEntity<String> createTopic(@RequestBody TopicDTO topic) {
    try {
        UUID id = UUID.randomUUID();
        this.topicService.createTopic(id, topic.getUserId(), topic.getTitle(), topic.getContent());
        return new ResponseEntity<>("Topic created successfully", HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>("Error creating topic: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@PutMapping
public ResponseEntity<String> updateTopic(@RequestBody TopicDTO topic) {
    try {
        this.topicService.updateTopic(topic.getUserId(), topic.getTitle(), topic.getContent());
        return new ResponseEntity<>("Topic updated successfully", HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>("Error updating topic: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@DeleteMapping("/{id}")
public ResponseEntity<String> deleteTopic(@PathVariable UUID id) {
    try {
        this.topicService.deleteTopic(id);
        return new ResponseEntity<>("Topic deleted successfully", HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>("Error deleting topic: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```
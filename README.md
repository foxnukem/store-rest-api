# store-rest-api
REST API service for the implementation of an e-commerce store.

HTTP endpoints were inspired by the [Fake Store API](http://fakestoreapi.com/).

### Entities and relations:
- User
- Address (one-to-one with User)
- Category
- Product (many-to-one with Category)
- Review (many-to-one with Product)
- Cart (many-to-one with User)
- CartProductQuantity (many-to-many with Cart, Product, and quantity of Products in the Cart)

### API functionality:
- JWT token-based authentication
- Creating, updating and fetching User data
- CRUD operations for managing Products
- CRUD operations for managing Product Reviews
- Products filtering by Category's name
- Adding a certain number of Products to Cart
- CR operations for managing Carts
- Changing the status of the Cart
- Fetching Carts by User id
- Filtering Carts by their OrderStatus

### Technologies:
- Java 17
- Spring Boot 3
- Spring Security 6
- Spring Web
- Spring Data JPA
- Lombok
- jjwt
- Slf4j
- PostgreSQL
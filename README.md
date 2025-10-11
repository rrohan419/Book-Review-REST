# Book Review REST API

Book Review REST API is a Spring Boot application that allows users to manage books and reviews with role-based access control (ADMIN and READER).

## Features

* List all books (paginated)
* Get book details by ID
* Add a new book (ADMIN only)
* Delete a book (ADMIN only)
* Add a review to a book (READER only)
* List all reviews for a book (paginated)
* Lookup book info by ISBN (with fallback)
* Simulated login with hardcoded users and tokens

## Installation

Clone the repository:

```bash
git clone <your-repo-url>
cd book-review-api
```

Build and run with Maven:

```bash
./mvnw spring-boot:run
```

Or with Gradle:

```bash
./gradlew bootRun
```

The application will run on [http://localhost:8081](http://localhost:8081).

## Usage

### Authentication

Use the following hardcoded users:

* Admin: `username: admin`, `password: admin123` → `token-admin`
* Reader: `username: reader`, `password: reader123` → `token-reader`

Example login endpoint:

```http
POST /auth/login
Content-Type: application/json

{
    "username": "admin",
    "password": "admin123"
}
```

Response:

```json
{
    "token": "token-admin",
    "role": "ADMIN"
}
```

### Book APIs

* **List books (paginated)**
  `GET /books?page=0&size=5`

* **Get book by ID**
  `GET /books/{id}`

* **Add book (ADMIN only)**
  `POST /books`

  ```json
  {
      "title": "The Hobbit",
      "author": "J.R.R. Tolkien",
      "publishedDate": "1937-09-21"
  }
  ```

* **Delete book (ADMIN only)**
  `DELETE /books/{id}`

* **Lookup ISBN**
  `GET /books/isbn/{isbn}`

### Review APIs

* **Add review (READER only)**
  `POST /books/{bookId}/reviews`

  ```json
  {
      "rating": 5,
      "comment": "Amazing book!"
  }
  ```

* **List reviews (paginated)**
  `GET /books/{bookId}/reviews?page=0&size=10`

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

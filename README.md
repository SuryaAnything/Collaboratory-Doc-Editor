# Collaboratory Document Editor

This project is a Spring Boot application designed for collaborative document management. It provides secure user authentication, authorization, and manages documents stored in MongoDB. Additionally, it leverages the TextRazor API for advanced text analysis and summarization.

## Table of Contents
- [Overview](#overview)
- [Security Configuration](#security-configuration)
- [Content Models](#content-models)
  - [User](#user)
  - [TextToSpeech](#texttospeech)
  - [RequestAccess](#requestaccess)
  - [Doc](#doc)
- [Controllers](#controllers)
  - [PublicController](#publiccontroller)
  - [UserController](#usercontroller)
  - [DocController](#doccontroller)
- [Services](#services)
  - [UserService](#userservice)
  - [Summarizer](#summarizer)
  - [DocService](#docservice)
- [Dependencies](#dependencies)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Overview

The Colaboratory Document Editor is a Spring Boot application designed for collaborative document management. It provides secure user authentication, authorization, and manages documents stored in MongoDB. Additionally, it leverages the TextRazor API for advanced text analysis and summarization.

## Security Configuration

The application's security is configured in the `SecurityConfig` class, ensuring secure access to endpoints and protecting user data.

## Content Models

### User

The `User` class represents a user in the system, storing user details and associated documents.

### TextToSpeech

The `TextToSpeech` class models a request for text-to-speech conversion, specifying document ID, voice type, and output path.

### RequestAccess

The `RequestAccess` class facilitates granting access to documents for other users by specifying the requester's username and the document ID.

### Doc

The `Doc` class represents a document in the system, including its title and content.

## Controllers

### PublicController

The `PublicController` manages public endpoints for user management, allowing user creation.

### UserController

The `UserController` handles authenticated user operations, including user deletion and document access control.

### DocController

The `DocController` provides endpoints for document management, such as adding, deleting, summarizing, and converting text to speech for documents.

## Services

### UserService

The `UserService` manages user data persistence, including saving, deleting, and retrieving user information.

### Summarizer

The `Summarizer` service utilizes the TextRazor API to summarize document content based on extracted entities.

### DocService

The `DocService` handles CRUD operations for documents stored in MongoDB, ensuring efficient management and retrieval.

## Dependencies

Ensure the following dependencies are included in your project:

- Spring Boot Starter Web
- Spring Boot Starter Security
- Spring Data MongoDB
- Lombok (for annotation processing)
- TextRazor API (for text analysis and summarization)

## Usage

To use the application, configure your MongoDB instance, ensure dependencies are resolved, and run the application. Access the provided endpoints using a REST client or browser to manage users and documents collaboratively.

## Contributing

Contributions are welcome! Fork the repository, make your changes, and submit a pull request for review.

## License

This project is licensed under the MIT License.

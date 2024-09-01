# Library Management System


## Overview
The Library Management System (LMS) is a comprehensive solution designed to manage and streamline library operations. This system allows for the efficient handling of books, members, and transactions, including borrowing and returning books, managing reservations, and calculating fines.

## Features
### Main Branch
> Basic book management  
> Basic member management  
> Simple borrowing and returning process  


### Extended Functionality Branch
> Advanced book and member management  
> Reservation system with the ability to cancel reservations  
> Fine calculation based on overdue books  
> Enhanced search and filtering options  

 
 
## Getting Started
### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Maven 3.6.3 or higher

 
 ## Installation
### 1. Clone the repository:

```sh
git clone https://github.com/yourusername/library-management-system.git
cd library-management-system
```


### 2. Build the project using Maven:

```sh
mvn clean install
```

### 3. Run the application:

```sh
mvn exec:java -Dexec.mainClass="com.example.library.LibraryManagementSystem"
```

## Branches
### - main  
The main branch contains the core functionalities of the Library Management System. It includes basic features for managing books, members, and transactions. This branch is suitable for understanding the fundamental aspects of the system.

### - libsys-v1.1  
This branch includes additional features beyond the basics. This branch provides enhanced functionalities such as:
- Reservation system with cancelation capability  
- Fine calculation for overdue books  
- Improved search and filtering options
  
## To switch to this branch:

```sh
git checkout libsys-v1.1
```
## Running Tests  
To ensure the correctness of the system, run the tests using Maven:

```sh
mvn test
```

### Known Issues
Some functionalities in the extended-functionality branch may be incomplete or experimental.  
Ensure all required dependencies are installed for smooth operation.  

### Contributing
Feel free to contribute to this project by submitting pull requests or opening issues. Please follow the standard guidelines for contributing to open-source projects.

### License
This project is licensed under the MIT License - see the LICENSE file for details.

### Contact
For any questions or feedback, please reach out to jinanshii09@gmail.com.

Feel free to adjust any sections as needed!








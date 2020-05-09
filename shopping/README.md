# Shopping searching

## Tools used
    - Spring boot 2.1.2.RELEASE   
    - Junit 5
    - Flyway
    - Mysql 8.0
    - JDK 1.8
    - Docker
## Folder Structure
    controller: Product restful API 
    dao: Product repository which extends JpaRepository to obtain DB operation.
    service: service layer
    exception: custom exception handling of restful API
    entity: Product and ProductAudit entities    
    test: Unit test for searching feature
    
## How to set up
    Mysql Database
    * Create database on mysql with name "shopping"
    * Username: root
    * Password: mysql123
    * Port: 3306
    
    That's it for setting up

## Building JAR File
    
    Run mvn command:
    mvn clean install
    
## Run application        
    
    Run docker-compose commands:
    
    docker-compose build : generates the Docker images for the containers    
    docker-compose up -d : starts the Docker containers in the background
    
    Or run docker commands:
    
    docker run -d --name=docker-mysql --env="MYSQL_ROOT_PASSWORD=mysql123" --env="MYSQL_DATABASE=shopping" mysql
    docker build -f dockerfile -t shopping_app .
    docker run -t --link docker-mysql:mysql -p 8080:8080 shopping_app
                   
    By default port: 8080
    
    * All necessary tables and data should be generated
    
## Sample request and response
    
    1. Search by name, product, color and price; sort by name DESCENDING
    GET http://localhost:8080/api/products?sortBy=NAME DESC&searchBy=NAME,PRODUCT_NAME,COLOR,PRICE&productSearchKey=ANKER,ANKER,RED,100
    
    Request as param
    
    sortBy: NAME DESC
    searchBy: NAME,PRODUCT_NAME,COLOR,PRICE
    productSearchKey: ANKER,ANKER,RED,100
   
    2. Search by name; sort by price ASCENDING
    
    GET http://localhost:8080/api/products?productSearchKey=ANKER&sortBy=PRICE ASC&searchBy=NAME
    
    Request as param
        
        sortBy: PRICE ASC
        searchBy: NAME
        productSearchKey: ANKER
      
    Sample response as JSON     
        [
            {
                "id": 1,
                "name": "CHARGER ANKER 10000",
                "description": "ANKER IS BEST",
                "branchName": "ANKER",
                "price": 100.0,
                "color": "RED"
            }
        ]
    
    2. Get product by ID
    
    GET http://localhost:8080/api/products/1
    
    Request as path param: 1        
        
    Sample response as JSON
        {
            "id": 1,
            "name": "CHARGER ANKER 10000",
            "description": "ANKER IS BEST",
            "branchName": "ANKER",
            "price": 100.0,
            "color": "RED"
        }

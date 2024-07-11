import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import books.domain.Book;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class BooksRESTTest {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testGetOneBook() {
        // add the book to be fetched
        Book book = new Book("878","Book 123", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        // test getting the book
        given()
                .when()
                .get("books/878")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("isbn",equalTo("878"))
                .body("title",equalTo("Book 123"))
                .body("price",equalTo(18.95f))
                .body("author",equalTo("Joe Smith"));
        //cleanup
        given()
                .when()
                .delete("books/878");
    }


    @Test
    public void testGetBooks() {
        // Add some books to be fetched
        Book book1 = new Book("878", "Book 123", 18.95, "Joe Smith");
        Book book2 = new Book("879", "Book 124", 20.95, "Jane Doe");
        given().contentType("application/json").body(book1).when().post("/books").then().statusCode(200);
        given().contentType("application/json").body(book2).when().post("/books").then().statusCode(200);

        // test getting the books
        given()
                .when()
                .get("books")
                .then()
                .contentType(ContentType.JSON)
                .log().body()
                .and()
                .body("books", hasSize(greaterThan(0))) // Ensure the response has a 'books' field with at least one element
                .body("books.isbn", hasItems("878", "879"))
                .body("books.title", hasItems("Book 123", "Book 124"))
                .body("books.price", hasItems(18.95f, 20.95f))
                .body("books.author", hasItems("Joe Smith", "Jane Doe"));

        // cleanup
        given().when().delete("books/878");
        given().when().delete("books/879");
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book("880", "Book 125", 15.95, "Alice Wonderland");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);

        given()
                .when()
                .delete("books/880")
                .then()
                .statusCode(204); // No Content

        given()
                .when()
                .get("books/880")
                .then()
                .statusCode(404); // Not Found
    }

    @Test
    public void testAddBook() {
        Book book = new Book("881", "Book 126", 25.95, "Bob Builder");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200)
                .and()
                .body("isbn", equalTo("881"))
                .body("title", equalTo("Book 126"))
                .body("price", equalTo(25.95f))
                .body("author", equalTo("Bob Builder"));

        // cleanup
        given().when().delete("books/881");
    }


    @Test
    public void testUpdateBook() {
        Book book = new Book("882", "Book 127", 22.95, "Carol Singer");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);

        book.setTitle("Updated Book 127");
        book.setPrice(23.95);
        given()
                .contentType("application/json")
                .body(book)
                .when()
                .put("books/882")
                .then()
                .statusCode(200)
                .and()
                .body("isbn", equalTo("882"))
                .body("title", equalTo("Updated Book 127"))
                .body("price", equalTo(23.95f))
                .body("author", equalTo("Carol Singer"));

        // cleanup
        given().when().delete("books/882");
    }
}

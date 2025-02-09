package com.maxiflexy.orderservice.web.controller;

import com.maxiflexy.orderservice.AbstractIntegrationTest;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import com.maxiflexy.orderservice.testdata.TestDataFactory;
import io.restassured.common.mapper.TypeRef;

import com.maxiflexy.orderservice.domain.models.OrderSummary;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

@Sql("/db/migration/V4__test-orders.sql")
class OrderControllerTest extends AbstractIntegrationTest {


    @Test
    void shouldCreateOrderSuccessfully() {
        mockGetProductByCode("P100", "Product 1", new BigDecimal("25.50"));
        var payload =
                """
                    {
                        "customer" : {
                            "name": "user",
                            "email": "siva@gmail.com",
                            "phone": "08187626932"
                        },
                        "deliveryAddress" : {
                            "addressLine1": "HNO 123",
                            "addressLine2": "Kukatpally",
                            "city": "Hyderabad",
                            "state": "Telangana",
                            "zipCode": "500072",
                            "country": "Nigeria"
                        },
                        "items": [
                            {
                                "code": "P100",
                                "name": "Product 1",
                                "price": 25.50,
                                "quantity": 1
                            }
                        ]
                    }
                """;
        given().contentType(ContentType.JSON)
                //.header("Authorization", "Bearer " + getToken())
                .body(payload)
                .when()
                .post("/api/orders")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("orderNumber", notNullValue());
    }


    @Test
    void shouldReturnBadRequestWhenMandatoryDataIsMissing() {
        var payload = TestDataFactory.createOrderRequestWithInvalidCustomer();
        given().contentType(ContentType.JSON)
                //.header("Authorization", "Bearer " + getToken())
                .body(payload)
                .when()
                .post("/api/orders")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    void shouldGetOrdersSuccessfully() {
        List<OrderSummary> orderSummaries = given().when()
                //.header("Authorization", "Bearer " + getToken())
                .get("/api/orders")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(new TypeRef<>() {});

        assertThat(orderSummaries).hasSize(2);
    }


    @Test
    void shouldGetOrderSuccessfully() {
        String orderNumber = "order-123";
        given().when()
                //.header("Authorization", "Bearer " + getToken())
                .get("/api/orders/{orderNumber}", orderNumber)
                .then()
                .statusCode(200)
                .body("orderNumber", is(orderNumber))
                .body("items.size()", is(2));
    }

}
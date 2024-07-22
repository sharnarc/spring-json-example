# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.3/maven-plugin/reference/html/#build-image)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/docs/3.1.3/reference/htmlsingle/index.html#web.reactive)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)


http://localhost:8080/refund/options

post request
{
"businessAreaCurrencyList": [
{
"businessArea": "EG00",
"isoCurrencyCode": "USD"
}
]
}

response
[
{
"businessAreaCurrencyList": [
{
"businessArea": "EG00",
"isoCurrencyCode": "USD"
}
],
"refundType": "CHEQUE_PICKUP",
"s4PaymentMethod": "4",
"paymentMethod": null
},
{
"businessAreaCurrencyList": [
{
"businessArea": "EG00",
"isoCurrencyCode": "USD"
}
],
"refundType": "BANK_TRANSFER",
"s4PaymentMethod": "7",
"paymentMethod": null
}
]

http://localhost:8080/refund/options/payment

request

{
"businessAreaCurrencyList": [
{
"businessArea": "EG00",
"isoCurrencyCode": "USD"
}
],
"modeOfPayment": "Cheque"
}

response

{
"currency": "USD",
"paymentMethod": "D",
"s4PaymentMethod": "4"
}




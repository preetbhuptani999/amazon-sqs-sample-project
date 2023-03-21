# README #

**API Details**

1) /order API is used to create a new order
2) /labpartner/webhook API is called by a third-party lab partner.

**Amazon SQS Implementation**

We publish a new message in Amazon SQS whenever there is any new order. The consumer will poll the SQS and if there is a new order then it will save order details in a database and send the request to a third-party lab partner.
# food_app

Small demo app that retrieves data from a RabbitMQ exchange called "branded-food-exchange".

RabbitMQ should be running locally, recommend starting it using the following command:
docker run -d --hostname my-rabbit --name rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management

With this, the Rabbit management interface will be available at localhost:15672 with login guest/guest.

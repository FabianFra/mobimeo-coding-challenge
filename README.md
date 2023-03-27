# Mobimeo-Coding-Challenge

---------------------------
This is my solution for the Mobimeo coding challenge for Software Engineer - Scala postion.

Thanks again for the opportunity to show my skills. The task was quite fun and I have learned a lot about Scala and Akka!

You can start the server when you run [HttpServerRouting](src/main/scala/HttpServerRouting.scala) or simply execute `sbt run`.
## Available endpoints

--------------------------
Usually all available endpoints are documented in a Swagger Document. Due to the time limit I decided to not include it.

**localhost:8081/vehicle?time={time}&x={x}&y={y}** : Find a vehicle for a given time and X & Y coordinates e.g.
http://localhost:8081/vehicle?time=10:00:00&x=1&y=1

**localhost:8081/vehicle/stop/{stopId}** : Return the vehicle arriving next at a given stop
e.g. http://localhost:8081/vehicle/stop/9

**localhost:8081/vehicle/{vehicleId}/is-delayed** : Indicate if a given line is currently delayed
e.g. http://localhost:8081/vehicle/2/is-delayed

## What would I do if I have more time

---------------------------
Due to the given time I was not able to do everything I wanted to do.

- Integrate Swagger and write a well described documentation of the webservice
- More exception handling for edge cases
- Write more tests

- Try to optimize "db"-calls and the code in general (add more Scala sugar)

## Structure

----------------------------

*src/main/scala*:
 * model: Model entities and dtos
 * repository: Repository classes for entities which mock db queries
 * resources: Includes the provided data as .csv files
 * service: Service classes which are called for web request handling
 * util: Includes helper classes for .csf file and date time handling

*src/main/scala*: Includes test classes (JUnit)

---------------------------

### Mobimeo: https://mobimeo.com/
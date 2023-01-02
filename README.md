# Comment section

REST API developed with Java and Spring Boot as an aggregate-oriented database assignment for my Databases course in university, in 2021.

The project simulates a comment section in which users can create posts and add comments to existing posts. Data is stored as JSON-like documents in MongoDB.

The database is designed as the following UML diagram, so that Comments are nested in Posts, and Posts are referenced by Users. This way, there are documents that contain a user data and reference all posts made by this user, and documents that contain a post data with all comments added to it.

![UML diagram](https://github.com/stautisabela/comment-section/blob/master/etc/uml.png?raw=true)

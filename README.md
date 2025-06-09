# MoonPhase MCP SSE server

This project uses [Quarkus](http://quarkus.io/) framework.
It creates a Model Context Protocol (MCP) Server-Sent Event server to calculate the current phase of the moon, or the phase at a given date.

The algorithm has been translated to Java thanks to [Gemini](http://gemini.google.com/), 
from this [repository](https://github.com/oliverkwebb/moonphase/tree/main) offering implementations in various languages.

> [!NOTE]
> Read more about this project in this article: 
> [Building an MCP server with Quarkus and deploying on Google Cloud Run](https://glaforge.dev/posts/2025/06/09/building-an-mcp-server-with-quarkus-and-deploying-on-google-cloud-run/)

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using:

```shell
./mvnw clean package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell
./mvnw clean package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell
./mvnw clean package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell
./mvnw clean package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/moonphase-1.0-SNAPSHOT-runner`

## Deploying to Cloud Run

You can containerize your application with the provided `Dockerfile`s.
Copy the `Dockerfile.jvm` from `src/main/docker/` at the root of your project.

> [!NOTE]
> If you don't have an account on Google Cloud yet, you can create an account with a [free trial](https://cloud.google.com/free) 
> and benefit from $300 of credits.

Build the project with Cloud Build:

```shell
gcloud builds submit --tag gcr.io/YOUR_PROJECT_ID/moonphases
```

And deploy it to Cloud Run with:

```shell
gcloud run deploy moonphases --allow-unauthenticated --image gcr.io/YOUR_PROJECT_ID/moonphases
```

You'll be asked along the way to activate the required cloud APIs.

---

This project is licensed under the [Apache 2 license](LICENSE).

> [!NOTE]
> This is not an official Google project
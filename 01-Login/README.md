# Java EE Login Sample

This sample demonstrates how to use Auth0 to perform authentication using Java EE and the `mvc-auth-commons` SDK. Download or clone this repository and follow the instructions below to setup the sample.

## Project Setup

### Auth0 Dashboard

1. On the [Auth0 Dashboard](https://manage.auth0.com/#/clients) create a new Application of type **Regular Web Application**.
1. On the **Settings** tab of your application, add the URL `http://localhost:3000/callback` to the **Allowed Callback URLs** field.
1. On the **Settings** tab of your application, add the URL `http://localhost:3000/` to the **Allowed Logout URLs** field.
1. Copy the `Domain`, `Client ID` and `Client Secret` values at the top of the page and use them to configure the Java Application.

### Application configuration

Copy `src/main/webapp/WEB-INF/web.xml.example` to `src/main/webapp/WEB-INF/web.xml`:

```bash
cp src/main/webapp/WEB-INF/web.xml.example src/main/webapp/WEB-INF/web.xml
```

Set the application values in the `web.xml` file to the values of your Auth0 application.

```xml
<env-entry>
    <env-entry-name>auth0.domain</env-entry-name>
    <env-entry-type>java.lang.String</env-entry-type>
    <env-entry-value>{DOMAIN}</env-entry-value>
</env-entry>
<env-entry>
    <env-entry-name>auth0.clientId</env-entry-name>
    <env-entry-type>java.lang.String</env-entry-type>
    <env-entry-value>{CLIENT_ID}</env-entry-value>
</env-entry>
<env-entry>
    <env-entry-name>auth0.clientSecret</env-entry-name>
    <env-entry-type>java.lang.String</env-entry-type>
    <env-entry-value>{CLIENT_SECRET}</env-entry-value>
</env-entry>
<env-entry>
    <env-entry-name>auth0.scope</env-entry-name>
    <env-entry-type>java.lang.String</env-entry-type>
    <env-entry-value>openid profile email</env-entry-value>
</env-entry>
```

## Running the sample

You can run the sample with Maven or Docker.

### Maven

Open a terminal, go to the project root directory and execute the `wildfly:run` Maven goal.

Linux/macOS:
```bash
./mvnw clean wildfly:run
```

Windows:
```bash
mvnw.cmd clean wildfly:run
```

The server will be accessible on http://localhost:3000.

### Docker

Linux/macOS:
```bash
sh exec.sh
```

Windows:
```bash
./exec.ps1
```

The server will be available on http://localhost:3000.

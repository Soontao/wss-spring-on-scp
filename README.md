# WS-Security-Spring-Application

Application on SCP with WSS service

with Spring and JPA

## start

enable h2 & log in local running, and start as application in class Application

## links

Open link after server started

* Access all services: http://localhost:8080/services

* View main service WSDL: http://localhost:8080/services/main?wsdl

## test

please import `src/main/resources` `privatestore/publicstore` with password `keyStorePassword` into SOAPUI, and [set private key to sign & public key to encrypt](https://www.soapui.org/security-testing/getting-started.html), alias password is `WSSign`

## x509

```bash
keytool -genkey -alias WSSign -keypass testcert -keystore  privatestore.jks -storepass keyStorePassword -dname "cn=WSSign" -keyalg RSA
keytool -selfcert -alias WSSign -keystore privatestore.jks -storepass keyStorePassword -keypass testcert
keytool -export -alias WSSign -file key.rsa -keystore privatestore.jks -storepass keyStorePassword
keytool -import -alias WSSign  -file key.rsa -keystore publicstore.jks -storepass keyStorePassword
```

privatekey used to decrypt/sign

publickey used to encrypt/validation

## convert p12 to jks

```bash
keytool -importkeystore -srckeystore Download.p12 -destkeystore out.jks -deststoretype jks -deststorepass test001
```
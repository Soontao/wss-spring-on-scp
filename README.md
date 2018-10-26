# WS-Security-Spring-Application

Application on SCP with WSS service

with Spring and JPA

WS Security provides `application layer` encrypt method, ref [this document](https://www.oasis-open.org/committees/download.php/16790/wss-v1.1-spec-os-SOAPMessageSecurity.pdf)

## start application

Enable h2 & log maven profile in local running, and start as application in class Application.

You can disable WebserviceConfig.class file to disable ws security feature.

This application does NOT include user authentication module.

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

cert alias: wssign
cert pass: testcert

## convert p12 to jks

```bash
keytool -importkeystore -srckeystore Download.p12 -destkeystore out.jks -deststoretype jks -deststorepass test001
```

## encrypt & sign sample

Source Message

```xml
<soapenv:Envelope xmlns:impl="http://impl.services.wss.spring.hana.sap.corp/" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
   <soapenv:Header><wsse:Security xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd"/></soapenv:Header>
   <soapenv:Body>
      <impl:createTeacher>
         <arg0>
            <teacherAge>22</teacherAge>
            <teacherName>Theo Sun</teacherName>
         </arg0>
      </impl:createTeacher>
   </soapenv:Body>
</soapenv:Envelope>
```

Encrypted

```xml
<soapenv:Envelope xmlns:impl="http://impl.services.wss.spring.hana.sap.corp/" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
   <soapenv:Header><wsse:Security xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd"><ds:Signature Id="SIG-9FE6FA2C3D9C8FBBA8154054071653346" xmlns:ds="http://www.w3.org/2000/09/xmldsig#"><ds:SignedInfo><ds:CanonicalizationMethod Algorithm="http://www.w3.org/2001/10/xml-exc-c14n#"><ec:InclusiveNamespaces PrefixList="impl soapenv" xmlns:ec="http://www.w3.org/2001/10/xml-exc-c14n#"/></ds:CanonicalizationMethod><ds:SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1"/><ds:Reference URI="#id-9FE6FA2C3D9C8FBBA8154054071653245"><ds:Transforms><ds:Transform Algorithm="http://www.w3.org/2001/10/xml-exc-c14n#"><ec:InclusiveNamespaces PrefixList="impl" xmlns:ec="http://www.w3.org/2001/10/xml-exc-c14n#"/></ds:Transform></ds:Transforms><ds:DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1"/><ds:DigestValue>SvA03A3pg5UDGGaLwStURinanbc=</ds:DigestValue></ds:Reference></ds:SignedInfo><ds:SignatureValue>XakHoDdz3aR9F+U3CVoAZBLoXAuprh/K7oZee/yY/YkkrOou9IlfwtRQ5wbRPdRgoVK7UvcDe2kp
P/7x8FpYPp+u/dxh72wq0SSiDIKlvuWWN39iWknmwn6dKyEt941SuxRxlvh0X9oQ9pPGK4VoRNpe
iLgfk5VhYarSpT4k057a0+RfrzMk4V6Nh3v+6LmxDfCmMvprR+8zX3bfSdMCmSGN8T215o22/A5h
sGpSu4j3XkKY8d1jy0+wEMkoTO60iFhY/eCiC6ecRk1yZwEvx18fUnIiiKTAnd3wAJeKh/wAx7c5
Z6qgwTOaZa1hn1KSOxQKSty9DSdpkIUaHhzSkQ==</ds:SignatureValue><ds:KeyInfo Id="KI-9FE6FA2C3D9C8FBBA8154054071653243"><wsse:SecurityTokenReference wsu:Id="STR-9FE6FA2C3D9C8FBBA8154054071653244"><ds:X509Data><ds:X509IssuerSerial><ds:X509IssuerName>CN=WSSign</ds:X509IssuerName><ds:X509SerialNumber>1919776202</ds:X509SerialNumber></ds:X509IssuerSerial></ds:X509Data></wsse:SecurityTokenReference></ds:KeyInfo></ds:Signature><xenc:EncryptedKey Id="EK-9FE6FA2C3D9C8FBBA8154054071652940" xmlns:xenc="http://www.w3.org/2001/04/xmlenc#"><xenc:EncryptionMethod Algorithm="http://www.w3.org/2001/04/xmlenc#rsa-oaep-mgf1p"/><ds:KeyInfo xmlns:ds="http://www.w3.org/2000/09/xmldsig#"><wsse:SecurityTokenReference><ds:X509Data><ds:X509IssuerSerial><ds:X509IssuerName>CN=WSSign</ds:X509IssuerName><ds:X509SerialNumber>1919776202</ds:X509SerialNumber></ds:X509IssuerSerial></ds:X509Data></wsse:SecurityTokenReference></ds:KeyInfo><xenc:CipherData><xenc:CipherValue>gYcK9LCi6nc12TzIKk4wAJeRzbNR9zxJP/iDYtEemfcaob6SZd0y77FVWK6QIfkbiW80D2VdMEeTooNOZHr6EMSx/tFVSO2O/nif73KM1PEHcYMpQDh7aRHkpre8W4/05fsZ1Jozc8uUPpDlq2A5D6gxA+GhiWwrFHjbje3YvwKFXCkwLBXjCvqahpUbXPIX+PHoMS+OTLghladIk4Jy1xHI3bhjKL5DLS9erRFrqEmCfN7XTM3B3ogJVPqyYUmt6iX2LKzqpi0fanhsYikfuwNEhYT7Hd4FDmRcRs+3XHqftjSeRZc7jQJBYuKTsCEIDm8a4zepZhVQ4XuteHpNUA==</xenc:CipherValue></xenc:CipherData><xenc:ReferenceList><xenc:DataReference URI="#ED-9FE6FA2C3D9C8FBBA8154054071653041"/></xenc:ReferenceList></xenc:EncryptedKey></wsse:Security></soapenv:Header>
   <soapenv:Body wsu:Id="id-9FE6FA2C3D9C8FBBA8154054071653245" xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd"><xenc:EncryptedData Id="ED-9FE6FA2C3D9C8FBBA8154054071653041" Type="http://www.w3.org/2001/04/xmlenc#Content" xmlns:xenc="http://www.w3.org/2001/04/xmlenc#"><xenc:EncryptionMethod Algorithm="http://www.w3.org/2001/04/xmlenc#aes128-cbc"/><ds:KeyInfo xmlns:ds="http://www.w3.org/2000/09/xmldsig#"><wsse:SecurityTokenReference wsse11:TokenType="http://docs.oasis-open.org/wss/oasis-wss-soap-message-security-1.1#EncryptedKey" xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" xmlns:wsse11="http://docs.oasis-open.org/wss/oasis-wss-wssecurity-secext-1.1.xsd"><wsse:Reference URI="#EK-9FE6FA2C3D9C8FBBA8154054071652940"/></wsse:SecurityTokenReference></ds:KeyInfo><xenc:CipherData><xenc:CipherValue>8w5tjgY7eEqaoiKFQFLE4qTAZL/CzaMYhtqxK7H9LGhUAbKEaoK+90TNXoK1/MT6Pyd2yTajByT6d28yQkeq8pN+ZjAvt9t4ukk9cuB6x0KOlWHPeXePoMjCN3GTyK1UhsvfGt9j8nr7GKCKghdLCZFnSUBfrY7WaCLHyHdslpAV1KHRgDtCJe3cuEIyjcIfOjpB0UiN2Xakes5yB2kMd6JM/3j4vplwPb5MayjqKEWETpMvTnBx8MuLovjuQc9daUViIpwPyrq08eexkHZ0IVc/yQP38dZ7YKzmT1d6vSQT2ydsGS5ZnKsdyfhx7/3mqODfvdxyh11iVuqQFHLRPdT/SCnRa/NzW6TvaY7uK6tPt/WIsZL70Xs6SpoX030Orkc/9mVv0Kd9iZ0m6XmPrTpySqjrPGBucHmzL2HKVgI=</xenc:CipherValue></xenc:CipherData></xenc:EncryptedData></soapenv:Body>
</soapenv:Envelope>
```
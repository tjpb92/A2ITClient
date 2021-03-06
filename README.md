# A2ITCLient

Connecteur Anstel / Intent Technologies (lien montant).

## Utilisation:
```
java A2ITClient[-ifxserver ifxdb] [-mgoserver mgodb] [-apiserver apiserver] [-d] [-t] 
```
où :
* ```-apiserver apiserver``` est la référence au serveur d'API distant, par défaut désigne le serveur de développement Voir fichier *A2ITClient.prop* (paramètre optionnel).
* ```-ifxserver ifxdb``` est la référence à la base de données Informix, par défaut désigne la base de données de développement. Voir fichier *A2ITClient.prop* (optionnel).
* ```-mgoserver mgodb``` est la référence à la base de données Mongo, par défaut désigne la base de données de développement. Voir fichier *A2ITClient.prop* (optionnel).
* ```-d``` le programme s'exécute en mode débug, il est beaucoup plus verbeux. Désactivé par défaut (paramètre optionnel).
* ```-t``` le programme s'exécute en mode test, les transcations en base de données ne sont pas faites. Désactivé par défaut (paramètre optionnel).

## Pré-requis :
- Java 6 ou supérieur.
- Driver Mongodb pour Java
- JDBC Informix
- Jackson
- OKHttp
- OKIO
- Kotlin
- ez-vcard
- JavaMail API

## Fichier des paramètres : 

Ce fichier permet de spécifier les paramètres d'accès aux différentes bases de données.

A adapter selon les implémentations locales.

Ce fichier est nommé : *A2ITCLient.prop*.

Le fichier *A2ITCLient_Example.prop* est fourni à titre d'exemple.

## Références:

- [GitHub OKHttp] (https://square.github.io/okhttp/)
- [GitHub OKIO] (https://github.com/square/okio)
- [Tuto A Guide to OkHttp] (https://www.baeldung.com/guide-to-okhttp)
- [Pour valider un Json] (https://jsonformatter.curiousconcept.com/)
- [vCard library] (https://github.com/mangstadt/ez-vcard)
- [JavaMail API] (https://javaee.github.io/javamail)

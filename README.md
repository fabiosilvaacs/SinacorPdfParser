# Sinacor PDF Parser

Objetivo: Extrair notas de negociacao Sinacor de arquivos pdf no formato Sinacor da Bovespa e BM&F.

Fork de https://github.com/davidboto/SinacorPdfParser/.

## Uso como dependência

O projeto gera um JAR Maven comum e não depende de Spring Boot. 
Depois de publicar o artefato em um repositório Maven, adicione:

```xml
<dependency>
    <groupId>br.com.fabiophx</groupId>
    <artifactId>sinacorpdfparser</artifactId>
    <version>1.0.0</version>
</dependency>
```

Para extrair o texto de um PDF e processar as notas:

```java
String texto = new PDFToText(caminho, senha).getText();
List<NotaNegociacao> notas = new ParserBMF().find(texto).getNotas();
```

```test
& .\mvnw.cmd -q -Dtest=ParserBMFTest test
```
# Sinacor PDF Parser

Objetivo: Extrair dados de notas de corretagem de arquivos pdf no formato Sinacor da Bovespa e BM&F.

Fork de https://github.com/davidboto/SinacorPdfParser/.

## Compilação e testes

Requer JDK 25. Configure `JAVA_HOME` para o JDK 25 e execute:

```powershell
.\mvnw.cmd clean verify
```
## Alterações realizadas

* Uso do Maven
* Refatorado para facilitar o uso deste projeto em outros
* Testes unitários - não deixei os arquivos .pdf por terem informações particulares.
* Uso do lombok.

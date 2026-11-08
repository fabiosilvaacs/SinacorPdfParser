# Sinacor PDF Parser

Objetivo: Extrair dados de notas de corretagem de arquivos pdf no formato Sinacor da Bovespa e BM&F.

Fork de https://github.com/davidboto/SinacorPdfParser/.

## Alterações realizadas

* Uso do Maven
* Refatorado para facilitar o uso deste projeto em outros
* Testes unitários. Não deixei os arquivos .pdf por terem informações particulares.
* Uso do lombok.
* Uso de camelCase e BigDecimal em vez de Double por ter melhor precisão numérica.

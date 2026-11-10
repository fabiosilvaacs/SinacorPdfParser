# Sinacor PDF Parser

Objetivo: Extrair dados de notas de corretagem de arquivos pdf no formato Sinacor da Bovespa e BM&F.

Fork de https://github.com/davidboto/SinacorPdfParser/.

## Alterações realizadas

* Melhorias de projeto
  * Uso do Maven
  * Refatorado para facilitar o uso deste projeto em outros
  * Uso do lombok.
  * Uso de camelCase e BigDecimal em vez de Double por ter melhor precisão numérica.
* Inclusão de Testes unitários 
  * **IMPORTANTE:** Não deixei os arquivos .pdf e seus .json (valores esperados nos testes) por terem informações particulares.

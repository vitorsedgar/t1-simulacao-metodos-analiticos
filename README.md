## TODO

- [DONE] <s>Leitura de arquivo ou parametrização para input das variáveis (tempo médio de chegada e saída, número de servidores e capacidade da fila). Sugestão: .yml ou variaveis parametrizadas pelo comando ao rodar o jar </s>
- [DONE] <s>Testar e documentar código para ser compilado (.jar) Output: comando no terminal </s>
- [DONE] <s>Alterar implementação para aceitar as varáveis passadas no arquivo </s>
- Alterar entrada para rodar 5 vezes
- Alterar log de output para fazer média das execuções

## Como rodar o projeto:

Após compilador o jar execute: 

```java -jar t1-metodos-analiticos-1.0-SNAPSHOT.jar 2:4-3:5-2-5```

- Lembrando que o comando funciona caso o terminal esteja aberto no mesmo diretório do .jar, caso não, aponte para o mesmo

Caso queira alterar os parametros da execução altere os valores numéricos:

- 2:4 - Tempo de chegada minimo e máximo
- 3:5 - Tempo de saída minimo e máximo
- 2 - Número de servidores
- 5 - Capacidade máxima da fila 
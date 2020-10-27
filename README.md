## TODO

##### AJUSTES:

## Como rodar o projeto:

Após compilador o jar execute: 

```java -jar t1-metodos-analiticos-1.0-SNAPSHOT.jar```

- Lembrando que o comando funciona caso o terminal esteja aberto no mesmo diretório do .jar, caso não, aponte para o mesmo
- É necessário também um arquivo config.yml que deve conter as configurações das filas na seguinte forma:
- O arquivo de configuração é fixo como config.yml, uma alteração futura permitira que o nome e diretório do arquivo seja passado como argumento para a aplicação 

Exemplo de logging para uso como base: 

```=========================================================
   ============   QUEUEING NETWORK SIMULATOR   =============
   ===================   version 2.0    ====================
   ==================    (March 2013)    ===================
   ================    by Gabriel Couto    =================
   =========================================================
   =====  developed during the undergraduate class on  =====
   ====== Performance Evaluation of Systems (2012/2)  ======
   =====      taught by Prof. Afonso Sales at          =====
   ======   Faculty of Informatics (FACIN/PUCRS)      ======
   =========================================================
   Simulation: #1
   ...simulating with random numbers (seed '1')...
   Simulation: #2
   ...simulating with random numbers (seed '2')...
   Simulation: #3
   ...simulating with random numbers (seed '3')...
   Simulation: #4
   ...simulating with random numbers (seed '4')...
   Simulation: #5
   ...simulating with random numbers (seed '5')...
   =========================================================
   =================    END OF SIMULATION   ================
   =========================================================
   
   =========================================================
   ======================    REPORT   ======================
   =========================================================
   *********************************************************
   Queue:   Q1 (G/G/1/5)
   Arrival: 20.0 ... 40.0
   Service: 10.0 ... 12.0
   *********************************************************
      State               Time               Probability
         0         1556763,9510                60,59%
         1          984734,4270                38,33%
         2           27544,9674                 1,07%
         3             231,3232                 0,01%
   
   Number of losses: 0
   
   *********************************************************
   Queue:   Q2 (G/G/2/5)
   Service: 30.0 ... 120.0
   *********************************************************
      State               Time               Probability
         0           25325,8646                 0,99%
         1          165968,3616                 6,46%
         2          434554,4680                16,91%
         3          700389,0130                27,26%
         4          790809,9556                30,78%
         5          452227,0059                17,60%
   
   Number of losses: 6244
   
   *********************************************************
   Queue:   Q3 (G/G/2/5)
   Service: 15.0 ... 60.0
   *********************************************************
      State               Time               Probability
         0         1636967,9845                63,71%
         1          779468,0203                30,34%
         2          140508,5906                 5,47%
         3           11822,0921                 0,46%
         4             507,7963                 0,02%
         5               0,1848                 0,00%
   
   Number of losses: 0
   
   *********************************************************
   Queue:   Q4 (G/G/3)
   Service: 5.0 ... 15.0
   *********************************************************
      State               Time               Probability
         0         1931277,9789                75,17%
         1          576108,8657                22,42%
         2           59407,5978                 2,31%
         3            2442,9579                 0,10%
         4              37,2684                 0,00%
   
   Number of losses: 0
   
   =========================================================
   Simulation average time: 513854,9337
   =========================================================```
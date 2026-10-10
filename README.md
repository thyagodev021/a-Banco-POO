# 🏦 Banco POO em Java

Projeto de terminal em Java que simula um banco simples, usando os pilares da **Programação Orientada a Objetos**: abstração, herança, polimorfismo e encapsulamento.

## Funcionalidades

- Criar conta corrente (com limite de cheque especial) e conta poupança (com rendimento)
- Depositar, sacar e transferir entre contas
- Ver extrato de cada conta
- Aplicar juros mensais na poupança
- Tratamento de erros com exceção própria (`SaldoInsuficienteException`)

## Estrutura

```
src/banco/
├── Main.java                       # menu no terminal
├── Conta.java                      # classe abstrata base
├── ContaCorrente.java              # herda de Conta
├── ContaPoupanca.java              # herda de Conta
└── SaldoInsuficienteException.java # exceção personalizada
```

## Como rodar

Requisito: JDK 17 ou superior.

```bash
git clone https://github.com/thyagodev021/java-banco-poo.git
cd java-banco-poo
javac -d out src/banco/*.java
java -cp out banco.Main
```

## Conceitos usados

- Classe abstrata e métodos abstratos
- Herança e polimorfismo (`saldoDisponivel()` muda em cada conta)
- Encapsulamento com atributos privados
- Exceções personalizadas
- `switch` moderno, `instanceof` com pattern matching e Streams

## Autor

Thyago Ferreira - [@thyagodev021](https://github.com/thyagodev021)

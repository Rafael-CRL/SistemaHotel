# SistemaHotel

Sistema de gestão de hotel em Java, desenvolvido em equipe na faculdade.

- Login por CPF
- Reservas: criação, check-in e check-out
- Financeiro: pagamentos, saldo do dia e emissão de recibo

## Stack

Java 24, Swing (NetBeans), JCalendar, MySQL via JDBC. Arquitetura MVC com DAO: `model`, `view`, `controller` e `dao`.

## Executar

1. Abrir o projeto no NetBeans com JDK 24.
2. Ter um banco MySQL com as tabelas de usuários, reservas e transações.
3. Definir as variáveis de ambiente antes de rodar:

```bash
HOTEL_DB_URL="jdbc:mysql://host:3306/banco?useSSL=false&serverTimezone=UTC"
HOTEL_DB_USER=usuario
HOTEL_DB_PASSWORD=senha
```

No NetBeans, em *Run → Set Project Configuration*, ou no ambiente do sistema.

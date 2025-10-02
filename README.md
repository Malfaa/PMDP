# Plataforma de Mentoria e Desenvolvimento Profissional

![Status](https://img.shields.io/badge/status-em_desenvolvimento-yellow)

## 🎯 O que é o projeto?

Este projeto é o desenvolvimento de uma API RESTful para uma **plataforma que conecta profissionais experientes (Mentores) a indivíduos que buscam orientação de carreira (Mentorados)**. A plataforma visa facilitar a busca por mentores, o agendamento de sessões, o compartilhamento de materiais e o acompanhamento do desenvolvimento profissional de forma organizada e eficiente.

## ✨ Funcionalidades Principais

O sistema foi desenhado com três perfis de usuários, cada um com suas funcionalidades específicas:

#### Para Mentorados:
* 🔎 **Buscar mentores** por área de expertise, disponibilidade e avaliações.
* 📅 **Agendar sessões** de mentoria de forma simples.
* 📚 **Acessar materiais de apoio** compartilhados pelos mentores.
* ⭐ **Avaliar as sessões** com notas e comentários.
* 💬 **Canal de comunicação** direto com o mentor e com a administração.

#### Para Mentores:
* 🛠️ **Gerenciar suas sessões:** criar, editar e definir detalhes como preço e duração.
* 📤 **Compartilhar materiais** com seus mentorados.
* 📝 **Fornecer feedback** estruturado sobre o progresso dos mentorados.
* 📊 **Visualizar seu histórico** de agendamentos e avaliações recebidas.

#### Para Administradores:
* 👥 **Gerenciamento completo de usuários**, sessões e categorias de mentoria.
* 📈 **Visualização de relatórios** de uso da plataforma.
* 💬 **Moderação** da comunicação entre os usuários.

## 🚀 Tecnologias Utilizadas

O backend está sendo construído com um stack moderno e robusto, focado em boas práticas de desenvolvimento e escalabilidade:

* **Linguagem:** Java 21
* **Framework Principal:** Spring Boot 3
* **Segurança:** Spring Security (autenticação via JWT)
* **Banco de Dados:** PostgreSQL (ambiente de produção) e H2 (ambiente de testes)
* **Testes:** JUnit 5 e Mockito
* **Build Tool:** Gradle

## ⚙️ Como Executar o Projeto

```bash
# 1. Clone o repositório
git clone [https://github.com/Malfaa/PMDP.git](https://github.com/Malfaa/PMDP.git)

# 2. Navegue até a pasta do projeto
cd PMDP

# 3. Execute o projeto com Maven
mvn spring-boot:run
```
> **Nota:** É necessário ter o Java 21 e o Gradle configurados no seu ambiente. As configurações do banco de dados estão no arquivo `application.properties`.
---
> Para uma documentação técnica completa, com todos os Requisitos Funcionais, Não Funcionais e Casos de Uso detalhados, veja o arquivo [Documentação Completa PDF](https://drive.google.com/file/d/1ShQCktbPCvR4gaO6e_GU4anxErB1Y19T/view?usp=drive_link).

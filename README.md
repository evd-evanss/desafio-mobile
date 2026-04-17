# Desafio Mobile - Ingresso.com

Este projeto é uma aplicação Android modularizada que consome a API de eventos da Ingresso.com, exibindo filmes em destaque e estreias. Foi desenvolvido utilizando as tecnologias mais modernas do ecossistema Android para garantir performance, testabilidade e manutenibilidade.

---

## 🚀 Como Rodar o Projeto

1.  **Pré-requisitos**: Certifique-se de ter o **Android Studio Ladybug (ou superior)** instalado.
2.  **Clone o repositório**: `git clone <url-do-repositorio>`
3.  **Sincronize o Gradle**: Abra o projeto e aguarde a indexação e o download das dependências (Version Catalog).
4.  **Configuração de SDK**: O projeto utiliza **Compile SDK 36** e **Java 11**.
5.  **Execução**: Selecione o módulo `app` e clique em **Run**.
    *   *Dica*: O projeto inclui o **Chucker**. Ao realizar requisições, você verá uma notificação para inspecionar os payloads da API em tempo real.

---

## 🛠 Tomada de Decisão e Arquitetura

O projeto foi estruturado seguindo os princípios de **Clean Architecture** e **MVVM**, visando uma separação clara de responsabilidades.

### 1. Modularização por Camadas e Features
*   **`:app`**: Orquestrador central, contém a classe `Application` e a configuração de navegação.
*   **`:feature:movies` / `:feature:splash`**: Módulos independentes para cada fluxo de negócio. Isso isola o escopo de compilação e facilita o trabalho em equipe.
*   **`:network`**: Centraliza a infraestrutura de rede, evitando que detalhes de implementação do Ktor vazem para as camadas de domínio.
*   **`:commons`**: Contém classes base (ViewModel, State) e extensões compartilhadas entre as features.
*   **`:ds` (Design System)**: Centraliza componentes de UI, cores, fontes e Shimmers para garantir consistência visual.

### 2. Stack Tecnológica
*   **Jetpack Compose**: Interface 100% declarativa.
*   **Ktor Client**: Escolhido em vez do Retrofit por ser mais leve, moderno e oferecer suporte nativo a Coroutines e Kotlin Serialization.
*   **Koin**: Framework de Injeção de Dependência pela sua simplicidade e baixo boilerplate em comparação ao Dagger/Hilt.
*   **Kotlinx Serialization**: Para parsing de JSON, garantindo segurança de tipos e integração nativa com o Ktor.
*   **Turbine**: Utilizado nos testes unitários para validar fluxos de `StateFlow` de forma concisa.

### 3. Gerenciamento de Dependências
*   **Version Catalog (libs.versions.toml)**: Centraliza todas as versões de bibliotecas em um único lugar, facilitando atualizações e garantindo que todos os módulos utilizem as mesmas versões.

---

## 🔍 Vulnerabilidades e Observações de Desenvolvimento

Durante o desenvolvimento, alguns pontos foram observados:

*   **Dependência de Contexto no Módulo de Rede**: O módulo `:network` necessita de um `androidContext` para inicializar o Chucker. Isso cria uma dependência implícita de que o módulo `:app` forneça o contexto corretamente via Koin. Para falhas graves de segurança ou bugs impeditivos, favor acionar por e-mail.
*   **Hardcoded URLs**: Atualmente, as URLs da API estão diretamente na classe de serviço. Em um cenário de produção, estas seriam movidas para variáveis de ambiente via `BuildConfig`.

---

## 🧪 Testes Unitários

O projeto foca na cobertura da lógica de negócio. Para rodar os testes:
1. Vá até o módulo `:feature:movies`.
2. Execute `./gradlew :feature:movies:test`.
3. Tecnologias: **JUnit 4**, **MockK** (mocking), **Truth** (assertions) e **Turbine** (Flow testing).

---

*Nota para o "eu do futuro": A estrutura de State e Effects no módulo `:commons` segue o padrão MVI, garantindo que a UI apenas reaja a estados imutáveis disparados pela ViewModel.*

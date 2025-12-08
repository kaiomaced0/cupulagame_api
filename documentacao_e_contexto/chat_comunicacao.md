# 💬 **Sistema de Chat e Comunicação**

Este documento detalha as regras de comunicação, privacidade e distinção entre **Usuário** (Conta Real) e **Player** (Personagem no RP).

---

# 👤 **1. Conceito: Usuário vs. Player**

A premissa fundamental é o **Sigilo de Identidade**.
*   **Usuário:** A conta global (Login/Email).
*   **Player:** O personagem criado dentro de um servidor/mundo.

## 🔒 **1.1 Regra de Sigilo**
*   Por padrão, **ninguém sabe quem é o Usuário por trás de um Player**.
*   Ao encontrar um personagem no mundo, você vê apenas o nome do **Player**.
*   Isso permite que um mesmo Usuário tenha personagens com reputações diferentes (ex: um herói e um bandido) sem que as ações de um manchem o outro.

---

# 📨 **2. Canais de Comunicação**

## 🗣️ **2.1 Chat Local (Proximidade)**
*   **Escopo:** Apenas jogadores dentro de um raio X (ex: 50 metros).
*   **Identificação:** Nome do **Player**.
*   **Uso:** Roleplay, conversas diretas, negociações locais.

## 🌍 **2.2 Chat Global / Regional**
*   **Escopo:** Todo o servidor ou toda a Ilha (dependendo da configuração do canal).
*   **Identificação:** Nome do **Player**.
*   **Restrição:** Pode consumir recursos (ex: megafone) ou ter cooldown para evitar spam.

## 🛡️ **2.3 Chat de Guilda**
*   **Escopo:** Membros da guilda.
*   **Identificação:** Nome do **Player** (pois é o personagem que faz parte da guilda).
*   **Dependência:** Pode depender do nível da **Torre de Comunicação** (ver `principal.md`).

## 👥 **2.4 Chat de Grupo (Party)**
*   **Escopo:** Membros do grupo temporário.
*   **Identificação:** Nome do **Player**.

---

# 🤝 **3. Sistema de Amigos e Mensagens Privadas (PM)**

Aqui ocorre a maior distinção entre as camadas.

## 🆔 **3.1 Amizade de Usuário (BattleTag / ID)**
*   **Conexão:** Feita entre contas.
*   **Visibilidade:**
    *   Você vê quando seu amigo está online.
    *   Você vê **em qual personagem** ele está logado (se a privacidade permitir).
*   **Chat Privado (Whisper):**
    *   A mensagem chega para o **Usuário**, independente do personagem que ele esteja usando.
    *   O histórico de conversa persiste entre trocas de personagens.

## 🎭 **3.2 Contato de Player (Agenda do Personagem)**
*   **Conexão:** Feita entre personagens dentro do jogo (ex: trocar cartões de visita ou adicionar aos contatos).
*   **Visibilidade:**
    *   Você só sabe se aquele **personagem específico** está online.
    *   Se o Usuário logar em outro personagem (alt), o contato aparece como **Offline**.
*   **Chat Privado (Whisper):**
    *   Enviado para o **Player**.
    *   Se o alvo trocar de personagem, a comunicação é cortada ("Personagem não encontrado").

---

# 🚫 **4. Block e Moderacão**

*   **Bloqueio de Usuário:** Impede qualquer comunicação de **qualquer personagem** daquela conta.
*   **Bloqueio de Player:** Impede comunicação apenas daquele personagem específico (útil para RP, "não falo mais com você").

---

# 📝 **5. Resumo da Lógica**

| Funcionalidade | Nível Usuário (Conta) | Nível Player (Personagem) |
| :--- | :--- | :--- |
| **Identidade** | Oculta (exceto amigos) | Pública |
| **Lista de Amigos** | Vê status global da conta | Vê apenas status do char |
| **Chat Privado** | Persiste entre chars | Limitado ao char logado |
| **Guilda/Grupo** | N/A | Vínculo exclusivo do char |

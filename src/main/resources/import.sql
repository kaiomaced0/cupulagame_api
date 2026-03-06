-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database

-- ==================== COLOR MATERIALS (MOVIDO PARA MyInitializer.java) ====================
-- Os inserts de ColorMaterial foram movidos para o MyInitializer.java para serem criados programaticamente.
-- Isso permite melhor controle de dependências e consistência com os outros seeds.

-- ==================== PLAYER TIPO (MOVIDO PARA MyInitializer.java) ====================
-- Os inserts abaixo foram movidos para o MyInitializer.java para serem criados programaticamente.
-- Isso permite melhor controle de dependências e evita problemas com IDs auto-gerados.

-- PlayerTipoBaseColorSkin - Cores de pele possíveis por raça (MOVIDO PARA MyInitializer)
-- INSERT INTO player_tipo_base_color_skin (id, playerRaca, possibilidade, color_material_id, ativo, dataInclusao) VALUES ...

-- PlayerTipoBaseTamanho - Tamanhos base por raça (MOVIDO PARA MyInitializer)
-- INSERT INTO player_tipo_base_tamanho (id, playerRaca, possibilidade, eixoXMinimo, eixoXMaximo, eixoYMinimo, eixoYMaximo, eixoZMinimo, eixoZMaximo, ativo, dataInclusao) VALUES ...

-- TipoOrelhaColorMaterial - Cores de orelhas possíveis (MOVIDO PARA MyInitializer)
-- INSERT INTO tipo_orelha_color_material (id, color_material_id, possibilidade, ativo, dataInclusao) VALUES ...

-- PlayerTipoBaseOrelha - Configuração de orelhas por raça (MOVIDO PARA MyInitializer)
-- INSERT INTO player_tipo_base_orelha (id, playerRaca, possibilidade, eixoXMinimo, eixoXMaximo, eixoYMinimo, eixoYMaximo, eixoZMinimo, eixoZMaximo, ativo, dataInclusao) VALUES ...

-- PlayerTipoBaseOrelha <-> TipoOrelhaColorMaterial (ManyToMany) (MOVIDO PARA MyInitializer)
-- INSERT INTO player_tipo_base_orelha_color_material (player_tipo_base_orelha_id, tipo_orelha_color_material_id) VALUES ...

-- PlayerTipoCabelo - Tipos de cabelo por raça (MOVIDO PARA MyInitializer)
-- INSERT INTO player_tipo_cabelo (id, nome, path, possibilidade, playerRaca, ativo, dataInclusao) VALUES ...

-- ==================== ITEMS (MOVIDO PARA MyInitializer) ====================

-- ItemTipo - Tipos base de itens (MOVIDO PARA MyInitializer)
-- Os 80 tipos de itens foram movidos para seedItemTipos() no MyInitializer.java
-- Isso permite melhor controle de dependências e facilita a manutenção

-- ==================== ITEM STRUCTURES ====================

-- BaseItemStructure - Estruturas base para geração procedural de itens
INSERT INTO base_item_structure (id, base_item_structure_id, possibilidade, ativo, dataInclusao) VALUES
(1, 1, 100, true, NOW()), -- Espada de Ferro - estrutura padrão
(2, 2, 80, true, NOW()),  -- Espada de Aço - variação comum
(3, 2, 20, true, NOW()),  -- Espada de Aço - variação rara
(4, 3, 100, true, NOW()), -- Machado - estrutura única
(5, 4, 100, true, NOW()), -- Arco - estrutura única
(6, 5, 70, true, NOW()),  -- Escudo de Madeira - comum
(7, 5, 30, true, NOW()),  -- Escudo de Madeira - reforçado
(8, 7, 100, true, NOW()), -- Elmo - estrutura padrão
(9, 8, 100, true, NOW()), -- Armadura de Couro - estrutura padrão
(10, 9, 100, true, NOW()); -- Armadura de Ferro - estrutura padrão

-- ColorPossibility - Possibilidades de cores para partes dos itens
INSERT INTO color_possibility (id, possibilidade, ativo, dataInclusao) VALUES
(1, 100, true, NOW()), -- Ferro padrão
(2, 80, true, NOW()),  -- Aço comum
(3, 15, true, NOW()),  -- Aço com detalhes dourados (raro)
(4, 5, true, NOW()),   -- Aço totalmente dourado (muito raro)
(5, 100, true, NOW()), -- Madeira clara
(6, 100, true, NOW()), -- Madeira escura
(7, 100, true, NOW()), -- Couro marrom
(8, 50, true, NOW()),  -- Couro preto (menos comum)
(9, 100, true, NOW()), -- Tecido básico
(10, 30, true, NOW()); -- Tecido especial

-- ColorPossibility <-> ColorMaterial (ManyToMany)
INSERT INTO color_possibility_color_material (color_possibility_id, color_material_id) VALUES
-- Cores de ferro/aço (espadas, armaduras)
(1, 15), -- Ferro padrão
(2, 16), -- Aço comum
(3, 16), (3, 17), -- Aço com detalhes de ouro
(4, 17), -- Ouro total (muito raro)
-- Cores de madeira (cabos, escudos, arcos)
(5, 13), -- Madeira clara
(6, 14), -- Madeira escura
-- Cores de couro (armaduras leves)
(7, 19), -- Couro marrom
(8, 20), -- Couro preto
-- Cores de tecido (capas, detalhes)
(9, 21), (9, 22), (9, 23), -- Mix de tecidos
(10, 22), (10, 17); -- Tecido azul com fios dourados

-- ==================== TEXTURAS E MATERIAIS ====================

-- Texture - Texturas para renderização
INSERT INTO texture (id, name, path, ativo, dataInclusao) VALUES
(1, 'Madeira Lisa', '/textures/wood/smooth.png', true, NOW()),
(2, 'Madeira Rústica', '/textures/wood/rough.png', true, NOW()),
(3, 'Madeira com Nós', '/textures/wood/knotty.png', true, NOW()),
(4, 'Metal Polido', '/textures/metal/polished.png', true, NOW()),
(5, 'Metal Fosco', '/textures/metal/matte.png', true, NOW()),
(6, 'Metal Enferrujado', '/textures/metal/rusty.png', true, NOW()),
(7, 'Ouro Brilhante', '/textures/metal/gold_shiny.png', true, NOW()),
(8, 'Couro Liso', '/textures/leather/smooth.png', true, NOW()),
(9, 'Couro Curtido', '/textures/leather/tanned.png', true, NOW()),
(10, 'Couro Rachado', '/textures/leather/cracked.png', true, NOW()),
(11, 'Tecido Simples', '/textures/cloth/plain.png', true, NOW()),
(12, 'Tecido Bordado', '/textures/cloth/embroidered.png', true, NOW()),
(13, 'Seda', '/textures/cloth/silk.png', true, NOW()),
(14, 'Pedra Lisa', '/textures/stone/smooth.png', true, NOW()),
(15, 'Pedra Bruta', '/textures/stone/rough.png', true, NOW());

-- Material - Materiais de construção e craft
INSERT INTO Material (id, name, item_tipo_id, quantidadeItemPorPixel, resistenciaFogo, resistenciaAgua, resistenciaImpacto, ativo, dataInclusao) VALUES
-- Madeiras
(1, 'Madeira de Carvalho', 13, 10, 20, 40, 50, true, NOW()),
(2, 'Madeira de Pinho', 13, 12, 15, 30, 40, true, NOW()),
(3, 'Madeira Antiga', 13, 8, 25, 50, 60, true, NOW()),
-- Metais
(4, 'Ferro Bruto', 14, 5, 80, 90, 100, true, NOW()),
(5, 'Aço Temperado', 14, 4, 90, 95, 120, true, NOW()),
(6, 'Ouro Puro', 12, 2, 70, 100, 40, true, NOW()),
(7, 'Bronze', 14, 6, 75, 85, 80, true, NOW()),
-- Couros
(8, 'Couro Comum', NULL, 15, 10, 60, 30, true, NOW()),
(9, 'Couro Reforçado', NULL, 10, 20, 70, 50, true, NOW()),
(10, 'Couro Dragão', NULL, 5, 95, 90, 85, true, NOW()),
-- Tecidos
(11, 'Linho', NULL, 20, 5, 20, 10, true, NOW()),
(12, 'Algodão', NULL, 18, 8, 25, 12, true, NOW()),
(13, 'Seda Élfica', NULL, 8, 30, 40, 20, true, NOW()),
-- Pedras
(14, 'Granito', NULL, 3, 100, 100, 150, true, NOW()),
(15, 'Mármore', NULL, 4, 90, 95, 130, true, NOW());

-- Material <-> Texture (ManyToMany)
INSERT INTO material_textures (material_id, texture_id) VALUES
-- Madeiras
(1, 1), (1, 2), -- Carvalho: lisa e rústica
(2, 2), (2, 3), -- Pinho: rústica e com nós
(3, 3), (3, 6), -- Madeira antiga: nós e enferrujada
-- Metais
(4, 5), (4, 6), -- Ferro: fosco e enferrujado
(5, 4), (5, 5), -- Aço: polido e fosco
(6, 7), (6, 4), -- Ouro: brilhante e polido
(7, 5), (7, 6), -- Bronze: fosco e enferrujado
-- Couros
(8, 8), (8, 9), -- Couro comum: liso e curtido
(9, 9), (9, 10), -- Couro reforçado: curtido e rachado
(10, 8), (10, 9), -- Couro dragão: liso e curtido
-- Tecidos
(11, 11), -- Linho: simples
(12, 11), (12, 12), -- Algodão: simples e bordado
(13, 13), (13, 12), -- Seda: seda e bordado
-- Pedras
(14, 14), (14, 15), -- Granito: lisa e bruta
(15, 14); -- Mármore: lisa

-- Material <-> ColorMaterial (ManyToMany)
INSERT INTO material_color_materials (material_id, color_material_id) VALUES
-- Madeiras
(1, 13), (1, 14), -- Carvalho: clara e escura
(2, 13), -- Pinho: clara
(3, 14), -- Madeira antiga: escura
-- Metais
(4, 15), -- Ferro: cinza
(5, 16), -- Aço: prateado
(6, 17), -- Ouro: dourado
(7, 18), -- Bronze: bronze
-- Couros
(8, 19), (8, 20), -- Couro comum: marrom e preto
(9, 19), (9, 20), -- Couro reforçado: marrom e preto
(10, 20), -- Couro dragão: preto
-- Tecidos
(11, 21), -- Linho: branco
(12, 21), (12, 22), (12, 23), -- Algodão: branco, azul, vermelho
(13, 21), (13, 17); -- Seda: branco e dourado

-- ==================== USUÁRIOS E PLAYERS (SEED EXEMPLO) ====================
-- ===================================================================
-- SEEDS PROGRAMÁTICOS - Os dados abaixo foram movidos para MyInitializer
-- ===================================================================
-- As inserções de Pessoa, Usuario e Player agora são feitas programaticamente
-- na classe org.cupula.initializer.MyInitializer
-- Isso permite melhor controle, validações e facilita a manutenção dos seeds

-- COMENTADO: Seeds antigos de Pessoa, Usuario e Player
-- Agora gerenciados por MyInitializer.java com @Startup

-- Senha padrão (HashService.main para "123456"):
-- nKfKWJJt4zqpQDV0/F3CM5bwcocTBj12OPQD9EV0OjaNPM/QJyxPVhZ/aAmExNkfUFdT/9mHL7jEPj85uFTmXw==

-- INSERT INTO pessoa (id, nomeCompleto, cpf, dataNascimento, sexo, ativo, dataInclusao) VALUES
-- (1, 'Kaio Macedo', '11111111111', '1992-03-15', 'MASCULINO', true, NOW()),
-- (2, 'Ana Clara Marinho', '22222222222', '1995-07-10', 'FEMININO', true, NOW()),
-- (3, 'Rafael Aguiar', '33333333333', '1990-01-21', 'MASCULINO', true, NOW()),
-- (4, 'David Silva', '44444444444', '1998-11-03', 'MASCULINO', true, NOW()),
-- (5, 'Diego Dias', '55555555555', '1993-05-29', 'MASCULINO', true, NOW());

-- INSERT INTO usuario (id, email, senha, nickname, mudarSenha, login_local_habilitado, pessoa_id, ativo, dataInclusao) VALUES
-- (1, '0Glacks@cupula.dev', 'nKfKWJJt4zqpQDV0/F3CM5bwcocTBj12OPQD9EV0OjaNPM/QJyxPVhZ/aAmExNkfUFdT/9mHL7jEPj85uFTmXw==', '0Glacks', false, true, 1, true, NOW()),
-- (2, 'aaa123@cupula.dev', 'nKfKWJJt4zqpQDV0/F3CM5bwcocTBj12OPQD9EV0OjaNPM/QJyxPVhZ/aAmExNkfUFdT/9mHL7jEPj85uFTmXw==', 'aaa123', false, true, 2, true, NOW()),
-- (3, 'bbb123@cupula.dev', 'nKfKWJJt4zqpQDV0/F3CM5bwcocTBj12OPQD9EV0OjaNPM/QJyxPVhZ/aAmExNkfUFdT/9mHL7jEPj85uFTmXw==', 'bbb123', false, true, 3, true, NOW()),
-- (4, 'ccc123@cupula.dev', 'nKfKWJJt4zqpQDV0/F3CM5bwcocTBj12OPQD9EV0OjaNPM/QJyxPVhZ/aAmExNkfUFdT/9mHL7jEPj85uFTmXw==', 'ccc123', false, true, 4, true, NOW()),
-- (5, 'ddd123@cupula.dev', 'nKfKWJJt4zqpQDV0/F3CM5bwcocTBj12OPQD9EV0OjaNPM/QJyxPVhZ/aAmExNkfUFdT/9mHL7jEPj85uFTmXw==', 'ddd123', false, true, 5, true, NOW());

-- INSERT INTO usuario_perfil (id_usuario, perfil) VALUES
-- (1, 'USER'),
-- (2, 'USER'),
-- (3, 'USER'),
-- (4, 'USER'),
-- (5, 'USER');

-- Distribuição solicitada:
-- Usuários com 1 player: 0Glacks, aaa123
-- Usuários com 2 players: bbb123, ccc123, ddd123
-- INSERT INTO player (
-- 	id, usuario_id, visibilidade, raca,
-- 	tamanhoX, tamanhoY, tamanhoZ,
-- 	tipo_cabelo_id, cor_pele_id,
-- 	tamanhoXOrelha, tamanhoYOrelha, tamanhoZOrelha, cor_orelha_id,
-- 	nickName, tag, ultimaAlteracaoNickName,
-- 	ativo, dataInclusao
-- ) VALUES
-- -- user1 (1 player)
-- (1, 1, 'AMIGOS', 'HUMANO', 172, 68, 39, 5, 2, 4, 7, 3, 9, 'u1_main', 'U1A', NOW(), true, NOW()),

-- -- user2 (1 player)
-- (2, 2, 'AMIGOS', 'ELFO', 198, 52, 30, 11, 1, 5, 14, 2, 11, 'u2_main', 'U2A', NOW(), true, NOW()),

-- -- user3 (2 players)
-- (3, 3, 'AMIGOS', 'ORC', 196, 118, 58, 7, 6, 7, 11, 5, 10, 'u3_war', 'U3A', NOW(), true, NOW()),
-- (4, 3, 'PUBLICO', 'ANAO', 136, 85, 48, 2, 3, 3, 6, 3, 10, 'u3_smith', 'U3B', NOW(), true, NOW()),

-- -- user4 (2 players)
-- (5, 4, 'AMIGOS', 'HUMANO', 178, 72, 42, 4, 4, 4, 8, 3, 8, 'u4_guard', 'U4A', NOW(), true, NOW()),
-- (6, 4, 'PRIVADO', 'ELFO', 205, 50, 31, 10, 2, 6, 16, 3, 12, 'u4_arch', 'U4B', NOW(), true, NOW()),

-- -- user5 (2 players)
-- (7, 5, 'AMIGOS', 'ANAO', 142, 88, 50, 1, 3, 3, 5, 3, 9, 'u5_tank', 'U5A', NOW(), true, NOW()),
-- (8, 5, 'PUBLICO', 'ORC', 202, 125, 62, 9, 5, 8, 12, 6, 10, 'u5_hunt', 'U5B', NOW(), true, NOW());
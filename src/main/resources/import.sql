-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database

-- ColorMaterial - Cores e materiais para personagens
INSERT INTO color_material (id, name, hexCode, tipo, ativo, dataInclusao) VALUES 
(1, 'Pele Clara', '#F5D5C5', 'PELE', true, NOW()),
(2, 'Pele Média', '#D4A574', 'PELE', true, NOW()),
(3, 'Pele Escura', '#8D5524', 'PELE', true, NOW()),
(4, 'Pele Negra', '#4A2511', 'PELE', true, NOW()),
(5, 'Pele Verde Clara', '#8FBC8F', 'PELE', true, NOW()),
(6, 'Pele Verde Escura', '#556B2F', 'PELE', true, NOW()),
(7, 'Pele Cinza', '#696969', 'PELE', true, NOW()),
(8, 'Orelha Natural Clara', '#F5D5C5', 'ORELHA', true, NOW()),
(9, 'Orelha Natural Média', '#D4A574', 'ORELHA', true, NOW()),
(10, 'Orelha Natural Escura', '#8D5524', 'ORELHA', true, NOW()),
(11, 'Orelha Élfica Dourada', '#FFD700', 'ORELHA', true, NOW()),
(12, 'Orelha Élfica Prateada', '#C0C0C0', 'ORELHA', true, NOW()),
-- Cores adicionais para itens
(13, 'Madeira Clara', '#D2B48C', 'MATERIAL', true, NOW()),
(14, 'Madeira Escura', '#8B4513', 'MATERIAL', true, NOW()),
(15, 'Ferro', '#696969', 'MATERIAL', true, NOW()),
(16, 'Aço', '#C0C0C0', 'MATERIAL', true, NOW()),
(17, 'Ouro', '#FFD700', 'MATERIAL', true, NOW()),
(18, 'Bronze', '#CD7F32', 'MATERIAL', true, NOW()),
(19, 'Couro Marrom', '#8B4513', 'MATERIAL', true, NOW()),
(20, 'Couro Preto', '#1C1C1C', 'MATERIAL', true, NOW()),
(21, 'Tecido Branco', '#F5F5F5', 'MATERIAL', true, NOW()),
(22, 'Tecido Azul', '#4169E1', 'MATERIAL', true, NOW()),
(23, 'Tecido Vermelho', '#DC143C', 'MATERIAL', true, NOW());

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

-- ==================== ITEMS ====================

-- ItemTipo - Tipos base de itens
INSERT INTO item_tipo (id, nome, descricao, limiteQuantidade, pesoPorQuantidade, ativo, dataInclusao) VALUES
-- ARMAS CORPO A CORPO (1-10)
(1, 'Espada de Ferro', 'Espada básica forjada em ferro', 1, 150, true, NOW()),
(2, 'Espada de Aço', 'Espada resistente de aço', 1, 180, true, NOW()),
(3, 'Espada Longa', 'Espada de duas mãos com grande alcance', 1, 250, true, NOW()),
(4, 'Adaga', 'Lâmina curta e ágil', 1, 50, true, NOW()),
(5, 'Machado de Batalha', 'Machado pesado de guerra', 1, 200, true, NOW()),
(6, 'Machado de Madeira', 'Machado simples com cabo de madeira', 1, 120, true, NOW()),
(7, 'Lança', 'Arma de haste com ponta afiada', 1, 180, true, NOW()),
(8, 'Martelo de Guerra', 'Martelo maciço para combate', 1, 300, true, NOW()),
(9, 'Foice', 'Lâmina curva montada em cabo longo', 1, 140, true, NOW()),
(10, 'Katana', 'Espada curva oriental afiada', 1, 160, true, NOW()),

-- ARMAS À DISTÂNCIA (11-15)
(11, 'Arco Curto', 'Arco leve e rápido', 1, 60, true, NOW()),
(12, 'Arco Longo', 'Arco de madeira para longa distância', 1, 80, true, NOW()),
(13, 'Besta', 'Arco mecânico potente', 1, 150, true, NOW()),
(14, 'Zarabatana', 'Tubo para disparar dardos', 1, 30, true, NOW()),
(15, 'Shuriken', 'Estrela de arremesso ninja', 50, 5, true, NOW()),

-- ESCUDOS (16-20)
(16, 'Escudo de Madeira', 'Escudo básico de madeira reforçada', 1, 200, true, NOW()),
(17, 'Escudo de Ferro', 'Escudo robusto de ferro', 1, 300, true, NOW()),
(18, 'Escudo Grande', 'Escudo de corpo inteiro', 1, 400, true, NOW()),
(19, 'Escudo Redondo', 'Escudo circular ágil', 1, 150, true, NOW()),
(20, 'Tarja', 'Pequeno escudo de braço', 1, 100, true, NOW()),

-- ARMADURAS CABEÇA (21-25)
(21, 'Elmo de Ferro', 'Capacete básico de ferro', 1, 100, true, NOW()),
(22, 'Elmo de Aço', 'Capacete reforçado de aço', 1, 120, true, NOW()),
(23, 'Capuz de Couro', 'Proteção leve para a cabeça', 1, 40, true, NOW()),
(24, 'Coroa de Ouro', 'Ornamento régio dourado', 1, 200, true, NOW()),
(25, 'Máscara Ninja', 'Máscara furtiva preta', 1, 20, true, NOW()),

-- ARMADURAS CORPO (26-30)
(26, 'Armadura de Couro', 'Armadura leve de couro', 1, 250, true, NOW()),
(27, 'Armadura de Ferro', 'Armadura pesada de ferro', 1, 500, true, NOW()),
(28, 'Cota de Malha', 'Armadura de anéis entrelaçados', 1, 350, true, NOW()),
(29, 'Armadura de Aço', 'Armadura completa de aço', 1, 600, true, NOW()),
(30, 'Túnica de Mago', 'Veste mágica encantada', 1, 80, true, NOW()),

-- ARMADURAS PERNAS (31-35)
(31, 'Calças de Couro', 'Proteção leve para pernas', 1, 100, true, NOW()),
(32, 'Perneiras de Ferro', 'Proteção pesada de ferro', 1, 200, true, NOW()),
(33, 'Calças de Tecido', 'Calças simples de tecido', 1, 30, true, NOW()),
(34, 'Botas de Couro', 'Botas resistentes de couro', 1, 80, true, NOW()),
(35, 'Botas de Ferro', 'Botas pesadas blindadas', 1, 150, true, NOW()),

-- ACESSÓRIOS (36-40)
(36, 'Anel de Ouro', 'Anel valioso dourado', 10, 2, true, NOW()),
(37, 'Colar de Prata', 'Colar elegante prateado', 10, 5, true, NOW()),
(38, 'Amuleto Mágico', 'Talismã com poder arcano', 1, 3, true, NOW()),
(39, 'Cinto de Couro', 'Cinto resistente com fivela', 1, 20, true, NOW()),
(40, 'Mochila', 'Bolsa para carregar itens', 1, 50, true, NOW()),

-- CONSUMÍVEIS (41-50)
(41, 'Poção de Vida Pequena', 'Restaura 50 HP', 99, 5, true, NOW()),
(42, 'Poção de Vida', 'Restaura 150 HP', 99, 8, true, NOW()),
(43, 'Poção de Vida Grande', 'Restaura 300 HP', 99, 12, true, NOW()),
(44, 'Poção de Mana Pequena', 'Restaura 30 MP', 99, 5, true, NOW()),
(45, 'Poção de Mana', 'Restaura 100 MP', 99, 8, true, NOW()),
(46, 'Poção de Mana Grande', 'Restaura 200 MP', 99, 12, true, NOW()),
(47, 'Poção de Energia', 'Restaura stamina', 99, 6, true, NOW()),
(48, 'Antídoto', 'Cura envenenamento', 99, 4, true, NOW()),
(49, 'Pão', 'Alimento básico nutritivo', 99, 2, true, NOW()),
(50, 'Carne Assada', 'Carne cozida restauradora', 99, 4, true, NOW()),

-- MATERIAIS DE CRAFT (51-65)
(51, 'Madeira', 'Tábuas de madeira para construção', 999, 10, true, NOW()),
(52, 'Tora de Madeira', 'Madeira bruta cortada', 999, 15, true, NOW()),
(53, 'Minério de Ferro', 'Minério bruto de ferro', 999, 20, true, NOW()),
(54, 'Barra de Ferro', 'Ferro processado', 999, 25, true, NOW()),
(55, 'Minério de Ouro', 'Minério bruto de ouro', 999, 18, true, NOW()),
(56, 'Barra de Ouro', 'Ouro puro refinado', 999, 22, true, NOW()),
(57, 'Carvão', 'Combustível mineral', 999, 8, true, NOW()),
(58, 'Pedra', 'Blocos de pedra', 999, 30, true, NOW()),
(59, 'Areia', 'Areia fina para construção', 999, 5, true, NOW()),
(60, 'Argila', 'Barro moldável', 999, 12, true, NOW()),
(61, 'Couro', 'Pele curtida de animal', 999, 6, true, NOW()),
(62, 'Tecido', 'Tecido básico de algodão', 999, 3, true, NOW()),
(63, 'Corda', 'Corda resistente de fibra', 999, 3, true, NOW()),
(64, 'Prego', 'Prego de metal', 999, 1, true, NOW()),
(65, 'Cola', 'Substância adesiva', 999, 2, true, NOW()),

-- GEMAS E TESOUROS (66-75)
(66, 'Pedra Preciosa', 'Gema valiosa para comércio', 999, 1, true, NOW()),
(67, 'Rubi', 'Gema vermelha rara', 99, 1, true, NOW()),
(68, 'Safira', 'Gema azul valiosa', 99, 1, true, NOW()),
(69, 'Esmeralda', 'Gema verde reluzente', 99, 1, true, NOW()),
(70, 'Diamante', 'Gema transparente raríssima', 50, 1, true, NOW()),
(71, 'Pérola', 'Gema oceânica iridescente', 99, 1, true, NOW()),
(72, 'Moeda de Ouro', 'Moeda valiosa', 9999, 0, true, NOW()),
(73, 'Moeda de Prata', 'Moeda comum', 9999, 0, true, NOW()),
(74, 'Relíquia Antiga', 'Artefato histórico valioso', 1, 10, true, NOW()),
(75, 'Cristal Mágico', 'Cristal com energia arcana', 50, 2, true, NOW()),

-- FERRAMENTAS (76-80)
(76, 'Picareta de Ferro', 'Ferramenta para minerar', 1, 180, true, NOW()),
(77, 'Machado de Lenhador', 'Ferramenta para cortar árvores', 1, 160, true, NOW()),
(78, 'Pá', 'Ferramenta para cavar', 1, 120, true, NOW()),
(79, 'Foice de Colheita', 'Ferramenta agrícola', 1, 100, true, NOW()),
(80, 'Anzol', 'Ferramenta de pesca', 10, 5, true, NOW());

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
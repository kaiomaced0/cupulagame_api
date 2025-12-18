-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database

-- ColorMaterial - Cores e materiais para personagens
INSERT INTO ColorMaterial (id, name, hexCode, tipo, ativo, dataInclusao) VALUES 
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

-- PlayerTipoBaseColorSkin - Cores de pele possíveis por raça
INSERT INTO PlayerTipoBaseColorSkin (id, playerRaca, possibilidade, color_material_id, ativo, dataInclusao) VALUES
-- HUMANO - Todas as variações naturais
(1, 'HUMANO', 20, 1, true, NOW()),  -- Pele Clara
(2, 'HUMANO', 30, 2, true, NOW()),  -- Pele Média
(3, 'HUMANO', 30, 3, true, NOW()),  -- Pele Escura
(4, 'HUMANO', 20, 4, true, NOW()),  -- Pele Negra

-- ELFO - Predominantemente tons claros
(5, 'ELFO', 40, 1, true, NOW()),    -- Pele Clara (mais comum)
(6, 'ELFO', 35, 2, true, NOW()),    -- Pele Média
(7, 'ELFO', 20, 3, true, NOW()),    -- Pele Escura (raro)
(8, 'ELFO', 5, 4, true, NOW()),     -- Pele Negra (muito raro)

-- ANÃO - Predominantemente tons médios/escuros
(9, 'ANAO', 15, 1, true, NOW()),    -- Pele Clara (raro)
(10, 'ANAO', 30, 2, true, NOW()),   -- Pele Média
(11, 'ANAO', 35, 3, true, NOW()),   -- Pele Escura (mais comum)
(12, 'ANAO', 20, 4, true, NOW()),   -- Pele Negra

-- ORC - Tons verdes e cinzas (característico)
(13, 'ORC', 30, 5, true, NOW()),    -- Pele Verde Clara
(14, 'ORC', 40, 6, true, NOW()),    -- Pele Verde Escura (mais comum)
(15, 'ORC', 25, 7, true, NOW()),    -- Pele Cinza
(16, 'ORC', 5, 3, true, NOW());     -- Pele Escura Natural (raro)

-- PlayerTipoBaseTamanho - Tamanhos base por raça
INSERT INTO PlayerTipoBaseTamanho (id, playerRaca, possibilidade, eixoXMinimo, eixoXMaximo, eixoYMinimo, eixoYMaximo, eixoZMinimo, eixoZMaximo, ativo, dataInclusao) VALUES
-- ANÃO - Pequeno e robusto (120-150cm altura)
(1, 'ANAO', 20, 120, 130, 70, 100, 40, 60, true, NOW()),  -- Anão Atarracado
(2, 'ANAO', 35, 130, 140, 65, 95, 38, 58, true, NOW()),   -- Anão Robusto
(3, 'ANAO', 30, 140, 150, 60, 90, 35, 55, true, NOW()),   -- Anão Alto
(4, 'ANAO', 15, 125, 135, 75, 105, 42, 62, true, NOW()),  -- Anão Muito Largo

-- HUMANO - Médio e equilibrado (160-190cm altura)
(5, 'HUMANO', 15, 160, 170, 50, 70, 30, 45, true, NOW()), -- Humano Magro
(6, 'HUMANO', 30, 165, 178, 55, 75, 32, 48, true, NOW()), -- Humano Médio
(7, 'HUMANO', 30, 172, 185, 58, 80, 35, 50, true, NOW()), -- Humano Atlético
(8, 'HUMANO', 15, 175, 190, 65, 90, 38, 55, true, NOW()), -- Humano Forte
(9, 'HUMANO', 10, 160, 168, 70, 95, 40, 58, true, NOW()), -- Humano Robusto

-- ORC - Alto e muito largo (180-220cm altura, mais largo que humano)
(10, 'ORC', 20, 180, 195, 90, 130, 45, 75, true, NOW()),  -- Orc Guerreiro
(11, 'ORC', 35, 190, 205, 95, 135, 48, 78, true, NOW()),  -- Orc Forte
(12, 'ORC', 25, 200, 215, 100, 140, 50, 80, true, NOW()), -- Orc Gigante
(13, 'ORC', 20, 185, 200, 105, 145, 52, 82, true, NOW()), -- Orc Colossal

-- ELFO - Bem mais alto e esbelto (185-215cm altura, mais magro)
(14, 'ELFO', 25, 185, 195, 40, 60, 25, 40, true, NOW()),  -- Elfo Esbelto
(15, 'ELFO', 35, 195, 205, 43, 63, 27, 42, true, NOW()),  -- Elfo Alto
(16, 'ELFO', 25, 200, 210, 45, 65, 28, 44, true, NOW()),  -- Elfo Muito Alto
(17, 'ELFO', 15, 205, 215, 48, 68, 30, 46, true, NOW());  -- Elfo Majestoso

-- TipoOrelhaColorMaterial - Cores de orelhas possíveis
INSERT INTO TipoOrelhaColorMaterial (id, color_material_id, possibilidade, ativo, dataInclusao) VALUES
(1, 8, 25, true, NOW()),   -- Orelha Natural Clara
(2, 9, 25, true, NOW()),   -- Orelha Natural Média
(3, 10, 25, true, NOW()),  -- Orelha Natural Escura
(4, 11, 15, true, NOW()),  -- Orelha Élfica Dourada
(5, 12, 10, true, NOW());  -- Orelha Élfica Prateada

-- PlayerTipoBaseOrelha - Configuração de orelhas por raça
INSERT INTO PlayerTipoBaseOrelha (id, playerRaca, possibilidade, eixoXMinimo, eixoXMaximo, eixoYMinimo, eixoYMaximo, eixoZMinimo, eixoZMaximo, ativo, dataInclusao) VALUES
-- ANÃO - Orelhas pequenas e arredondadas
(1, 'ANAO', 35, 2, 3, 4, 5, 2, 3, true, NOW()),   -- Orelha Anã Pequena
(2, 'ANAO', 40, 3, 4, 4, 6, 2, 3, true, NOW()),   -- Orelha Anã Normal
(3, 'ANAO', 25, 2, 4, 5, 7, 2, 3, true, NOW()),   -- Orelha Anã Destacada

-- HUMANO - Orelhas médias e variadas
(4, 'HUMANO', 25, 3, 4, 5, 7, 2, 3, true, NOW()), -- Orelha Humana Pequena
(5, 'HUMANO', 40, 3, 5, 6, 8, 2, 4, true, NOW()), -- Orelha Humana Normal
(6, 'HUMANO', 25, 4, 6, 6, 9, 3, 4, true, NOW()), -- Orelha Humana Grande
(7, 'HUMANO', 10, 3, 5, 5, 7, 2, 3, true, NOW()), -- Orelha Humana Colada

-- ORC - Orelhas grandes, pontiagudas e grossas
(8, 'ORC', 30, 5, 7, 7, 10, 3, 5, true, NOW()),   -- Orelha Orc Normal
(9, 'ORC', 35, 6, 8, 8, 12, 4, 6, true, NOW()),   -- Orelha Orc Grande
(10, 'ORC', 25, 7, 9, 9, 13, 4, 6, true, NOW()),  -- Orelha Orc Gigante
(11, 'ORC', 10, 5, 8, 6, 9, 3, 5, true, NOW()),   -- Orelha Orc Larga

-- ELFO - Orelhas longas e pontiagudas (característica marcante)
(12, 'ELFO', 20, 4, 6, 10, 14, 2, 3, true, NOW()), -- Orelha Élfica Alongada
(13, 'ELFO', 35, 5, 7, 12, 16, 2, 3, true, NOW()), -- Orelha Élfica Longa
(14, 'ELFO', 30, 5, 8, 14, 18, 2, 3, true, NOW()), -- Orelha Élfica Muito Longa
(15, 'ELFO', 15, 6, 8, 16, 20, 2, 3, true, NOW()); -- Orelha Élfica Majestosa

-- PlayerTipoBaseOrelha <-> TipoOrelhaColorMaterial (ManyToMany)
INSERT INTO player_tipo_base_orelha_color_material (player_tipo_base_orelha_id, tipo_orelha_color_material_id) VALUES
-- ANÃO - Tons naturais (clara, média, escura)
(1, 1), (1, 2), (1, 3),  -- Orelha Anã Pequena
(2, 1), (2, 2), (2, 3),  -- Orelha Anã Normal
(3, 1), (3, 2), (3, 3),  -- Orelha Anã Destacada

-- HUMANO - Todos os tons naturais
(4, 1), (4, 2), (4, 3),  -- Orelha Humana Pequena
(5, 1), (5, 2), (5, 3),  -- Orelha Humana Normal
(6, 1), (6, 2), (6, 3),  -- Orelha Humana Grande
(7, 1), (7, 2), (7, 3),  -- Orelha Humana Colada

-- ORC - Tons escuros e verdes
(8, 2), (8, 3),          -- Orelha Orc Normal
(9, 2), (9, 3),          -- Orelha Orc Grande
(10, 2), (10, 3),        -- Orelha Orc Gigante
(11, 2), (11, 3),        -- Orelha Orc Larga

-- ELFO - Tons claros e especiais (dourado, prateado)
(12, 1), (12, 2), (12, 4), (12, 5),  -- Orelha Élfica Alongada
(13, 1), (13, 2), (13, 4), (13, 5),  -- Orelha Élfica Longa
(14, 1), (14, 2), (14, 4), (14, 5),  -- Orelha Élfica Muito Longa
(15, 1), (15, 2), (15, 4), (15, 5);  -- Orelha Élfica Majestosa

-- PlayerTipoCabelo - Tipos de cabelo por raça
INSERT INTO PlayerTipoCabelo (id, nome, path, possibilidade, playerRaca, ativo, dataInclusao) VALUES
-- ANÃO - Cabelos robustos e volumosos
(1, 'Barba Longa Anã', '/hair/dwarf/long_beard.obj', 40, 'ANAO', true, NOW()),
(2, 'Barba Trançada', '/hair/dwarf/braided_beard.obj', 35, 'ANAO', true, NOW()),
(3, 'Cabelo Curto com Barba', '/hair/dwarf/short_with_beard.obj', 25, 'ANAO', true, NOW()),

-- HUMANO - Cabelos variados e versáteis
(4, 'Cabelo Curto', '/hair/human/short_hair.obj', 30, 'HUMANO', true, NOW()),
(5, 'Cabelo Médio', '/hair/human/medium_hair.obj', 40, 'HUMANO', true, NOW()),
(6, 'Cabelo Longo', '/hair/human/long_hair.obj', 30, 'HUMANO', true, NOW()),

-- ORC - Cabelos selvagens e guerreiros
(7, 'Moicano Orc', '/hair/orc/mohawk.obj', 35, 'ORC', true, NOW()),
(8, 'Cabelo Espetado', '/hair/orc/spiked_hair.obj', 30, 'ORC', true, NOW()),
(9, 'Crinas Longas', '/hair/orc/long_mane.obj', 35, 'ORC', true, NOW()),

-- ELFO - Cabelos elegantes e fluidos
(10, 'Cabelo Liso Longo', '/hair/elf/straight_long.obj', 35, 'ELFO', true, NOW()),
(11, 'Cabelo Ondulado', '/hair/elf/wavy_hair.obj', 40, 'ELFO', true, NOW()),
(12, 'Tranças Élficas', '/hair/elf/elven_braids.obj', 25, 'ELFO', true, NOW());

-- ==================== ITEMS ====================

-- ItemTipo - Tipos base de itens
INSERT INTO ItemTipo (id, nome, descricao, limiteQuantidade, pesoPorQuantidade, ativo, dataInclusao) VALUES
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
INSERT INTO BaseItemStructure (id, base_item_structure_id, possibilidade, ativo, dataInclusao) VALUES
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
INSERT INTO ColorPossibility (id, possibilidade, ativo, dataInclusao) VALUES
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
INSERT INTO Texture (id, name, path, ativo, dataInclusao) VALUES
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
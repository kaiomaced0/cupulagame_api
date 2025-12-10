#!/usr/bin/env python3
"""
Script para adicionar getters e setters em classes Java do pacote model.
Não modifica anotações JPA/Jakarta existentes.
"""

import os
import re
from pathlib import Path
from typing import List, Tuple, Set

def capitalize_first(text: str) -> str:
    """Capitaliza a primeira letra de uma string."""
    return text[0].upper() + text[1:] if text else text

def parse_java_fields(content: str) -> List[Tuple[str, str]]:
    """
    Extrai campos privados de uma classe Java.
    Retorna lista de tuplas (tipo, nome).
    """
    # Regex para capturar campos privados (incluindo genéricos como List<...>)
    # Ignora linhas com @, pois podem ser anotações
    field_pattern = re.compile(
        r'^\s*private\s+([A-Z][\w<>,\s\[\]]*?)\s+(\w+)\s*;',
        re.MULTILINE
    )
    
    fields = []
    for match in field_pattern.finditer(content):
        field_type = match.group(1).strip()
        field_name = match.group(2).strip()
        fields.append((field_type, field_name))
    
    return fields

def has_getter(content: str, field_name: str) -> bool:
    """Verifica se existe um getter para o campo."""
    getter_name = f"get{capitalize_first(field_name)}"
    # Procura por padrão: public TipoQualquer getNome()
    getter_pattern = re.compile(
        rf'^\s*public\s+\w[\w<>,\s\[\]]*?\s+{re.escape(getter_name)}\s*\(',
        re.MULTILINE
    )
    return bool(getter_pattern.search(content))

def has_setter(content: str, field_name: str) -> bool:
    """Verifica se existe um setter para o campo."""
    setter_name = f"set{capitalize_first(field_name)}"
    # Procura por padrão: public void setNome(
    setter_pattern = re.compile(
        rf'^\s*public\s+void\s+{re.escape(setter_name)}\s*\(',
        re.MULTILINE
    )
    return bool(setter_pattern.search(content))

def generate_getter(field_type: str, field_name: str) -> str:
    """Gera código de getter."""
    method_name = f"get{capitalize_first(field_name)}"
    return f"""
    public {field_type} {method_name}() {{
        return {field_name};
    }}"""

def generate_setter(field_type: str, field_name: str) -> str:
    """Gera código de setter."""
    method_name = f"set{capitalize_first(field_name)}"
    return f"""
    public void {method_name}({field_type} {field_name}) {{
        this.{field_name} = {field_name};
    }}"""

def add_getters_setters_to_class(file_path: Path) -> bool:
    """
    Adiciona getters e setters faltantes em um arquivo Java.
    Retorna True se houve modificação.
    """
    try:
        content = file_path.read_text(encoding='utf-8')
    except Exception as e:
        print(f"❌ Erro ao ler {file_path}: {e}")
        return False
    
    # Verifica se é uma classe (não enum ou interface)
    if re.search(r'\benum\s+\w+', content) or re.search(r'\binterface\s+\w+', content):
        return False
    
    # Extrai campos privados
    fields = parse_java_fields(content)
    if not fields:
        return False
    
    # Identifica métodos faltantes
    methods_to_add = []
    for field_type, field_name in fields:
        if not has_getter(content, field_name):
            methods_to_add.append(('getter', field_type, field_name))
        if not has_setter(content, field_name):
            methods_to_add.append(('setter', field_type, field_name))
    
    if not methods_to_add:
        return False
    
    # Gera código dos métodos
    methods_code = ""
    for method_type, field_type, field_name in methods_to_add:
        if method_type == 'getter':
            methods_code += generate_getter(field_type, field_name)
        else:
            methods_code += generate_setter(field_type, field_name)
    
    # Encontra a última chave de fechamento da classe
    # Insere os métodos antes dela
    last_brace_pattern = re.compile(r'\n}\s*$', re.MULTILINE)
    match = None
    for m in last_brace_pattern.finditer(content):
        match = m
    
    if not match:
        print(f"⚠️  Não encontrei fechamento de classe em {file_path}")
        return False
    
    # Insere os novos métodos antes da última chave
    insert_pos = match.start()
    new_content = content[:insert_pos] + methods_code + "\n" + content[insert_pos:]
    
    # Escreve o arquivo modificado
    try:
        file_path.write_text(new_content, encoding='utf-8')
        return True
    except Exception as e:
        print(f"❌ Erro ao escrever {file_path}: {e}")
        return False

def process_model_directory(base_path: Path) -> Tuple[int, int]:
    """
    Processa recursivamente todos os arquivos .java no diretório model.
    Retorna (arquivos_processados, arquivos_modificados).
    """
    java_files = list(base_path.rglob('*.java'))
    processed = 0
    modified = 0
    
    print(f"🔍 Encontrados {len(java_files)} arquivos Java no diretório model")
    print("=" * 80)
    
    for java_file in java_files:
        processed += 1
        if add_getters_setters_to_class(java_file):
            modified += 1
            relative_path = java_file.relative_to(base_path.parent.parent.parent.parent.parent)
            print(f"✅ {relative_path}")
    
    return processed, modified

def main():
    # Caminho base do projeto
    project_root = Path(__file__).parent
    model_path = project_root / 'src' / 'main' / 'java' / 'org' / 'cupula' / 'model'
    
    if not model_path.exists():
        print(f"❌ Diretório não encontrado: {model_path}")
        return 1
    
    print("🚀 Iniciando processamento de getters/setters em classes model...")
    print(f"📁 Diretório base: {model_path}")
    print()
    
    processed, modified = process_model_directory(model_path)
    
    print("=" * 80)
    print(f"✨ Processamento concluído!")
    print(f"📊 Arquivos processados: {processed}")
    print(f"✏️  Arquivos modificados: {modified}")
    print(f"✓  Arquivos sem alteração: {processed - modified}")
    
    return 0

if __name__ == '__main__':
    exit(main())

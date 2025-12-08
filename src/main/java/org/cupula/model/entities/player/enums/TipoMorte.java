package org.cupula.model.entities.player.enums;

public enum TipoMorte {
    PVP,           // Morto por outro player
    PVE,           // Morto por mob
    QUEDA,         // Caiu de muito alto
    AFOGAMENTO,    // Ficou sem ar debaixo d'água
    FOME,          // Estamina/fome zerou
    VENENO,        // DoT (Damage over Time)
    ARMADILHA,     // Trap/explosão
    SUICIDIO,      // Pulou de propósito ou comando /kill
    DESCONHECIDO
}

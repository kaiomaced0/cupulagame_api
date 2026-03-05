package org.cupula.model.auth.pagamento;

public enum BandeiraCartao {
    VISA,
    MASTERCARD,
    ELO,
    AMERICAN_EXPRESS,
    HIPERCARD,
    OUTRO;

    public static BandeiraCartao fromId(Integer id) {
        if (id == null) return null;
        BandeiraCartao[] values = BandeiraCartao.values();
        if (id < 0 || id >= values.length) {
            throw new IllegalArgumentException("ID inválido para BandeiraCartao: " + id);
        }
        return values[id];
    }

    public Integer getId() {
        return this.ordinal();
    }
}

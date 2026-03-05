package org.cupula.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import org.cupula.model.auth.enums.AuthProvider;
import org.cupula.model.auth.enums.Perfil;
import org.cupula.model.auth.pagamento.BandeiraCartao;
import org.cupula.model.auth.pessoa.Sexo;
import org.cupula.model.comunity.VisibilidadePerfil;
import org.cupula.model.entities.enums.MobTipo;
import org.cupula.model.entities.enums.PlayerRaca;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EnumConverterProvider implements ParamConverterProvider {

    @SuppressWarnings("unchecked")
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if (rawType.equals(Sexo.class)) {
            return (ParamConverter<T>) new SexoConverter();
        }
        if (rawType.equals(AuthProvider.class)) {
            return (ParamConverter<T>) new AuthProviderConverter();
        }
        if (rawType.equals(BandeiraCartao.class)) {
            return (ParamConverter<T>) new BandeiraCartaoConverter();
        }
        if (rawType.equals(PlayerRaca.class)) {
            return (ParamConverter<T>) new PlayerRacaConverter();
        }
        if (rawType.equals(MobTipo.class)) {
            return (ParamConverter<T>) new MobTipoConverter();
        }
        if (rawType.equals(VisibilidadePerfil.class)) {
            return (ParamConverter<T>) new VisibilidadePerfilConverter();
        }
        if (rawType.equals(Perfil.class)) {
            return (ParamConverter<T>) new PerfilConverter();
        }
        return null;
    }
}

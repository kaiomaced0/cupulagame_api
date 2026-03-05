package org.cupula.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import org.cupula.model.auth.enums.AuthProvider;
import org.cupula.model.auth.pessoa.Sexo;

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
        return null;
    }
}

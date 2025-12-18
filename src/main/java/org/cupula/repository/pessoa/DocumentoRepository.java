package org.cupula.repository.pessoa;

import org.cupula.model.documento.Documento;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DocumentoRepository implements PanacheRepository<Documento> {
}

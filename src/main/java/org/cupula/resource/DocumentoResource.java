package org.cupula.resource;

import org.cupula.model.documento.Documento;
import org.cupula.model.documento.DocumentoTipo;
import org.cupula.service.DocumentoService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import io.netty.handler.codec.http.multipart.FileUpload;
import jakarta.ws.rs.BeanParam;

@Path("/documentos")
@Produces(MediaType.APPLICATION_JSON)
public class DocumentoResource {

    @Inject
    DocumentoService documentoService;

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@BeanParam("file") FileUpload file,
                           @BeanParam("tipo") DocumentoTipo tipo) {
        Documento doc = documentoService.uploadDocumento(file, tipo);
        return Response.status(Response.Status.CREATED).entity(doc).build();
    }

    @GET
    @Path("/{id}/download")
    public Response download(@PathParam("id") Long id) {
        return documentoService.downloadDocumento(id);
    }
}

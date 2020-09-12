package pe.com.coelectus.inventario.dto.converter;

import org.springframework.stereotype.Component;

import pe.com.coelectus.inventario.dto.DocumentTypeDto;
import pe.com.coelectus.inventario.entity.DocumentType;

@Component
public class DocumentTypeConverter extends AbstractConverter<DocumentType, DocumentTypeDto>{

	@Override
	public DocumentType fromDto(DocumentTypeDto dto) {
		DocumentType docType = new DocumentType();
		docType.setDoctypeId(dto.getId());
		docType.setName(dto.getName());
		docType.setShortname(dto.getShortname());
		return docType;
	}

	@Override
	public DocumentTypeDto fromEntity(DocumentType entity) {
		DocumentTypeDto type = new DocumentTypeDto();
		type.setId(entity.getDoctypeId());
		type.setName(entity.getName());
		type.setShortname(entity.getShortname());
		return type;
	}

}
